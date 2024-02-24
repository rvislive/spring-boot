package springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.entity.Department;
import springboot.expectation.DepartmentNotFoundException;
import springboot.repository.DepartmentRepository;


import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> getDepartment() {
        return departmentRepository.findAll();
    }

    @Override
    public Department getDepartmentById(Long departmentId) throws DepartmentNotFoundException {
        Optional<Department> department = departmentRepository.findById(departmentId);

        if(!department.isPresent()) {
            throw new DepartmentNotFoundException("Department is not available.");
        }

        return department.get();
    }

    @Override
    public String deleteDepartmentById(Long departmentId) {
        departmentRepository.deleteById(departmentId);
        return "Deleted";
    }

    @Override
    public Department updateDepartmentById(Long departmentId, Department department) throws DepartmentNotFoundException {
        Optional<Department> isDepData = departmentRepository.findById(departmentId);

        if(!isDepData.isPresent()) {
            throw new DepartmentNotFoundException("No data is to update.");
        }

        Department depDb = isDepData.get();

        if(Objects.nonNull(department.getDepartmentName()) && !"".equalsIgnoreCase(department.getDepartmentName())) {
            depDb.setDepartmentName(department.getDepartmentName());
        }

        if(Objects.nonNull(department.getDepartmentCode()) && !"".equalsIgnoreCase(department.getDepartmentCode())) {
            depDb.setDepartmentCode(department.getDepartmentCode());
        }

        if(Objects.nonNull(department.getDepartmentAddress()) && !"".equalsIgnoreCase(department.getDepartmentAddress())) {
            depDb.setDepartmentAddress(department.getDepartmentAddress());
        }

        return departmentRepository.save(depDb);
    }

    @Override
    public Department getDepartmentByName(String departmentName) {
        return departmentRepository.getDepartmentByDepartmentNameIgnoreCase(departmentName);
    }


}
