package com.texoit.goldenraspberryawardsapi.adapters.in.controller.mapper;

import com.texoit.goldenraspberryawardsapi.adapters.in.controller.request.StudioRequest;
import com.texoit.goldenraspberryawardsapi.adapters.in.controller.response.StudioResponse;
import com.texoit.goldenraspberryawardsapi.application.core.domain.Studio;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudioMapper {

    Studio toStudio(StudioRequest studioRequest);
    StudioResponse toStudioResponse(Studio studio);

}
