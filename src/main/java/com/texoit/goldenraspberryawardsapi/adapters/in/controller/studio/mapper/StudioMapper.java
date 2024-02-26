package com.texoit.goldenraspberryawardsapi.adapters.in.controller.studio.mapper;

import com.texoit.goldenraspberryawardsapi.adapters.in.controller.studio.request.StudioRequest;
import com.texoit.goldenraspberryawardsapi.adapters.in.controller.studio.response.StudioResponse;
import com.texoit.goldenraspberryawardsapi.application.core.domain.studio.Studio;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudioMapper {

    Studio toStudio(StudioRequest studioRequest);
    StudioResponse toStudioResponse(Studio studio);

}
