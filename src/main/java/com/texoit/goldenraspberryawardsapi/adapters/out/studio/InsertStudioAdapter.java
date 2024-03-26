package com.texoit.goldenraspberryawardsapi.adapters.out.studio;

import com.texoit.goldenraspberryawardsapi.adapters.out.studio.repository.StudioRepository;
import com.texoit.goldenraspberryawardsapi.adapters.out.studio.repository.mapper.StudioEntityMapper;
import com.texoit.goldenraspberryawardsapi.application.core.domain.studio.Studio;
import com.texoit.goldenraspberryawardsapi.application.ports.out.studio.InsertStudioOutputPort;
import org.springframework.stereotype.Component;

@Component
public class InsertStudioAdapter implements InsertStudioOutputPort {

    private final StudioRepository studioRepository;

    private final StudioEntityMapper studioEntityMapper;

    public InsertStudioAdapter(StudioRepository studioRepository, StudioEntityMapper studioEntityMapper) {
        this.studioRepository = studioRepository;
        this.studioEntityMapper = studioEntityMapper;
    }

    @Override
    public Studio insert(Studio studio) {
        var studioEntity = studioRepository.save(studioEntityMapper.toStudioEntity(studio));
        return studioEntityMapper.toStudio(studioEntity);
    }

}
