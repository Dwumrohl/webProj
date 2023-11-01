package com.example.webproj;

import java.io.File;

public class announcement {
    private int id;
    private String header;
    private String image;
    private String type;
    private String date;
    private String content;
    private String textInfo;
    private int idTypesFk;

    public announcement(String header, String image, String type, String content, String textInfo, int idTypesFk) {
        this.header = header;
        this.image = image;
        this.type = type;
        this.content = content;
        this.textInfo = textInfo;
        this.idTypesFk = idTypesFk;
    }

    public announcement(String header, String type, String content, String textInfo, int idTypesFk) {
        this.header = header;
        this.type = type;
        this.content = content;
        this.textInfo = textInfo;
        this.idTypesFk = idTypesFk;
    }

    public announcement(int id, String header, String image, String type, String date, String content, String textInfo) {
        this.id = id;
        this.header = header;
        this.image = image;
        this.type = type;
        this.date = date;
        this.content = content;
        this.textInfo = textInfo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getIdTypesFk() {
        return idTypesFk;
    }

    public void setIdTypesFk(int idTypesFk) {
        this.idTypesFk = idTypesFk;
    }

    public String getTextInfo() {
        return textInfo;
    }

    public void setTextInfo(String textInfo) {
        this.textInfo = textInfo;
    }
}
