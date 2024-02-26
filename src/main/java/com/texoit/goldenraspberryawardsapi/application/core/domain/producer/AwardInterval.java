package com.texoit.goldenraspberryawardsapi.application.core.domain.producer;

public class AwardInterval {

    private String producer;
    private Integer yearInterval;
    private Integer previousWin;
    private Integer followingWin;

    public AwardInterval(String producer, Integer yearInterval, Integer previousWin, Integer followingWin) {
        this.producer = producer;
        this.yearInterval = yearInterval;
        this.previousWin = previousWin;
        this.followingWin = followingWin;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public Integer getYearInterval() {
        return yearInterval;
    }

    public void setYearInterval(Integer yearInterval) {
        this.yearInterval = yearInterval;
    }

    public Integer getPreviousWin() {
        return previousWin;
    }

    public void setPreviousWin(Integer previousWin) {
        this.previousWin = previousWin;
    }

    public Integer getFollowingWin() {
        return followingWin;
    }

    public void setFollowingWin(Integer followingWin) {
        this.followingWin = followingWin;
    }
}
