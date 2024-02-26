package com.texoit.goldenraspberryawardsapi.application.core.usecase.producer;

import com.texoit.goldenraspberryawardsapi.application.core.domain.producer.Producer;
import com.texoit.goldenraspberryawardsapi.application.ports.in.producer.FindProducerByNameOrSaveInputPort;
import com.texoit.goldenraspberryawardsapi.application.ports.in.producer.InsertProducerInputPort;
import com.texoit.goldenraspberryawardsapi.application.ports.out.producer.FindProducerByNameOutputPort;

public class FindProducerByNameOrSaveUseCase implements FindProducerByNameOrSaveInputPort {

    private final FindProducerByNameOutputPort findProducerByNameOutputPort;
    private final InsertProducerInputPort insertProducerInputPort;

    public FindProducerByNameOrSaveUseCase(FindProducerByNameOutputPort findProducerByNameOutputPort, InsertProducerInputPort insertProducerInputPort) {
        this.findProducerByNameOutputPort = findProducerByNameOutputPort;
        this.insertProducerInputPort = insertProducerInputPort;
    }

    @Override
    public Producer execute(Producer producer) {
        var search = findProducerByNameOutputPort.find(producer.getName());
        return search.orElseGet(() -> insertProducerInputPort.insert(producer));
    }
}