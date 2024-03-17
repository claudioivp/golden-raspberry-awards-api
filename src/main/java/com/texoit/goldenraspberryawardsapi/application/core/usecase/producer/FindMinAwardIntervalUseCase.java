package com.texoit.goldenraspberryawardsapi.application.core.usecase.producer;

import com.texoit.goldenraspberryawardsapi.application.core.domain.producer.AwardInterval;
import com.texoit.goldenraspberryawardsapi.application.ports.in.producer.FindMinAwardIntervalInputPort;
import com.texoit.goldenraspberryawardsapi.application.ports.out.producer.FindMinAwardIntervalOutputPort;

import java.util.List;

public class FindMinAwardIntervalUseCase implements FindMinAwardIntervalInputPort {

    private final FindMinAwardIntervalOutputPort findMinAwardIntervalOutputPort;

    public FindMinAwardIntervalUseCase(FindMinAwardIntervalOutputPort findMinAwardIntervalOutputPort) {
        this.findMinAwardIntervalOutputPort = findMinAwardIntervalOutputPort;
    }

    @Override
    public List<AwardInterval> findAll() {
        return findMinAwardIntervalOutputPort.findAll();
    }

}
