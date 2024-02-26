package com.texoit.goldenraspberryawardsapi.adapters.out.producer.repository.response;

public interface ProducerIntervalsProjection {
    String getProducer();
    Integer getYearInterval();
    Integer getPreviousWin();
    Integer getFollowingWin();
}
