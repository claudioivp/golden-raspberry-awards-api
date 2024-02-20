package com.texoit.goldenraspberryawardsapi.application.ports.out;

import com.texoit.goldenraspberryawardsapi.application.core.domain.AwardInterval;

import java.util.Set;

public interface FindMinAwardIntervalOutputPort {

    Set<AwardInterval> findAll();

}
