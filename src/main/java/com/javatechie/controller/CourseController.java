package com.javatechie.controller;

import com.javatechie.dto.Course;
import com.javatechie.dto.CourseRequestDTO;
import com.javatechie.dto.CourseResponseDTO;
import com.javatechie.dto.ServiceResponse;
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
    public ServiceResponse<CourseResponseDTO> addCourse(@RequestBody CourseRequestDTO course) {
   CourseResponseDTO newCourse =  courseService.onboardNewCourse(course);
    return new ServiceResponse<>(HttpStatus.CREATED,newCourse);
    }
    @GetMapping
    public ServiceResponse<List<CourseResponseDTO>> findAllCourses() {
       List<CourseResponseDTO> courseResponseDTOS = courseService.viewAllCourses();
       return new ServiceResponse<>(HttpStatus.OK,courseResponseDTOS);
    }

    @GetMapping("/search/request")
    public ServiceResponse<CourseResponseDTO> findCourseUsingRequestParam(@RequestParam(required = false) Integer courseId) {
        CourseResponseDTO responseDTO = courseService.findCourseById(courseId);
        return new ServiceResponse<>(HttpStatus.OK,responseDTO);
    }
    @DeleteMapping("{courseId}")
    public ResponseEntity<?> deleteCourseById(@PathVariable Integer courseId) {
        courseService.deleteCourseById(courseId);
        return new ResponseEntity<>("",HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{courseId}")
    public ServiceResponse<CourseResponseDTO> updateCourse(@PathVariable int courseId, CourseRequestDTO course) {
        CourseResponseDTO courseResponseDTO = courseService.updateCourse(courseId, course);
        return new ServiceResponse<>(HttpStatus.OK,courseResponseDTO);
    }
}
