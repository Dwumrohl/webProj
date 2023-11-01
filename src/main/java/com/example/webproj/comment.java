package com.example.webproj;

public class comment {
    private int id;
    private String commentBody;
    private String commDate;
    private String username;
    private boolean admin;

    public comment(int id, String commentBody, String commDate, String username) {
        this.id = id;
        this.commentBody = commentBody;
        this.commDate = commDate;
        this.username = username;
    }

    public comment(String commentBody, String commDate, String username) {
        this.commentBody = commentBody;
        this.commDate = commDate;
        this.username = username;
    }

    public comment(String commentBody, String commDate, String username, boolean admin) {
        this.commentBody = commentBody;
        this.commDate = commDate;
        this.username = username;
        this.admin = admin;
    }

    public comment(int id, String commentBody, String commDate, String username, boolean admin) {
        this.id = id;
        this.commentBody = commentBody;
        this.commDate = commDate;
        this.username = username;
        this.admin = admin;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCommentBody() {
        return commentBody;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
    }

    public String getCommDate() {
        return commDate;
    }

    public void setCommDate(String commDate) {
        this.commDate = commDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
