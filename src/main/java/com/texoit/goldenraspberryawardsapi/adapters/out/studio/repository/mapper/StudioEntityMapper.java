package com.texoit.goldenraspberryawardsapi.adapters.out.studio.repository.mapper;

import com.texoit.goldenraspberryawardsapi.adapters.out.studio.repository.entity.StudioEntity;
import com.texoit.goldenraspberryawardsapi.application.core.domain.studio.Studio;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudioEntityMapper {

    StudioEntity toStudioEntity(Studio studio);
    Studio toStudio(StudioEntity studioEntity);

}
