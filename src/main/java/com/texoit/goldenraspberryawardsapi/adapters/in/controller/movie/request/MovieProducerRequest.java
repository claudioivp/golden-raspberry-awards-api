package com.texoit.goldenraspberryawardsapi.adapters.in.controller.movie.request;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record MovieProducerRequest(
        @NotNull(message = "O produtor precisa ser informado")
        UUID id
) {

}