package com.texoit.goldenraspberryawardsapi.application.core.usecase.studio;

import com.texoit.goldenraspberryawardsapi.application.core.domain.studio.Studio;
import com.texoit.goldenraspberryawardsapi.application.ports.in.studio.FindStudioByIdInputPort;
import com.texoit.goldenraspberryawardsapi.application.ports.out.studio.FindStudioByIdOutputPort;

import java.util.UUID;

public class FindStudioByIdUseCase implements FindStudioByIdInputPort {

    private final FindStudioByIdOutputPort findStudioByIdOutputPort;

    public FindStudioByIdUseCase(FindStudioByIdOutputPort findStudioByIdOutputPort) {
        this.findStudioByIdOutputPort = findStudioByIdOutputPort;
    }

    @Override
    public Studio find(UUID id) {
        return findStudioByIdOutputPort.find(id);
    }
}
