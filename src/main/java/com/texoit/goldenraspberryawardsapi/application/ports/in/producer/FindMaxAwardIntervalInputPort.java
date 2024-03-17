package com.texoit.goldenraspberryawardsapi.application.ports.in.producer;

import com.texoit.goldenraspberryawardsapi.application.core.domain.producer.AwardInterval;

import java.util.List;

public interface FindMaxAwardIntervalInputPort {

    List<AwardInterval> findAll();

}
