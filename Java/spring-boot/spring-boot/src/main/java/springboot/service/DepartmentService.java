package springboot.service;

import springboot.entity.Department;
import springboot.expectation.DepartmentNotFoundException;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {

    public Department saveDepartment(Department department);

    public List<Department> getDepartment();

    public Department getDepartmentById(Long departmentId) throws DepartmentNotFoundException;

    public String deleteDepartmentById(Long departmentId);

    public Department updateDepartmentById(Long departmentId, Department department) throws DepartmentNotFoundException;

    public Department getDepartmentByName(String departmentName);
}
