package com.demo.dp.decorator;

public class VehicleDecoratorDoor implements Vehicle {

    private Vehicle vehicle;

    public VehicleDecoratorDoor(Vehicle vehicle) {
        super();
        this.vehicle = vehicle;
    }

    @Override
    public void show() {
        vehicle.show();
        System.out.println("a door");
    }
}
