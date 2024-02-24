package springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    public Department getDepartmentByDepartmentName(String departmentName);

    public Department getDepartmentByDepartmentNameIgnoreCase(String departmentName);
}
