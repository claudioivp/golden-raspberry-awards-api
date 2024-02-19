package com.texoit.goldenraspberryawardsapi.adapters.out;

import com.texoit.goldenraspberryawardsapi.adapters.out.repository.StudioRepository;
import com.texoit.goldenraspberryawardsapi.adapters.out.repository.mapper.StudioEntityMapper;
import com.texoit.goldenraspberryawardsapi.application.core.domain.Studio;
import com.texoit.goldenraspberryawardsapi.application.ports.out.FindStudioByIdOutputPort;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class FindStudioByIdAdapter implements FindStudioByIdOutputPort {

    private final StudioRepository studioRepository;
    private final StudioEntityMapper studioEntityMapper;

    public FindStudioByIdAdapter(StudioRepository studioRepository, StudioEntityMapper studioEntityMapper) {
        this.studioRepository = studioRepository;
        this.studioEntityMapper = studioEntityMapper;
    }

    @Override
    public Studio find(UUID id) {
        var studioEntity = studioRepository.getReferenceById(id);
        return studioEntityMapper.toStudio(studioEntity);
    }
}
