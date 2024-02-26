package com.texoit.goldenraspberryawardsapi.adapters.out.studio;

import com.texoit.goldenraspberryawardsapi.adapters.out.studio.repository.StudioRepository;
import com.texoit.goldenraspberryawardsapi.adapters.out.studio.repository.mapper.StudioEntityMapper;
import com.texoit.goldenraspberryawardsapi.application.core.domain.studio.Studio;
import com.texoit.goldenraspberryawardsapi.application.ports.out.studio.FindStudioByNameOutputPort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class FindStudioByNameAdapter implements FindStudioByNameOutputPort {

    private final StudioRepository studioRepository;
    private final StudioEntityMapper studioEntityMapper;

    public FindStudioByNameAdapter(StudioRepository studioRepository, StudioEntityMapper studioEntityMapper) {
        this.studioRepository = studioRepository;
        this.studioEntityMapper = studioEntityMapper;
    }

    @Override
    public Optional<Studio> find(String name) {
        var studioEntity = studioRepository.findByName(name);
        return studioEntity.map(studioEntityMapper::toStudio);
    }
}
