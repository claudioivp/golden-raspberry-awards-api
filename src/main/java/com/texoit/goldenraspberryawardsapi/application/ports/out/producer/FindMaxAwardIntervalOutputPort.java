package com.texoit.goldenraspberryawardsapi.application.ports.out.producer;

import com.texoit.goldenraspberryawardsapi.application.core.domain.producer.AwardInterval;

import java.util.List;

public interface FindMaxAwardIntervalOutputPort {

    List<AwardInterval> findAll();

}
