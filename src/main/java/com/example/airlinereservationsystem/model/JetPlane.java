package com.example.airlinereservationsystem.model;

public class JetPlane extends Plane {
    private final int planeId;
    private final String model;
    private final int capacity;
    private final String engine_type;
    private final String owner;

    public JetPlane(int planeId, String model, int capacity, String engine_type, String owner) {
        this.planeId = planeId;
        this.model = model;
        this.capacity = capacity;
        this.engine_type = engine_type;
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "JetPlane{" +
                "planeId=" + planeId +
                ", model='" + model + '\'' +
                ", capacity=" + capacity +
                ", engine_type='" + engine_type + '\'' +
                ", owner='" + owner + '\'' +
                '}';
    }
}
