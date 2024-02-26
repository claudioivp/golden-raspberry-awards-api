package com.texoit.goldenraspberryawardsapi.adapters.in.controller.movie.response;

import java.util.UUID;

public record MovieProducerResponse(
        UUID id,
        String name
) {

}