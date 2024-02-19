package com.texoit.goldenraspberryawardsapi.application.core.usecase;

import com.texoit.goldenraspberryawardsapi.application.core.domain.Producer;
import com.texoit.goldenraspberryawardsapi.application.ports.in.FindProducerByNameOrSaveInputPort;
import com.texoit.goldenraspberryawardsapi.application.ports.in.InsertProducerInputPort;
import com.texoit.goldenraspberryawardsapi.application.ports.out.FindProducerByNameOutputPort;

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