package com.texoit.goldenraspberryawardsapi.adapters.in.controller.producer.response;

import com.texoit.goldenraspberryawardsapi.adapters.in.controller.studio.response.StudioResponse;

import java.util.List;
import java.util.UUID;

public record ProducerMovieResponse(
        UUID id,
        Integer year,
        String title,
        List<StudioResponse> studios,
        Boolean winner) {

}
