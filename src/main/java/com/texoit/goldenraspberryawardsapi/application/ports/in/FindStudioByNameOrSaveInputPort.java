package com.texoit.goldenraspberryawardsapi.application.ports.in;

import com.texoit.goldenraspberryawardsapi.application.core.domain.Studio;

public interface FindStudioByNameOrSaveInputPort {

    Studio execute(Studio studio);

}