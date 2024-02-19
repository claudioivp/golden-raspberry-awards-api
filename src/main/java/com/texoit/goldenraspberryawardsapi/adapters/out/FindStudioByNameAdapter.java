package com.texoit.goldenraspberryawardsapi.adapters.out;

import com.texoit.goldenraspberryawardsapi.adapters.out.repository.StudioRepository;
import com.texoit.goldenraspberryawardsapi.adapters.out.repository.mapper.StudioEntityMapper;
import com.texoit.goldenraspberryawardsapi.application.core.domain.Studio;
import com.texoit.goldenraspberryawardsapi.application.ports.out.FindStudioByNameOutputPort;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

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
