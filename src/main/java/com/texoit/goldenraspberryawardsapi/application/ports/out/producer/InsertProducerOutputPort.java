package com.texoit.goldenraspberryawardsapi.application.ports.out.producer;

import com.texoit.goldenraspberryawardsapi.application.core.domain.producer.Producer;

public interface InsertProducerOutputPort {

    Producer insert(Producer producer);

}
