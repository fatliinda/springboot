package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.NoSuchElementException;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }

    public Course saveCourse(Course course) {
        if (course == null) {
            throw new IllegalArgumentException("Course cannot be null");
        }
        return courseRepository.save(course);
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    public Course updateCourse(Long id, Course course) {
        if (course == null) {
            throw new IllegalArgumentException("Course cannot be null");
        }

        Optional<Course> optionalExistingCourse = courseRepository.findById(id);
        if (optionalExistingCourse.isPresent()) {
            Course existingCourse = optionalExistingCourse.get();
            existingCourse.setTitle(course.getTitle());
            existingCourse.setCredits(course.getCredits());
            existingCourse.setSchedule(course.getSchedule());
            existingCourse.setProfessor(course.getProfessor());
            existingCourse.setLocation(course.getLocation());
            return courseRepository.save(existingCourse);
        } else {
            throw new NoSuchElementException("Course not found with ID: " + id);
        }
    }
}
