package com.texoit.goldenraspberryawardsapi.application.ports.out.producer;

import com.texoit.goldenraspberryawardsapi.application.core.domain.producer.Producer;

import java.util.UUID;

public interface FindProducerByIdOutputPort {

    Producer find(UUID id);

}
