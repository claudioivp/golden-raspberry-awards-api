package com.texoit.goldenraspberryawardsapi.adapters.in.controller.producer.response;

import com.texoit.goldenraspberryawardsapi.adapters.in.controller.studio.response.StudioResponse;

import java.util.Set;
import java.util.UUID;

public record ProducerMovieResponse(
        UUID id,
        Integer year,
        String title,
        Set<StudioResponse> studios,
        Boolean winner) {

}
