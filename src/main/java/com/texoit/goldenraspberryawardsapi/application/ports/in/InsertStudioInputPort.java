package com.texoit.goldenraspberryawardsapi.application.ports.in;

import com.texoit.goldenraspberryawardsapi.adapters.out.repository.entity.StudioEntity;
import com.texoit.goldenraspberryawardsapi.application.core.domain.Studio;

public interface InsertStudioInputPort {

    Studio insert(Studio studio);

}
