package com.texoit.goldenraspberryawardsapi.adapters.in.controller;

import com.texoit.goldenraspberryawardsapi.adapters.in.controller.mapper.ProducerMapper;
import com.texoit.goldenraspberryawardsapi.adapters.in.controller.request.ProducerRequest;
import com.texoit.goldenraspberryawardsapi.adapters.in.controller.response.ProducerResponse;
import com.texoit.goldenraspberryawardsapi.adapters.out.repository.ProducerRepository;
import com.texoit.goldenraspberryawardsapi.adapters.out.repository.mapper.ProducerEntityMapper;
import com.texoit.goldenraspberryawardsapi.application.ports.in.InsertProducerInputPort;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/producers")
public class ProducerController {

    private final InsertProducerInputPort insertProducerInputPort;
    private final ProducerMapper producerMapper;
    private final ProducerRepository producerRepository;
    private final ProducerEntityMapper producerEntityMapper;

    public ProducerController(InsertProducerInputPort insertProducerInputPort, ProducerMapper producerMapper, ProducerRepository producerRepository, ProducerEntityMapper producerEntityMapper) {
        this.insertProducerInputPort = insertProducerInputPort;
        this.producerMapper = producerMapper;
        this.producerRepository = producerRepository;
        this.producerEntityMapper = producerEntityMapper;
    }

    @PostMapping
    public ResponseEntity<ProducerResponse> insert(@Valid @RequestBody ProducerRequest producerRequest, UriComponentsBuilder uriBuilder) {
        var producer = insertProducerInputPort.insert(producerMapper.toProducer(producerRequest));
        var uri = uriBuilder.path("api/v1/producers/{id}").buildAndExpand(producer.getId()).toUri();

        return ResponseEntity.created(uri).body(producerMapper.toProducerResponse(producer));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProducerResponse> retrieve(@PathVariable UUID id) {
        var producerResponse = producerMapper.toProducerResponse(producerEntityMapper.toProducer(producerRepository.getReferenceById(id)));
        return ResponseEntity.ok(producerResponse);
    }

}
