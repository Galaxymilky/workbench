package com.demo.dp.decorator;

public class DecoratorPattern {
    public static void main(String[] args) {

        Vehicle vehicle = new OriginalVehicle();

        Vehicle myVehicle = new VehicleDecoratorDoor(new VehicleDecoratorEngine(new VehicleDecoratorGearbox(vehicle, "自动"), "93#汽油"));

        myVehicle.show();
    }
}
