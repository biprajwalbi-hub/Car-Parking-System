package com.smartparking.model;

public sealed interface Vehicle permits Car, Bike {
    String registration();
}
