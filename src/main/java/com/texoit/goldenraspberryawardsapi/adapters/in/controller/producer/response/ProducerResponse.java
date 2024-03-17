package com.texoit.goldenraspberryawardsapi.adapters.in.controller.producer.response;

import java.util.List;
import java.util.UUID;

public record ProducerResponse(
        UUID id,
        String name,
        List<ProducerMovieResponse> movies
) {

}