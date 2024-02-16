package com.texoit.goldenraspberryawardsapi.adapters.out;

import com.texoit.goldenraspberryawardsapi.adapters.out.repository.ProducerRepository;
import com.texoit.goldenraspberryawardsapi.adapters.out.repository.entity.ProducerEntity;
import com.texoit.goldenraspberryawardsapi.adapters.out.repository.mapper.ProducerEntityMapper;
import com.texoit.goldenraspberryawardsapi.application.core.domain.Producer;
import com.texoit.goldenraspberryawardsapi.application.ports.out.InsertProducerOutputPort;
import org.springframework.stereotype.Component;

@Component
public class InsertProducerAdapter implements InsertProducerOutputPort {

    private final ProducerRepository producerRepository;

    private final ProducerEntityMapper producerEntityMapper;

    public InsertProducerAdapter(ProducerRepository producerRepository, ProducerEntityMapper producerEntityMapper) {
        this.producerRepository = producerRepository;
        this.producerEntityMapper = producerEntityMapper;
    }

    @Override
    public Producer insert(Producer producer) {
        ProducerEntity producerEntity = producerRepository.save(producerEntityMapper.toProducerEntity(producer));
        return producerEntityMapper.toProducer(producerEntity);
    }

}
