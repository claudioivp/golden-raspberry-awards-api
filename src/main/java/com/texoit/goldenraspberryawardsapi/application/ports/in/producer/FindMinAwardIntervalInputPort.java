package com.texoit.goldenraspberryawardsapi.application.ports.in.producer;

import com.texoit.goldenraspberryawardsapi.application.core.domain.producer.AwardInterval;

import java.util.Set;

public interface FindMinAwardIntervalInputPort {

    Set<AwardInterval> findAll();

}
