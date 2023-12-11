package com.example.demo.rate;

import com.example.demo.classEmployee.ClassEmployee;
import com.example.demo.classEmployee.ClassEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RateService {

    private final RateRepository rateRepository;
    private final ClassEmployeeRepository classEmployeeRepository;

    @Autowired
    public RateService(RateRepository rateRepository, ClassEmployeeRepository classEmployeeRepository) {
        this.rateRepository = rateRepository;
        this.classEmployeeRepository = classEmployeeRepository;
    }

    public List<Rate> getAllRates() {
        return rateRepository.findAll();
    }

//    public List<Rate> getGroupRates() {
//
//    }

    public void addRate(Integer groupId, Rate rate) {
        ClassEmployee classEmployee = classEmployeeRepository.findById(groupId)
                .orElseThrow(() -> new IllegalStateException("Group with id " + groupId + " does not exist"));

        rate.setClassEmployee(classEmployee);

        rateRepository.save(rate);
    }

    public void deleteRate(Integer rateId) {
        if (rateRepository.existsById(rateId)) {
            rateRepository.deleteById(rateId);
        } else {
            throw new IllegalStateException("Rate with id " + rateId + " does not exist");
        }
    }
}
