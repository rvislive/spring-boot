package com.example.BookMyShow.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;


@Getter
@Setter
@Entity
public class Show extends BaseModel {

    @ManyToOne
    private Auditorium auditorium;
    private Date startTime;
    private Date endTime;

    @ManyToOne
    private Movie movie;

    @ElementCollection // element collection is required when we are storing enum values in the array.
    @Enumerated(EnumType.ORDINAL)
    private List<Feature> features;
}
