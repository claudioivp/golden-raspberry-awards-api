package com.texoit.goldenraspberryawardsapi.application.ports.in;

import com.texoit.goldenraspberryawardsapi.application.core.domain.Studio;

import java.util.UUID;

public interface FindStudioByIdInputPort {

    Studio find(UUID id);

}
