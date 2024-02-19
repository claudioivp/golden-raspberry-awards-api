package com.texoit.goldenraspberryawardsapi.application.core.usecase;

import com.texoit.goldenraspberryawardsapi.application.core.domain.Studio;
import com.texoit.goldenraspberryawardsapi.application.ports.in.FindStudioByNameOrSaveInputPort;
import com.texoit.goldenraspberryawardsapi.application.ports.in.InsertStudioInputPort;
import com.texoit.goldenraspberryawardsapi.application.ports.out.FindStudioByNameOutputPort;

import java.util.Optional;

public class FindStudioByNameOrSaveUseCase implements FindStudioByNameOrSaveInputPort {

    private final FindStudioByNameOutputPort findStudioByNameOutputPort;
    private final InsertStudioInputPort insertStudioInputPort;

    public FindStudioByNameOrSaveUseCase(FindStudioByNameOutputPort findStudioByNameOutputPort, InsertStudioInputPort insertStudioInputPort) {
        this.findStudioByNameOutputPort = findStudioByNameOutputPort;
        this.insertStudioInputPort = insertStudioInputPort;
    }

    @Override
    public Studio execute(Studio studio) {
        var search = findStudioByNameOutputPort.find(studio.getName());
        return search.orElseGet(() -> insertStudioInputPort.insert(studio));
    }
}