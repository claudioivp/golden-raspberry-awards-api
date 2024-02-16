package com.texoit.goldenraspberryawardsapi.application.core.usecase;

import com.texoit.goldenraspberryawardsapi.adapters.out.repository.entity.StudioEntity;
import com.texoit.goldenraspberryawardsapi.application.core.domain.Studio;
import com.texoit.goldenraspberryawardsapi.application.ports.in.InsertStudioInputPort;
import com.texoit.goldenraspberryawardsapi.application.ports.out.InsertStudioOutputPort;

public class InsertStudioUseCase implements InsertStudioInputPort {

    private final InsertStudioOutputPort insertStudioOutputPort;

    public InsertStudioUseCase(InsertStudioOutputPort insertStudioOutputPort) {
        this.insertStudioOutputPort = insertStudioOutputPort;
    }

    @Override
    public Studio insert(Studio Studio){
        return insertStudioOutputPort.insert(Studio);
    }

}
