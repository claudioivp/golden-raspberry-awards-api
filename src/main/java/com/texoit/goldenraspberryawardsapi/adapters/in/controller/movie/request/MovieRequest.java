package com.texoit.goldenraspberryawardsapi.adapters.in.controller.movie.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record MovieRequest(
        @NotNull(message = "O ano deve ser informado")
        Integer year,
        @NotBlank(message = "O título não deve estar em branco")
        String title,
        @NotEmpty(message = "Deve haver pelo menos um estúdio relacionado ao filme")
        Set<MovieStudioRequest> studios,
        @NotEmpty(message = "Deve haver pelo menos um produtor relacionado ao filme")
        Set<MovieProducerRequest> producers,
        Boolean winner
) {
    public MovieRequest {
        if(winner == null) winner = false;
    }
}


/*
    Integer year;
    String title;
    final Set<Studio> studios;
    final Set<Producer> producers;
    Boolean winner;

        @NotBlank(message = "O nome não deve estar em branco")
    */
