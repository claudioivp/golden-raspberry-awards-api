package com.texoit.goldenraspberryawardsapi.application.core.usecase;

import com.texoit.goldenraspberryawardsapi.application.core.domain.Studio;
import com.texoit.goldenraspberryawardsapi.application.ports.in.FindStudioByIdInputPort;
import com.texoit.goldenraspberryawardsapi.application.ports.out.FindStudioByIdOutputPort;

import java.util.Optional;
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
