package com.texoit.goldenraspberryawardsapi.application.ports.out.studio;

import com.texoit.goldenraspberryawardsapi.application.core.domain.studio.Studio;

import java.util.Optional;

public interface FindStudioByNameOutputPort {

    Optional<Studio> find(String name);

}
