package com.texoit.goldenraspberryawardsapi.application.ports.out.studio;

import com.texoit.goldenraspberryawardsapi.application.core.domain.studio.Studio;

public interface InsertStudioOutputPort {

    Studio insert(Studio studio);

}
