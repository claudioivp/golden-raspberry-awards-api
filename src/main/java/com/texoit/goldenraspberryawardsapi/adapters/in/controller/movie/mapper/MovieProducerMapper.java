package com.texoit.goldenraspberryawardsapi.adapters.in.controller.movie.mapper;

import com.texoit.goldenraspberryawardsapi.adapters.in.controller.producer.request.ProducerRequest;
import com.texoit.goldenraspberryawardsapi.adapters.in.controller.producer.response.ProducerResponse;
import com.texoit.goldenraspberryawardsapi.application.core.domain.producer.Producer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MovieProducerMapper {

    Producer toProducer(ProducerRequest producerRequest);
    @Mapping(target = "movies", ignore = true)
    ProducerResponse toProducerResponse(Producer producer);

}
