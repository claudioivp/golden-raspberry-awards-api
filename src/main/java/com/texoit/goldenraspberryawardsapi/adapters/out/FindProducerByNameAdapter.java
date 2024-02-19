package com.texoit.goldenraspberryawardsapi.adapters.out;

import com.texoit.goldenraspberryawardsapi.adapters.out.repository.ProducerRepository;
import com.texoit.goldenraspberryawardsapi.adapters.out.repository.mapper.ProducerEntityMapper;
import com.texoit.goldenraspberryawardsapi.application.core.domain.Producer;
import com.texoit.goldenraspberryawardsapi.application.ports.out.FindProducerByNameOutputPort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class FindProducerByNameAdapter implements FindProducerByNameOutputPort {

    private final ProducerRepository producerRepository;
    private final ProducerEntityMapper producerEntityMapper;

    public FindProducerByNameAdapter(ProducerRepository producerRepository, ProducerEntityMapper producerEntityMapper) {
        this.producerRepository = producerRepository;
        this.producerEntityMapper = producerEntityMapper;
    }

    @Override
    public Optional<Producer> find(String name) {
        var producerEntity = producerRepository.findByName(name);
        return producerEntity.map(producerEntityMapper::toProducer);
    }
}
