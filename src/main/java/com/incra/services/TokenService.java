package com.incra.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.incra.pojo.AniListAccessToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * TokenService will hold onto any of the temporary access tokens that the app currently holds for accessing
 * third party APIs. (such as Anilist.co)
 *
 * @author Brandon Risberg
 * @since 3/9/2016
 */
@Transactional
@Repository
public class TokenService {
    private static final Logger logger = LoggerFactory.getLogger(AniListApiService.class);

    private AniListAccessToken aniListAccessToken; // stored access token for Anilist.co

    public AniListAccessToken acquireAniListAccessToken() {
        if (aniListAccessToken != null && System.currentTimeMillis() < aniListAccessToken.getExpiresDate().getTime()) {
            return aniListAccessToken;
        } else {
            // acquire a new access token from Anilist.co
            String url = "https://anilist.co/api/auth/access_token";

            URL obj;
            try {
                obj = new URL(url);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();

                // set request params with my client info on AniList.co/settings/developer
                con.setRequestMethod("POST");
                con.setRequestProperty("User-Agent", "brisberg");
                con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

                // TODO: hard coded client credentials for now
                String urlParameters = "grant_type=client_credentials&client_id=brisbergdev-nvmwo&client_secret=hiufWOrDjZuKUzeh0KWDZvtU";

                // Send post request
                con.setDoOutput(true);
                con.setDoInput(true);
                OutputStream os = con.getOutputStream();
                BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                wr.write(urlParameters);
                wr.flush();
                wr.close();
                os.close();

                int responseCode = con.getResponseCode();
                System.out.println("\nSending 'POST' request to URL : " + url);
                System.out.println("Post parameters : " + urlParameters);
                System.out.println("Response Code : " + responseCode);

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuilder responseBuffer = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    responseBuffer.append(inputLine);
                }
                in.close();

                //print result
                System.out.println(responseBuffer.toString());

                ObjectMapper mapper = new ObjectMapper();
                return mapper.readValue(responseBuffer.toString(), AniListAccessToken.class);
            } catch (Exception e) {
                logger.error(e.toString());
            }

            return null;
        }
    }
}
