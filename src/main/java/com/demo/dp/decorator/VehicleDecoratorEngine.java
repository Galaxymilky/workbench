package com.demo.dp.decorator;

public class VehicleDecoratorEngine implements Vehicle {

    private Vehicle vehicle;

    public VehicleDecoratorEngine(Vehicle vehicle) {
        super();
        this.vehicle = vehicle;
    }

    private String oilType;

    public VehicleDecoratorEngine(Vehicle vehicle, String oilType) {
        super();
        this.oilType = oilType;
        this.vehicle = vehicle;
    }

    @Override
    public void show() {
        vehicle.show();
        if (oilType == null || "".equals(oilType)) {
            System.out.println("an engine");
            return;
        }
        System.out.println("an "+ oilType +" engine");
    }
}
