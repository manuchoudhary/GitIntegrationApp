package com.example.manishchoudhary.gitintegrationapp.data;

import java.io.Serializable;
import java.util.List;

public class GitUser implements Serializable {

    private int gitUserID;
    private String gitUserName;
    private String gitName;
    private String imgURL;
    private List<Repository> repoList;

    public GitUser(int gitUserID, String gitUserName, String gitName, String imgURL, List<Repository> repoList) {
        this.gitUserID = gitUserID;
        this.gitUserName = gitUserName;
        this.gitName = gitName;
        this.imgURL = imgURL;
        this.repoList = repoList;
    }

    public GitUser(){

    }

    public int getGitUserID() {
        return gitUserID;
    }

    public void setGitUserID(int gitUserID) {
        this.gitUserID = gitUserID;
    }

    public String getGitUserName() {
        return gitUserName;
    }

    public void setGitUserName(String gitUserName) {
        this.gitUserName = gitUserName;
    }

    public String getGitName() {
        return gitName;
    }

    public void setGitName(String gitName) {
        this.gitName = gitName;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public List<Repository> getRepoList() {
        return repoList;
    }

    public void setRepoList(List<Repository> repoList) {
        this.repoList = repoList;
    }
}
