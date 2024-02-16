package com.texoit.goldenraspberryawardsapi.application.core.usecase;

import com.texoit.goldenraspberryawardsapi.application.core.domain.Producer;
import com.texoit.goldenraspberryawardsapi.application.ports.in.InsertProducerInputPort;
import com.texoit.goldenraspberryawardsapi.application.ports.out.InsertProducerOutputPort;

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
