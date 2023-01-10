package kg.mega.saloon.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kg.mega.saloon.service.MasterScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "График мастера")
@RestController
@RequestMapping("api/v1/masterSchedule")
public class MasterScheduleController {
    @Autowired
    private MasterScheduleService service;

    @PostMapping("/save")
    @ApiOperation("Сохранение")
    ResponseEntity<?> create(@RequestParam Long masterId, @RequestParam List<Long> scheduleIds) {

        try {
            return new ResponseEntity<>(service.create(masterId, scheduleIds), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/get")
    @ApiOperation("Получение графика по id мастера")
    ResponseEntity<?> getSchedule(@RequestParam Long masterId) {

        try {
            return new ResponseEntity<>(service.getSched(masterId), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
}
