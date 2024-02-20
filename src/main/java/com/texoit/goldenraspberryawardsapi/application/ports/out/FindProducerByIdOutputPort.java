package com.texoit.goldenraspberryawardsapi.application.ports.out;

import com.texoit.goldenraspberryawardsapi.application.core.domain.Producer;

import java.util.UUID;

public interface FindProducerByIdOutputPort {

    Producer find(UUID id);

}
