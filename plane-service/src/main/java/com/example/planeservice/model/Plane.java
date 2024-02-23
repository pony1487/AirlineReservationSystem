package com.example.planeservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Plane {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int planeId;
    private String model;
    private int capacity;
    private String engineType;
    private String owner;
    private int seatsReserved;

    public Plane() {

    }

    public int getPlaneId() {
        return planeId;
    }

    public Plane(int planeId, String model, int capacity, String engineType, String owner, int seatsReserved) {
        this.planeId = planeId;
        this.model = model;
        this.capacity = capacity;
        this.engineType = engineType;
        this.owner = owner;
        this.seatsReserved = seatsReserved;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getSeatsReserved() {
        return seatsReserved;
    }

    public void setSeatsReserved(int seatsReserved) {
        this.seatsReserved = seatsReserved;
    }

    public boolean hasRoom(){
        return seatsReserved < capacity;
    }

    @Override
    public String toString() {
        return "Plane{" +
                "planeId=" + planeId +
                ", model='" + model + '\'' +
                ", capacity=" + capacity +
                ", engineType='" + engineType + '\'' +
                ", owner='" + owner + '\'' +
                ", seatsReserved=" + seatsReserved +
                '}';
    }
}
