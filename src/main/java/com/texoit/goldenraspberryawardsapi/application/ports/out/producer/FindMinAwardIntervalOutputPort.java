package com.texoit.goldenraspberryawardsapi.application.ports.out.producer;

import com.texoit.goldenraspberryawardsapi.application.core.domain.producer.AwardInterval;

import java.util.Set;

public interface FindMinAwardIntervalOutputPort {

    Set<AwardInterval> findAll();

}
