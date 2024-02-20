package com.texoit.goldenraspberryawardsapi.adapters.out.repository.mapper;

import com.texoit.goldenraspberryawardsapi.adapters.out.repository.StudioRepository;
import com.texoit.goldenraspberryawardsapi.adapters.out.repository.entity.StudioEntity;
import com.texoit.goldenraspberryawardsapi.application.core.domain.Studio;
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
        return studioRepository.findById(studio.getId()).orElseThrow();
    }

}
