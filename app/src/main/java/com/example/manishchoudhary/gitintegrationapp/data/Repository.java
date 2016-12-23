package com.example.manishchoudhary.gitintegrationapp.data;

import java.io.Serializable;

/**
 * Created by manish.choudhary on 12/21/2016.
 */

public class Repository implements Serializable {

    private int repoID;
    private int repoOwnerID;
    private int repoStarCount;
    private int repoWatchCount;
    private String repoOwnerName;
    private String repoDesc;
    private String repoName;
    private String imageURL;

    public Repository(int repoID, int repoOwnerID, int repoStarCount, int repoWatchCount, String repoOwnerName, String repoDesc, String repoName, String imageURL) {
        this.repoID = repoID;
        this.repoOwnerID = repoOwnerID;
        this.repoStarCount = repoStarCount;
        this.repoWatchCount = repoWatchCount;
        this.repoOwnerName = repoOwnerName;
        this.repoDesc = repoDesc;
        this.repoName = repoName;
        this.imageURL = imageURL;
    }

    public Repository(){

    }

    public int getRepoID() {
        return repoID;
    }

    public int getRepoOwnerID() {
        return repoOwnerID;
    }

    public int getRepoStarCount() {
        return repoStarCount;
    }

    public int getRepoWatchCount() {
        return repoWatchCount;
    }

    public String getRepoOwnerName() {
        return repoOwnerName;
    }

    public void setRepoID(int repoID) {
        this.repoID = repoID;
    }

    public void setRepoOwnerID(int repoOwnerID) {
        this.repoOwnerID = repoOwnerID;
    }

    public void setRepoStarCount(int repoStarCount) {
        this.repoStarCount = repoStarCount;
    }

    public void setRepoWatchCount(int repoWatchCount) {
        this.repoWatchCount = repoWatchCount;
    }

    public void setRepoOwnerName(String repoOwnerName) {
        this.repoOwnerName = repoOwnerName;
    }

    public String getRepoDesc() { return repoDesc; }

    public void setRepoDesc(String repoDesc) { this.repoDesc = repoDesc; }

    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }

    public String getImageURL() { return imageURL; }

    public void setImageURL(String imageURL) { this.imageURL = imageURL; }
}
