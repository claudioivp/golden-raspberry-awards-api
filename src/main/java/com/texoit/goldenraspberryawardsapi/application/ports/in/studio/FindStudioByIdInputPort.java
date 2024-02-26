package com.texoit.goldenraspberryawardsapi.application.ports.in.studio;

import com.texoit.goldenraspberryawardsapi.application.core.domain.studio.Studio;

import java.util.UUID;

public interface FindStudioByIdInputPort {

    Studio find(UUID id);

}
