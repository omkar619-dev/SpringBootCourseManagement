package com.javatechie.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class CourseRequestDTO {

    private String name;
    private String trainerName;
    private String duration;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy")
    private Date startDate;
    private String courseType;
    private  double fees;
    private boolean isCertificateAvailable;
    private String description;
}
