package com.texoit.goldenraspberryawardsapi.application.ports.out.producer;

import com.texoit.goldenraspberryawardsapi.application.core.domain.producer.Producer;

import java.util.Optional;

public interface FindProducerByNameOutputPort {

    Optional<Producer> find(String name);

}
