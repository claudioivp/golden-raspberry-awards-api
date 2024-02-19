package com.texoit.goldenraspberryawardsapi.application.ports.out;

import com.texoit.goldenraspberryawardsapi.application.core.domain.Producer;
import com.texoit.goldenraspberryawardsapi.application.core.domain.Studio;

import java.util.Optional;

public interface FindProducerByNameOutputPort {

    Optional<Producer> find(String name);

}
