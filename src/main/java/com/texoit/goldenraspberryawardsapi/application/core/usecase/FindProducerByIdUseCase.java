package com.texoit.goldenraspberryawardsapi.application.core.usecase;

import com.texoit.goldenraspberryawardsapi.application.core.domain.Producer;
import com.texoit.goldenraspberryawardsapi.application.ports.in.FindProducerByIdInputPort;
import com.texoit.goldenraspberryawardsapi.application.ports.out.FindProducerByIdOutputPort;

import java.util.UUID;

public class FindProducerByIdUseCase implements FindProducerByIdInputPort {

    private final FindProducerByIdOutputPort findProducerByIdOutputPort;

    public FindProducerByIdUseCase(FindProducerByIdOutputPort findProducerByIdOutputPort) {
        this.findProducerByIdOutputPort = findProducerByIdOutputPort;
    }

    @Override
    public Producer find(UUID id) {
        return findProducerByIdOutputPort.find(id);
    }
}
