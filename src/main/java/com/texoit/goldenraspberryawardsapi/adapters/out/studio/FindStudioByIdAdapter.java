package com.texoit.goldenraspberryawardsapi.adapters.out.studio;

import com.texoit.goldenraspberryawardsapi.adapters.out.studio.repository.StudioRepository;
import com.texoit.goldenraspberryawardsapi.adapters.out.studio.repository.mapper.StudioEntityMapper;
import com.texoit.goldenraspberryawardsapi.application.core.domain.studio.Studio;
import com.texoit.goldenraspberryawardsapi.application.ports.out.studio.FindStudioByIdOutputPort;
import org.springframework.stereotype.Component;

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
