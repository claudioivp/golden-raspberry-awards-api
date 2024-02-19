package com.texoit.goldenraspberryawardsapi.adapters.in.controller.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

public record MovieResponse(
        UUID id,
        Integer year,
        String title,
        Set<StudioResponse> studios,
        Set<MovieProducerResponse> producers,
        Boolean winner) {

}
