package com.tem.TransportApp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(name = "_travels")
public class Travels {
    @Id
    private Long id;
    private String price;
    private String noOfSitters;

//    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "vehicle_and_Travels",
            joinColumns = @JoinColumn(name = "travels_id",referencedColumnName ="id"),
            inverseJoinColumns = @JoinColumn(name = "vehicle_id", referencedColumnName = "id")
    )
    private Set<Vehicle> vehicle = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "travels_and_location",
            joinColumns = @JoinColumn(name = "travels_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "location_id", referencedColumnName = "id")
    )
    private Set<Location> locations = new HashSet<>();

}
