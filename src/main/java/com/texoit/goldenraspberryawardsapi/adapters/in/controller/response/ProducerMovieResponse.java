package com.texoit.goldenraspberryawardsapi.adapters.in.controller.response;

import java.util.Set;
import java.util.UUID;

public record ProducerMovieResponse(
        UUID id,
        Integer year,
        String title,
        Set<StudioResponse> studios,
        Boolean winner) {

}
