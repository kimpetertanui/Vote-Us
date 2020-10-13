package com.dekut.voteme.models;

/**
 * Created by Home on 11/7/2020.
 */

public class Vote {
    private String vote;

    public Vote(String vote) {
        this.vote = vote;
    }

    public Vote() {
    }

    public String getVote() {
        return vote;
    }

    public void setVote(String vote) {
        this.vote = vote;
    }
}
