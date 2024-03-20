package com.texoit.goldenraspberryawardsapi.adapters.out.movie.repository.mapper;

import com.texoit.goldenraspberryawardsapi.adapters.out.studio.repository.entity.StudioEntity;
import com.texoit.goldenraspberryawardsapi.application.core.domain.studio.Studio;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovieStudioEntityMapper {

    StudioEntity toStudioEntity(Studio studio);
    Studio toStudio(StudioEntity studioEntity);

}
