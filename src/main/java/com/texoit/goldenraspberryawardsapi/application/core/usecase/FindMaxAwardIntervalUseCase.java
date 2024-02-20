package com.texoit.goldenraspberryawardsapi.application.core.usecase;

import com.texoit.goldenraspberryawardsapi.application.core.domain.AwardInterval;
import com.texoit.goldenraspberryawardsapi.application.ports.in.FindMaxAwardIntervalInputPort;
import com.texoit.goldenraspberryawardsapi.application.ports.out.FindMaxAwardIntervalOutputPort;

import java.util.Set;

public class FindMaxAwardIntervalUseCase implements FindMaxAwardIntervalInputPort {

    private final FindMaxAwardIntervalOutputPort findMaxAwardIntervalOutputPort;

    public FindMaxAwardIntervalUseCase(FindMaxAwardIntervalOutputPort findMaxAwardIntervalOutputPort) {
        this.findMaxAwardIntervalOutputPort = findMaxAwardIntervalOutputPort;
    }

    @Override
    public Set<AwardInterval> findAll() {
        return findMaxAwardIntervalOutputPort.findAll();
    }

}
