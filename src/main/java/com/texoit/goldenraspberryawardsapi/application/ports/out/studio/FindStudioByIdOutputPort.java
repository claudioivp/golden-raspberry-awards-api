package com.texoit.goldenraspberryawardsapi.application.ports.out.studio;

import com.texoit.goldenraspberryawardsapi.application.core.domain.studio.Studio;

import java.util.UUID;

public interface FindStudioByIdOutputPort {

    Studio find(UUID id);

}
