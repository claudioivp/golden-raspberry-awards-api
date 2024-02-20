package com.texoit.goldenraspberryawardsapi.application.core.usecase;

import com.texoit.goldenraspberryawardsapi.application.core.domain.AwardInterval;
import com.texoit.goldenraspberryawardsapi.application.ports.in.FindMinAwardIntervalInputPort;
import com.texoit.goldenraspberryawardsapi.application.ports.out.FindMinAwardIntervalOutputPort;

import java.util.Set;

public class FindMinAwardIntervalUseCase implements FindMinAwardIntervalInputPort {

    private final FindMinAwardIntervalOutputPort findMinAwardIntervalOutputPort;

    public FindMinAwardIntervalUseCase(FindMinAwardIntervalOutputPort findMinAwardIntervalOutputPort) {
        this.findMinAwardIntervalOutputPort = findMinAwardIntervalOutputPort;
    }

    @Override
    public Set<AwardInterval> findAll() {
        return findMinAwardIntervalOutputPort.findAll();
    }

}
