package com.texoit.goldenraspberryawardsapi.application.ports.in.producer;

import com.texoit.goldenraspberryawardsapi.application.core.domain.producer.Producer;

public interface InsertProducerInputPort {

    Producer insert(Producer producer);

}
