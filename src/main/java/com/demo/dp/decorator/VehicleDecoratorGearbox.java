package com.demo.dp.decorator;

public class VehicleDecoratorGearbox implements Vehicle {

    private Vehicle vehicle;

    public VehicleDecoratorGearbox(Vehicle vehicle) {
        super();
        this.vehicle = vehicle;
    }

    private String type;

    public VehicleDecoratorGearbox(Vehicle vehicle, String type) {
        super();
        this.type = type;
        this.vehicle = vehicle;
    }

    @Override
    public void show() {
        vehicle.show();
        if (type == null || "".equals(type)) {
            System.out.println("a gearbox");
        } else {
            System.out.println("a " + type + " gearbox");
        }

    }
}
