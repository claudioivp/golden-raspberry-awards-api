package com.texoit.goldenraspberryawardsapi.application.ports.out;

import com.texoit.goldenraspberryawardsapi.application.core.domain.Studio;

import java.util.UUID;

public interface FindStudioByIdOutputPort {

    Studio find(UUID id);

}
