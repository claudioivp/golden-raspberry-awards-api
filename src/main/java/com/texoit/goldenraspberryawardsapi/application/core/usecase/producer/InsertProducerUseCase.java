package com.texoit.goldenraspberryawardsapi.application.core.usecase.producer;

import com.texoit.goldenraspberryawardsapi.application.core.domain.producer.Producer;
import com.texoit.goldenraspberryawardsapi.application.ports.in.producer.InsertProducerInputPort;
import com.texoit.goldenraspberryawardsapi.application.ports.out.producer.InsertProducerOutputPort;

public class InsertProducerUseCase implements InsertProducerInputPort {

    private final InsertProducerOutputPort insertProducerOutputPort;

    public InsertProducerUseCase(InsertProducerOutputPort insertProducerOutputPort) {
        this.insertProducerOutputPort = insertProducerOutputPort;
    }

    @Override
    public Producer insert(Producer Producer){
        return insertProducerOutputPort.insert(Producer);
    }

}
