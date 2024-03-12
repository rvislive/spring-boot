package springboot.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import springboot.entity.Department;
import springboot.expectation.DepartmentNotFoundException;
import springboot.repository.DepartmentRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DepartmentServiceImplTest {

    @Autowired
    private DepartmentService departmentService;

    @MockBean
    private DepartmentRepository departmentRepository;

    @BeforeEach
    void setUp() {
        Department department = Department.builder()
                .departmentAddress("Dhanbad")
                .departmentCode("IT-01")
                .departmentName("IT")
                .departmentId(1L)
                .build();

        Mockito.when(departmentRepository.getDepartmentByDepartmentNameIgnoreCase("IT")).thenReturn(department);
    }


    @Test
    @DisplayName("Get data based on pre-defined in the builder")
    public void validateDepartmentNameBeforeFound() {
        String departmentName = "IT";
        Department found = departmentService.getDepartmentByName(departmentName);
        assertEquals(departmentName, found.getDepartmentName());
    }
}