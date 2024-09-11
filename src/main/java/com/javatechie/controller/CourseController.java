package com.javatechie.controller;

import com.javatechie.dto.Course;
import com.javatechie.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {
    private CourseService courseService;
    //constructor injection
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }
    @PostMapping
    public ResponseEntity<?> addCourse(@RequestBody Course course) {
   Course newCourse =  courseService.onboardNewCourse(course);
    return new ResponseEntity<>(newCourse, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<?> findAllCourses() {
        return new ResponseEntity<>(courseService.viewAllCourses(),HttpStatus.OK);
    }

    @GetMapping("/search/request")
    public ResponseEntity<?> findCourseById(@RequestParam(required = false) Integer courseId) {
        return  new ResponseEntity<>(courseService.findCourseById(courseId),HttpStatus.OK);
    }
    @DeleteMapping("{courseId}")
    public ResponseEntity<?> deleteCourseById(@PathVariable Integer courseId) {
        courseService.deleteCourseById(courseId);
        return new ResponseEntity<>("",HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{courseId}")
    public ResponseEntity<?> updateCourse(@PathVariable int courseId, Course course) {
        return new ResponseEntity<>(courseService.updateCourse(courseId, course),HttpStatus.OK);
    }
}
