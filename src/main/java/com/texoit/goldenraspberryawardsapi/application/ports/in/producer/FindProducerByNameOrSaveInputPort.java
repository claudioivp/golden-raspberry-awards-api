package com.texoit.goldenraspberryawardsapi.application.ports.in.producer;

import com.texoit.goldenraspberryawardsapi.application.core.domain.producer.Producer;

public interface FindProducerByNameOrSaveInputPort {

    Producer execute(Producer studio);

}