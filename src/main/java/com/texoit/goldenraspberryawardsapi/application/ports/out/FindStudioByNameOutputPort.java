package com.texoit.goldenraspberryawardsapi.application.ports.out;

import com.texoit.goldenraspberryawardsapi.application.core.domain.Studio;

import java.util.Optional;

public interface FindStudioByNameOutputPort {

    Optional<Studio> find(String name);

}
