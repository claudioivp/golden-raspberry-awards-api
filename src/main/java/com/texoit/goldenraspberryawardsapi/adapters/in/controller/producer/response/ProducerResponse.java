package com.texoit.goldenraspberryawardsapi.adapters.in.controller.producer.response;

import java.util.Set;
import java.util.UUID;

public record ProducerResponse(
        UUID id,
        String name,
        Set<ProducerMovieResponse> movies
) {

}