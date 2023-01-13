package kg.mega.saloon.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kg.mega.saloon.enums.WorkDayEnum;
import kg.mega.saloon.models.dto.ScheduleDto;
import kg.mega.saloon.models.requests.SaveScheduleRequest;
import kg.mega.saloon.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Api(tags = "График")
@RestController
@RequestMapping("api/v1/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService service;

    @PostMapping("/save")
    @ApiOperation("Сохранение")
    ResponseEntity<?> save(@RequestBody ScheduleDto masterSchedule) {

        try {
            return new ResponseEntity<>(service.save(masterSchedule), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/create")
    @ApiOperation("Создание рабочего графика мастера")
    ResponseEntity<?>create(@ModelAttribute SaveScheduleRequest scheduleRequest){
        try {
            return new ResponseEntity<>(service.create(scheduleRequest), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/create#2")
    @ApiOperation("Создание рабочего графика мастера #2")
    ResponseEntity<?>create1(@RequestParam WorkDayEnum workDayEnum,
                             @RequestParam (defaultValue = "09:00") Date startTime,
                             @RequestParam (defaultValue = "18:00") Date endTime){
        try {
            return new ResponseEntity<>(service.create1(workDayEnum, startTime, endTime), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/findById")
    @ApiOperation("Вывод графика по id")
    ResponseEntity<?> findById(@RequestParam Long id) {
        try {
            return new ResponseEntity<>(service.findById(id), HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/findAll")
    @ApiOperation("Вывод всех графиков")
    ResponseEntity<List<ScheduleDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @DeleteMapping("/delete")
    @ApiOperation("Удаление")
    ResponseEntity<?> delete(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(service.delete(id));
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getScheduleByMasterName")
    @ApiOperation("Вывод графика по имени мастера")
    ResponseEntity<?> getScheduleByMasterName(@RequestParam String name) {
        try {
            return new ResponseEntity<>(service.getScheduleByMasterName(name), HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getScheduleByMasterId")
    @ApiOperation("Вывод графика по id мастера")
    ResponseEntity<?> getScheduleByMasterId(@RequestParam Long masterId) {
        try {
            return new ResponseEntity<>(service.getScheduleByMasterId(masterId), HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}