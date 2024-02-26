package com.texoit.goldenraspberryawardsapi.adapters.in.controller.producer.mapper;

import com.texoit.goldenraspberryawardsapi.adapters.in.controller.producer.request.ProducerRequest;
import com.texoit.goldenraspberryawardsapi.adapters.in.controller.producer.response.ProducerResponse;
import com.texoit.goldenraspberryawardsapi.application.core.domain.producer.Producer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProducerMapper {

    Producer toProducer(ProducerRequest producerRequest);
    ProducerResponse toProducerResponse(Producer producer);

}
