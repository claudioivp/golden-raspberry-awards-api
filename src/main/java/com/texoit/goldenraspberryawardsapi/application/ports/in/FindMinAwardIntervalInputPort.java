package com.texoit.goldenraspberryawardsapi.application.ports.in;

import com.texoit.goldenraspberryawardsapi.application.core.domain.AwardInterval;

import java.util.Set;

public interface FindMinAwardIntervalInputPort {

    Set<AwardInterval> findAll();

}
