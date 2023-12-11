package com.example.demo.classEmployee;

import com.example.demo.employee.EmployeeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/group")
public class ClassEmployeeController {
    private final ClassEmployeeService classEmployeeService;

    @Autowired
    public ClassEmployeeController(ClassEmployeeService classEmployeeService) {
        this.classEmployeeService = classEmployeeService;
    }

    @GetMapping
    public List<ClassEmployee> GetClassEmployee() {
        return classEmployeeService.getGroups();
    }

    @PostMapping
    public void addGroup(@RequestBody ClassEmployee classEmployee) {
        classEmployeeService.addGroup(classEmployee);
    }

    @DeleteMapping(path = "{groupId}")
    public void deleteEmployee(@PathVariable("groupId") Integer groupId) {
        classEmployeeService.deleteGroup(groupId);
    }

    @PutMapping(path = "{groupId}")
    public void updateGroup(
            @PathVariable("groupId") Integer groupId,
            @RequestBody ClassEmployee updatedClassEmployee) {
        classEmployeeService.updateGroup(groupId, updatedClassEmployee);
    }
}
