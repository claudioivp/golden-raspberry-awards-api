package com.texoit.goldenraspberryawardsapi.application.ports.out.producer;

import com.texoit.goldenraspberryawardsapi.application.core.domain.producer.AwardInterval;

import java.util.List;

public interface FindMinAwardIntervalOutputPort {

    List<AwardInterval> findAll();

}
