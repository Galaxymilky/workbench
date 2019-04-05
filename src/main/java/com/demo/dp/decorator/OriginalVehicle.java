package com.demo.dp.decorator;

public class OriginalVehicle implements Vehicle {
    @Override
    public void show() {
        System.out.println("The original Vehicle contains");
    }
}
