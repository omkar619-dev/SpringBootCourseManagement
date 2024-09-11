package com.javatechie.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    private int courseId;
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
