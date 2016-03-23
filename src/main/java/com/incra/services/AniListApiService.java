package com.incra.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.incra.models.Episode;
import com.incra.models.Season;
import com.incra.models.Series;
import com.incra.pojo.AniListAccessToken;
import com.incra.pojo.aniListPrototypes.AniListEpisodePrototype;
import com.incra.pojo.aniListPrototypes.AniListSeriesPrototype;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Repository
public class AniListApiService {
    private static final Logger logger = LoggerFactory.getLogger(AniListApiService.class);

    @Autowired
    private TokenService tokenService;
    @Autowired
    private SeriesService seriesService;
    @Autowired
    private EpisodeService episodeService;
    @Autowired
    private ConfigurationService configurationService;

    public List<Series> fetchAllSeries(Season season) {
        AniListAccessToken accessToken = tokenService.acquireAniListAccessToken();

        String url = "http://anilist.co/api/browse/anime?access_token=" + accessToken.getAccessToken()
                + "&year=" + season.getYear()
                + "&season=" + season.getQuarter()
                + "&type=Tv"
                + "&airing_data=true"
                + "&full_page=true";

        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // optional default is GET
            con.setRequestMethod("GET");

            //add request header
            con.setRequestProperty("User-Agent", "brisberg");

            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer responseBuffer = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                responseBuffer.append(inputLine);
            }
            in.close();

            //print result
            System.out.println(responseBuffer.toString());

            // Set up the ObjectMapper to map an array of series into an array of objects.
            ObjectMapper mapper = new ObjectMapper();
            List<AniListSeriesPrototype> seriesProtoList = mapper.readValue(responseBuffer.toString(), new TypeReference<List<AniListSeriesPrototype>>() {
            });
            List<Series> seriesList = new ArrayList<Series>();

            for (AniListSeriesPrototype seriesProto : seriesProtoList) {
                Series series = seriesProto.convert();
                series.setSeason(season);
                updateSeries(series);
                seriesService.save(series); // TODO: figure a better way to do this, such that date created and last modified get set.
            }

            return seriesList;
        } catch (Exception e)

        {
            logger.error(e.toString());
        }

        return null;
    }

    /**
     * Fetches the full data from aniList for a given series Id and returns the new Series object.
     *
     * @param aniListSeriesId The integer id for this series on AniList
     * @return Resulting Series object
     * @deprecated
     */
    public Series fetchSeries(int aniListSeriesId) {
        AniListAccessToken accessToken = tokenService.acquireAniListAccessToken();

        String url = "http://anilist.co/api/anime/" + aniListSeriesId + "?access_token=" + accessToken.getAccessToken();

        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // optional default is GET
            con.setRequestMethod("GET");

            //add request header
            con.setRequestProperty("User-Agent", "brisberg");

            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer responseBuffer = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                responseBuffer.append(inputLine);
            }
            in.close();

            //print result
            System.out.println(responseBuffer.toString());

            ObjectMapper mapper = new ObjectMapper();
            AniListSeriesPrototype resultProto = mapper.readValue(responseBuffer.toString(), AniListSeriesPrototype.class);
            return resultProto.convert();
        } catch (Exception e)

        {
            logger.error(e.toString());
        }

        return null;
    }

    /**
     * Update a given series object with the latest data (or simply the full data) from AniList.
     */
    public void updateSeries(Series series) {
        AniListAccessToken accessToken = tokenService.acquireAniListAccessToken();
        int weekIndex = configurationService.findActiveEntity().getCurrentWeekIndex();

        String url = "http://anilist.co/api/anime/" + series.getAniListId() + "?access_token=" + accessToken.getAccessToken();

        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // optional default is GET
            con.setRequestMethod("GET");

            //add request header
            con.setRequestProperty("User-Agent", "brisberg");

            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer responseBuffer = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                responseBuffer.append(inputLine);
            }
            in.close();

            //print result
            System.out.println(responseBuffer.toString());

            // Map the resulting JSON string to an object
            ObjectMapper mapper = new ObjectMapper();
            AniListSeriesPrototype resultProto = mapper.readValue(responseBuffer.toString(), AniListSeriesPrototype.class);
            resultProto.updateInto(series);

            // append the next episode
            final JsonNode episodeResponse = mapper.readTree(responseBuffer.toString()).path("airing");
            AniListEpisodePrototype episodeProto = mapper.treeToValue(episodeResponse, AniListEpisodePrototype.class);
            Episode nextEpisode = series.getEpisodeByIndex(episodeProto.getEpisodeIndex());
            if (nextEpisode != null) { // update the episode record
                episodeProto.updateInto(nextEpisode);
            } else { // create and store a new episode
                nextEpisode = episodeProto.convert();
                nextEpisode.setSeries(series);
                nextEpisode.setSeason(series.getSeason());
                nextEpisode.setWeekIndex(weekIndex);

                series.getEpisodeList().add(nextEpisode);
            }
        } catch (Exception e)

        {
            logger.error(e.toString());
        }

    }
}
