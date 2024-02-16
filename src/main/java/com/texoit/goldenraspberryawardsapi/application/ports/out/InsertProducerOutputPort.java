package com.texoit.goldenraspberryawardsapi.application.ports.out;

import com.texoit.goldenraspberryawardsapi.application.core.domain.Producer;

public interface InsertProducerOutputPort {

    Producer insert(Producer producer);

}
