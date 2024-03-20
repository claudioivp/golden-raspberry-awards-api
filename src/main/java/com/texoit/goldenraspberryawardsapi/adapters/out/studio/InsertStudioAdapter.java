package com.texoit.goldenraspberryawardsapi.adapters.out.studio;

import com.texoit.goldenraspberryawardsapi.adapters.out.studio.repository.StudioRepository;
import com.texoit.goldenraspberryawardsapi.adapters.out.studio.repository.mapper.StudioEntityMapper;
import com.texoit.goldenraspberryawardsapi.application.core.domain.studio.Studio;
import com.texoit.goldenraspberryawardsapi.application.ports.out.studio.InsertStudioOutputPort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class InsertStudioAdapter implements InsertStudioOutputPort {

    private final StudioRepository studioRepository;

    private final StudioEntityMapper studioEntityMapper;

    public InsertStudioAdapter(StudioRepository studioRepository, StudioEntityMapper studioEntityMapper) {
        this.studioRepository = studioRepository;
        this.studioEntityMapper = studioEntityMapper;
    }

    @Override
    @Transactional
    public Studio insert(Studio studio) {
        var studioEntity = studioRepository.saveAndFlush(studioEntityMapper.toStudioEntity(studio));
        studioRepository.refresh(studioEntity);
        return studioEntityMapper.toStudio(studioEntity);
    }

}
