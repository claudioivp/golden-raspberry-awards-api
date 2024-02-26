package com.texoit.goldenraspberryawardsapi.application.core.usecase.studio;

import com.texoit.goldenraspberryawardsapi.application.core.domain.studio.Studio;
import com.texoit.goldenraspberryawardsapi.application.ports.in.studio.InsertStudioInputPort;
import com.texoit.goldenraspberryawardsapi.application.ports.out.studio.InsertStudioOutputPort;

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
