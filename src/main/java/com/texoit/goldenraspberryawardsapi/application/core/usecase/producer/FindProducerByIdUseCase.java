package com.texoit.goldenraspberryawardsapi.application.core.usecase.producer;

import com.texoit.goldenraspberryawardsapi.application.core.domain.producer.Producer;
import com.texoit.goldenraspberryawardsapi.application.ports.in.producer.FindProducerByIdInputPort;
import com.texoit.goldenraspberryawardsapi.application.ports.out.producer.FindProducerByIdOutputPort;

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
