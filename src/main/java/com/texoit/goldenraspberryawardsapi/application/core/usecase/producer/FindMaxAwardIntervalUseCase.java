package com.texoit.goldenraspberryawardsapi.application.core.usecase.producer;

import com.texoit.goldenraspberryawardsapi.application.core.domain.producer.AwardInterval;
import com.texoit.goldenraspberryawardsapi.application.ports.in.producer.FindMaxAwardIntervalInputPort;
import com.texoit.goldenraspberryawardsapi.application.ports.out.producer.FindMaxAwardIntervalOutputPort;

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
