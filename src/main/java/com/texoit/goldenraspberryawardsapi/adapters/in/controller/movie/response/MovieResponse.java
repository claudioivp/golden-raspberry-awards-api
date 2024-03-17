package com.texoit.goldenraspberryawardsapi.adapters.in.controller.movie.response;

import com.texoit.goldenraspberryawardsapi.adapters.in.controller.studio.response.StudioResponse;

import java.util.List;
import java.util.UUID;

public record MovieResponse(
        UUID id,
        Integer year,
        String title,
        List<StudioResponse> studios,
        List<MovieProducerResponse> producers,
        Boolean winner) {

}
