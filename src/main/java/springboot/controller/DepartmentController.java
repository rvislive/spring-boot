package springboot.controller;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springboot.entity.Department;
import springboot.expectation.DepartmentNotFoundException;
import springboot.service.DepartmentService;

import java.util.List;
import java.util.Optional;

@RestController
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    private final Logger LOGGER = LoggerFactory.getLogger(Department.class);

    @PostMapping("/departments")
    public Department saveDepartment(@Valid @RequestBody Department department) {
        LOGGER.info("Inside from SaveDepartment");
        return departmentService.saveDepartment(department);
    }

    @GetMapping("/departments")
    public List<Department> getDepartment() {
        LOGGER.info("Inside from getDepartment");
        return departmentService.getDepartment();
    }

    @GetMapping("/departments/{id}")
    public Department getDepartmentById(@PathVariable("id") Long departmentId) throws DepartmentNotFoundException {
        LOGGER.info("Inside from getDepartmentById");
        return departmentService.getDepartmentById(departmentId);
    }

    @DeleteMapping("/departments/{id}")
    public String deleteDepartmentById(@PathVariable("id") Long departmentId) {
        LOGGER.info("Inside from deleteDepartmentById");
        return departmentService.deleteDepartmentById(departmentId);
    }

    @PutMapping("/departments/{id}")
    public Department updateDepartmentById(@PathVariable("id") Long departmentId, @RequestBody Department department) throws DepartmentNotFoundException {
        LOGGER.info("Inside from updateDepartmentById");
        return departmentService.updateDepartmentById(departmentId, department);
    }

    @GetMapping("/departments/name/{name}")
    public Department getDepartmentByName(@PathVariable("name") String departmentName) throws DepartmentNotFoundException {
        LOGGER.info("Inside from getDepartmentByName");
        return departmentService.getDepartmentByName(departmentName);
    }
}
