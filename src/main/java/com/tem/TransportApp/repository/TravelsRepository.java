package com.tem.TransportApp.repository;

import com.tem.TransportApp.domain.Travels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelsRepository extends JpaRepository<Travels,Long> {
}
