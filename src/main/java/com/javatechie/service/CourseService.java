package com.javatechie.service;

import com.javatechie.dto.Course;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;

@Service  //can use @Component too
public class CourseService {
    //H2 in memory db this is designed for testing purpose not to keep the data for long time.

    private List<Course> courseDatabase= new ArrayList<>();
    //create course object to db -> POST
    public Course onboardNewCourse(Course course) {
        course.setCourseId(new Random().nextInt(4000));
        courseDatabase.add(course);
        return  course;
    }
// load all the courses from the db -> GET
    public List<Course> viewAllCourses() {
        return courseDatabase;
    }
// filter course by courseId -> GET
    public Course findCourseById(Integer courseId) {
        return courseDatabase.stream().filter(course -> course.getCourseId()==courseId).findFirst().orElse(null);
    }
    //delete course -> DELETE
    public void deleteCourseById(int courseId) {
    Course course = findCourseById(courseId);
    courseDatabase.remove(course);
    }

    //update the course -> PUT
    public Course updateCourse(int courseId,Course course) {
        Course existingCourse = findCourseById(courseId);
        courseDatabase.set(courseDatabase.indexOf(existingCourse),course);
        return course;
    }
}
