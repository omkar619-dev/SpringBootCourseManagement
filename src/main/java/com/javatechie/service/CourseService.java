package com.javatechie.service;

import com.javatechie.dao.CourseDao;
import com.javatechie.dto.Course;
import com.javatechie.dto.CourseRequestDTO;
import com.javatechie.dto.CourseResponseDTO;
import com.javatechie.entity.CourseEntity;
import com.javatechie.util.AppUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.management.RuntimeErrorException;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service  //can use @Component too
@AllArgsConstructor
public class CourseService {
    //H2 in memory db this is designed for testing purpose not to keep the data for long time.

    private CourseDao courseDao;


    //create course object to db -> POST
    public CourseResponseDTO onboardNewCourse(CourseRequestDTO courseRequestDTO) {
        CourseEntity courseEntity = AppUtils.mapDTOToEntity(courseRequestDTO);
        CourseEntity entity = courseDao.save(courseEntity);
        CourseResponseDTO courseResponseDTO = AppUtils.mapEntityToDTO(entity);
        courseResponseDTO.setCourseUniqueCode(UUID.randomUUID().toString().split("-")[0]);
        return courseResponseDTO;
//        course.setCourseId(new Random().nextInt(4000));
//        courseDatabase.add(course);
//        return  course;
    }
// load all the courses from the db -> GET
    public List<CourseResponseDTO> viewAllCourses() {
        Iterable<CourseEntity> courseEntities = courseDao.findAll();
        return StreamSupport.stream(courseEntities.spliterator(), false)
                .map(AppUtils::mapEntityToDTO)
                .collect(Collectors.toList());

//        return courseDatabase;
    }
// filter course by courseId -> GET
    public CourseResponseDTO findCourseById(Integer courseId) {
        CourseEntity  courseEntity = courseDao.findById(courseId).orElseThrow( ()-> new RuntimeException(courseId + " is not valid"));
        return AppUtils.mapEntityToDTO(courseEntity);
    }
    //delete course -> DELETE
    public void deleteCourseById(int courseId) {
    courseDao.deleteById(courseId);
    }

    //update the course -> PUT
    public CourseResponseDTO updateCourse(int courseId,CourseRequestDTO course) {

        CourseEntity ExistingCourseEntity = courseDao.findById(courseId).orElseThrow( ()-> new RuntimeException(courseId + " is not valid"));

        ExistingCourseEntity.setName(course.getName());
        ExistingCourseEntity.setTrainerName(course.getTrainerName());
        ExistingCourseEntity.setDuration(course.getDuration());
        ExistingCourseEntity.setStartDate(course.getStartDate());
        ExistingCourseEntity.setCourseType(course.getCourseType());
        ExistingCourseEntity.setFees(course.getFees());
        ExistingCourseEntity.setCertificateAvailable(course.isCertificateAvailable());
        ExistingCourseEntity.setDescription(course.getDescription());
        CourseEntity updatedCourseEntity = courseDao.save(ExistingCourseEntity);
        return AppUtils.mapEntityToDTO(updatedCourseEntity);

//        Course existingCourse = findCourseById(courseId);
//        courseDatabase.set(courseDatabase.indexOf(existingCourse),course);
//        return course;
    }
}
