package com.texoit.goldenraspberryawardsapi.adapters.out.movie.repository.mapper;

import com.texoit.goldenraspberryawardsapi.adapters.out.producer.repository.ProducerRepository;
import com.texoit.goldenraspberryawardsapi.adapters.out.producer.repository.entity.ProducerEntity;
import com.texoit.goldenraspberryawardsapi.application.core.config.csv.InvalidBeanFromCsvException;
import com.texoit.goldenraspberryawardsapi.application.core.domain.producer.Producer;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
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
        return producerRepository.findById(producer.getId()).orElseThrow(
                () -> new InvalidBeanFromCsvException(String.format("O produtor com ID {%s} não foi encontrado.", producer.getId()))
        );
    }

}