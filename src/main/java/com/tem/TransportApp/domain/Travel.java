package com.tem.TransportApp.domain;

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
@Table(name = "_travel")
public class Travel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String price;
    private String noOfSitters;

//    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "vehicle_and_Travel",
            joinColumns = @JoinColumn(name = "travel_id",referencedColumnName ="id"),
            inverseJoinColumns = @JoinColumn(name = "vehicle_id", referencedColumnName = "id")
    )
    private Set<Vehicle> vehicle = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "travel_and_location",
            joinColumns = @JoinColumn(name = "travel_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "location_id", referencedColumnName = "id")
    )
    private Set<Location> locations = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "travel_and_user",
            joinColumns = @JoinColumn(name = "travel_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id")
    )
    private Set<AppUser> users = new HashSet<>();

}
