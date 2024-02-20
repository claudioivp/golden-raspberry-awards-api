package com.texoit.goldenraspberryawardsapi.adapters.in.controller.mapper;

import com.texoit.goldenraspberryawardsapi.adapters.in.controller.request.ProducerRequest;
import com.texoit.goldenraspberryawardsapi.adapters.in.controller.response.ProducerResponse;
import com.texoit.goldenraspberryawardsapi.application.core.domain.Producer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MovieProducerMapper {

    Producer toProducer(ProducerRequest producerRequest);
    @Mapping(target = "movies", ignore = true)
    ProducerResponse toProducerResponse(Producer producer);

}
