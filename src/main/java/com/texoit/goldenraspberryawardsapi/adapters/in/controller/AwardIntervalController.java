package com.texoit.goldenraspberryawardsapi.adapters.in.controller;

import com.texoit.goldenraspberryawardsapi.application.ports.in.FindMaxAwardIntervalInputPort;
import com.texoit.goldenraspberryawardsapi.application.ports.in.FindMinAwardIntervalInputPort;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/awardintervals")
public class AwardIntervalController {

    private final FindMinAwardIntervalInputPort findMinAwardIntervalInputPort;
    private final FindMaxAwardIntervalInputPort findMaxAwardIntervalInputPort;

    public AwardIntervalController(FindMinAwardIntervalInputPort findMinAwardIntervalInputPort, FindMaxAwardIntervalInputPort findMaxAwardIntervalInputPort) {
        this.findMinAwardIntervalInputPort = findMinAwardIntervalInputPort;
        this.findMaxAwardIntervalInputPort = findMaxAwardIntervalInputPort;
    }

    @GetMapping
    public ResponseEntity<ModelMap> retrieve(ModelMap map) {
        var minAwardIntervalResponse = findMinAwardIntervalInputPort.findAll();
        var maxAwardIntervalResponse = findMaxAwardIntervalInputPort.findAll();

        map.addAttribute("min", minAwardIntervalResponse);
        map.addAttribute("max", maxAwardIntervalResponse);

        return ResponseEntity.ok(map);
    }

}
