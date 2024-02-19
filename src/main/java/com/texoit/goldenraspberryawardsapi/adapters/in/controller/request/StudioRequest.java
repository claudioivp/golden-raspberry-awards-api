package com.texoit.goldenraspberryawardsapi.adapters.in.controller.request;

import jakarta.validation.constraints.NotBlank;

public record StudioRequest(
    @NotBlank(message = "O nome não deve estar em branco")
    String name
) {

}