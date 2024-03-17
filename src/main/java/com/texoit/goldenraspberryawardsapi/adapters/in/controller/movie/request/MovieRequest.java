package com.texoit.goldenraspberryawardsapi.adapters.in.controller.movie.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record MovieRequest(
        @NotNull(message = "O ano deve ser informado")
        Integer year,
        @NotBlank(message = "O título não deve estar em branco")
        String title,
        @NotEmpty(message = "Deve haver pelo menos um estúdio relacionado ao filme")
        List<MovieStudioRequest> studios,
        @NotEmpty(message = "Deve haver pelo menos um produtor relacionado ao filme")
        List<MovieProducerRequest> producers,
        Boolean winner
) {
    public MovieRequest {
        if(winner == null) winner = false;
    }

}