package springboot.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import springboot.entity.Department;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setUp() {
        Department department = Department.builder()
                .departmentAddress("Dhanbad")
                .departmentCode("ME-01")
                .departmentName("Mechanical Engineering")
                .build();

        entityManager.persist(department);
    }

    @Test
    public void validateDepartmentIdBeforeReturn() {
        String departmentName = "Mechanical Engineering";
        Department department = departmentRepository.findById(1L).get();

        assertEquals(department.getDepartmentName(), departmentName);
    }
}