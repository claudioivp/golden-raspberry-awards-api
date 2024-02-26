package com.texoit.goldenraspberryawardsapi.application.core.usecase.studio;

import com.texoit.goldenraspberryawardsapi.application.core.domain.studio.Studio;
import com.texoit.goldenraspberryawardsapi.application.ports.in.studio.FindStudioByNameOrSaveInputPort;
import com.texoit.goldenraspberryawardsapi.application.ports.in.studio.InsertStudioInputPort;
import com.texoit.goldenraspberryawardsapi.application.ports.out.studio.FindStudioByNameOutputPort;

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