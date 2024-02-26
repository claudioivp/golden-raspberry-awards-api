package com.texoit.goldenraspberryawardsapi.application.ports.in.producer;

import com.texoit.goldenraspberryawardsapi.application.core.domain.producer.Producer;

import java.util.UUID;

public interface FindProducerByIdInputPort {

    Producer find(UUID id);

}
