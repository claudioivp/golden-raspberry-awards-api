package com.texoit.goldenraspberryawardsapi.adapters.out.movie.repository.mapper;

import com.texoit.goldenraspberryawardsapi.adapters.out.studio.repository.StudioRepository;
import com.texoit.goldenraspberryawardsapi.adapters.out.studio.repository.entity.StudioEntity;
import com.texoit.goldenraspberryawardsapi.application.core.config.csv.InvalidBeanFromCsvException;
import com.texoit.goldenraspberryawardsapi.application.core.domain.studio.Studio;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class MovieStudioEntityMapper {

    @Autowired
    private StudioRepository studioRepository;

    public abstract StudioEntity toStudioEntity(Studio studio);
    public abstract Studio toStudio(StudioEntity studioEntity);

    @AfterMapping
    public StudioEntity afterMapping(Studio studio, @MappingTarget StudioEntity studioEntity) {
        return studioRepository.findById(studio.getId()).orElseThrow(
                () -> new InvalidBeanFromCsvException(String.format("O estúdio com ID {%s} não foi encontrado.", studio.getId()))
        );
    }

}
