package com.texoit.goldenraspberryawardsapi.adapters.in.controller.mapper;

import com.texoit.goldenraspberryawardsapi.adapters.in.controller.request.ProducerRequest;
import com.texoit.goldenraspberryawardsapi.adapters.in.controller.request.StudioRequest;
import com.texoit.goldenraspberryawardsapi.adapters.in.controller.response.ProducerResponse;
import com.texoit.goldenraspberryawardsapi.adapters.in.controller.response.StudioResponse;
import com.texoit.goldenraspberryawardsapi.application.core.domain.Producer;
import com.texoit.goldenraspberryawardsapi.application.core.domain.Studio;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProducerMapper {

    Producer toProducer(ProducerRequest producerRequest);
    ProducerResponse toProducerResponse(Producer producer);

}