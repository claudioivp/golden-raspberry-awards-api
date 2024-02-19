package com.texoit.goldenraspberryawardsapi.adapters.out.repository.mapper;

import com.texoit.goldenraspberryawardsapi.adapters.out.repository.ProducerRepository;
import com.texoit.goldenraspberryawardsapi.adapters.out.repository.entity.ProducerEntity;
import com.texoit.goldenraspberryawardsapi.application.core.domain.Producer;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class MovieProducerEntityMapper {

    @Autowired
    private ProducerRepository producerRepository;

    @Mapping(target = "movies", ignore = true)
    public abstract ProducerEntity toProducerEntity(Producer producer);
    @Mapping(target = "movies", ignore = true)
    public abstract Producer toProducer(ProducerEntity producerEntity);

    @AfterMapping
    public ProducerEntity afterMapping(Producer producer, @MappingTarget ProducerEntity producerEntity) {
        return producerRepository.findById(producer.getId()).orElseThrow();
    }

}