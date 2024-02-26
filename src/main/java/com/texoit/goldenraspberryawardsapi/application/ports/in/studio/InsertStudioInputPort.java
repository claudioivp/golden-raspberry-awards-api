package com.texoit.goldenraspberryawardsapi.application.ports.in.studio;

import com.texoit.goldenraspberryawardsapi.application.core.domain.studio.Studio;

public interface InsertStudioInputPort {

    Studio insert(Studio studio);

}
