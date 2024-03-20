package com.texoit.goldenraspberryawardsapi.adapters.out.producer;

import com.texoit.goldenraspberryawardsapi.adapters.out.producer.repository.ProducerRepository;
import com.texoit.goldenraspberryawardsapi.adapters.out.producer.repository.mapper.ProducerEntityMapper;
import com.texoit.goldenraspberryawardsapi.application.core.domain.producer.Producer;
import com.texoit.goldenraspberryawardsapi.application.ports.out.producer.InsertProducerOutputPort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class InsertProducerAdapter implements InsertProducerOutputPort {

    private final ProducerRepository producerRepository;

    private final ProducerEntityMapper producerEntityMapper;

    public InsertProducerAdapter(ProducerRepository producerRepository, ProducerEntityMapper producerEntityMapper) {
        this.producerRepository = producerRepository;
        this.producerEntityMapper = producerEntityMapper;
    }

    @Override
    @Transactional
    public Producer insert(Producer producer) {
        var producerEntity = producerRepository.saveAndFlush(producerEntityMapper.toProducerEntity(producer));
        producerRepository.refresh(producerEntity);
        return producerEntityMapper.toProducer(producerEntity);
    }

}
