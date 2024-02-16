package com.texoit.goldenraspberryawardsapi.application.ports.in;

import com.texoit.goldenraspberryawardsapi.application.core.domain.Producer;

public interface InsertProducerInputPort {

    Producer insert(Producer producer);

}
