package com.incra.models;

import com.incra.database.AbstractDatedDatabaseItem;
import com.incra.services.exception.ImageException;
import com.incra.util.ImageStore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Stores image info
 */
@Entity
@Table(name = "image")
public class Image extends AbstractDatedDatabaseItem {

    @Size(max = 100)
    private String title;

    @Size(max = 150)
    private String caption;

    @Column(name="filename")
    @NotNull
    private String fileName;

    @Column(name="path")
    @NotNull
    @Size(max = 100)
    private String path;

    @Column(name="width")
    private Integer width;

    @Column(name="height")
    private Integer height;

    /** Default Constructor */
    public Image() {
    }

    public Image(String title, String caption, String fileName, String path, Integer width, Integer height) {
        this.title = title;
        this.caption = caption;
        this.fileName = fileName;
        this.path = path;
        this.width = width;
        this.height = height;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getRelativePath() {
        return path + fileName;
    }

    public String getAbsolutePath() throws ImageException {
        return new ImageStore(getRelativePath()).getPath();
    }

    public String getContainingDir() throws ImageException {
        return new ImageStore(path).getPath();
    }

    private String getFileExtension() {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    private String removeFileExtension() {
        return fileName.substring(0, fileName.lastIndexOf("."));
    }

    private String removeProcessExtension() {
        if (fileName.contains("_s_")) {
            return fileName.substring(0, fileName.indexOf("_s_"));
        } else if (fileName.contains("_c_")) {
            return fileName.substring(0, fileName.indexOf("_c_"));
        } else {
            return removeFileExtension();
        }
    }

    public Image copy() {
        Image copy = new Image();
        copy.setCaption(getCaption());
        copy.setFileName(getFileName());
        copy.setHeight(getHeight());
        copy.setPath(getPath());
        copy.setTitle(getTitle());
        copy.setWidth(getWidth());
        copy.setFileName(getFileName());
        return copy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Image image = (Image) o;

        if (!fileName.equals(image.fileName)) return false;
        return path.equals(image.path);
    }

    @Override
    public int hashCode() {
        int result = fileName.hashCode();
        result = 31 * result + path.hashCode();
        return result;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();

        sb.append("Image[title=");
        sb.append(title);
        sb.append(", fileName=");
        sb.append(fileName);
        sb.append("]");

        return sb.toString();
    }
}