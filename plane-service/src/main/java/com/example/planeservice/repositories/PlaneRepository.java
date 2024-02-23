package com.example.planeservice.repositories;

import com.example.planeservice.model.Plane;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlaneRepository extends JpaRepository<Plane, Integer> {

    @Query("SELECT p FROM Plane p WHERE p.planeId=:id")
    public Optional<Plane> findPlaneById(int id);

    @Modifying
    @Transactional
    @Query("Update Plane p SET p.seatsReserved = p.seatsReserved + 1 WHERE p.planeId=:id")
    public void incrementSeatsReserved(int id);

    @Modifying
    @Transactional
    @Query("Update Plane p SET p.seatsReserved = 0 WHERE p.planeId=:id")
    public void resetSeatsReserved(int id);
}
