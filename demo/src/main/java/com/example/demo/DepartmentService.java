package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Optional<Department> getDepartmentById(Long id) {
        return departmentRepository.findById(id);
    }

    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public Department updateDepartment(Long id, Department department) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (optionalDepartment.isPresent()) {
            Department existingDepartment = optionalDepartment.get();
            existingDepartment.setName(department.getName());
            existingDepartment.setOfficeNumber(department.getOfficeNumber());
            existingDepartment.setFloor(department.getFloor());
            existingDepartment.setEmail(department.getEmail());
            existingDepartment.setConsultationHours(department.getConsultationHours());
            existingDepartment.setSubjects(department.getSubjects());
            return departmentRepository.save(existingDepartment);
        } else {
            throw new DepartmentNotFoundException("Department not found with id: " + id);
        }
    }

    public void deleteDepartment(Long id) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (optionalDepartment.isPresent()) {
            departmentRepository.deleteById(id);
        } else {
            throw new DepartmentNotFoundException("Department not found with id: " + id);
        }
    }
}
