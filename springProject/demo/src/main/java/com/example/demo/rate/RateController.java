package com.example.demo.rate;

import com.example.demo.employee.Employee;
import com.example.demo.employee.EmployeeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/rating")
public class RateController {
    private final RateService rateService;

    @Autowired
    public RateController(RateService rateService) {
        this.rateService = rateService;
    }

    @GetMapping
    public List<Rate> getAllRates() {
        return rateService.getAllRates();
    }

    @PostMapping
    public void addRate(
            @RequestParam("groupId") Integer groupId,
            @RequestBody Rate rate) {
        rateService.addRate(groupId, rate);
    }

    @DeleteMapping(path = "{rateId}")
    public void deleteRate(@PathVariable("rateId") Integer rateId) {
        rateService.deleteRate(rateId);
    }
}
