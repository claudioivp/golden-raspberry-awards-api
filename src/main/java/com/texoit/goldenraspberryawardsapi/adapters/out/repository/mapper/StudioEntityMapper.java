package com.texoit.goldenraspberryawardsapi.adapters.out.repository.mapper;

import com.texoit.goldenraspberryawardsapi.adapters.out.repository.entity.StudioEntity;
import com.texoit.goldenraspberryawardsapi.application.core.domain.Studio;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudioEntityMapper {

    StudioEntity toStudioEntity(Studio studio);
    Studio toStudio(StudioEntity studioEntity);

}
