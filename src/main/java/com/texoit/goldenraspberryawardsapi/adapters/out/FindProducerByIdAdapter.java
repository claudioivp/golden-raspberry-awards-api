package com.texoit.goldenraspberryawardsapi.adapters.out;

import com.texoit.goldenraspberryawardsapi.adapters.out.repository.ProducerRepository;
import com.texoit.goldenraspberryawardsapi.adapters.out.repository.mapper.ProducerEntityMapper;
import com.texoit.goldenraspberryawardsapi.application.core.domain.Producer;
import com.texoit.goldenraspberryawardsapi.application.ports.out.FindProducerByIdOutputPort;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class FindProducerByIdAdapter implements FindProducerByIdOutputPort {

    private final ProducerRepository producerRepository;
    private final ProducerEntityMapper producerEntityMapper;

    public FindProducerByIdAdapter(ProducerRepository producerRepository, ProducerEntityMapper producerEntityMapper) {
        this.producerRepository = producerRepository;
        this.producerEntityMapper = producerEntityMapper;
    }

    @Override
    public Producer find(UUID id) {
        var producerEntity = producerRepository.getReferenceById(id);
        return producerEntityMapper.toProducer(producerEntity);
    }
}
