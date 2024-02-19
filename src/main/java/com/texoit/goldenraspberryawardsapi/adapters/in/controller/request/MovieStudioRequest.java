package com.texoit.goldenraspberryawardsapi.adapters.in.controller.request;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record MovieStudioRequest(
        @NotNull(message = "O estúdio precisa ser informado")
        UUID id
) {

}