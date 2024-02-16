package com.texoit.goldenraspberryawardsapi.application.ports.out;

import com.texoit.goldenraspberryawardsapi.application.core.domain.Studio;

public interface InsertStudioOutputPort {

    Studio insert(Studio studio);

}
