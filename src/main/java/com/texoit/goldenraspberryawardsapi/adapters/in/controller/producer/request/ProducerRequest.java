package com.texoit.goldenraspberryawardsapi.adapters.in.controller.producer.request;

import jakarta.validation.constraints.NotBlank;

public record ProducerRequest(
        @NotBlank(message = "O nome não deve estar em branco")
        String name
) {

}