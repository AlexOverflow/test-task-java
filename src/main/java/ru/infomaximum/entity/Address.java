package ru.infomaximum.entity;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.opencsv.bean.CsvBindByName;

import java.util.Objects;

@JacksonXmlRootElement(localName = "item")
public class Address {
    @JacksonXmlProperty(isAttribute = true)
    @CsvBindByName(column = "city")
    private String city;
    @JacksonXmlProperty(isAttribute = true)
    @CsvBindByName(column = "street")
    private String street;
    @JacksonXmlProperty(isAttribute = true)
    @CsvBindByName(column = "house")
    private int house;
    @JacksonXmlProperty(isAttribute = true)
    @CsvBindByName(column = "floor")
    private int floor;

    public Address(String city, String street, int house, int floor) {
        this.city = city;
        this.street = street;
        this.house = house;
        this.floor = floor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return house == address.house && floor == address.floor && Objects.equals(city, address.city) && Objects.equals(street, address.street);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, street, house, floor);
    }

    public Address() {}

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouse() {
        return house;
    }

    public void setHouse(int house) {
        this.house = house;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    @Override
    public String toString() {
        return  "город: " + city +
                ", улица: " + street +
                ", дом: " + house +
                ", этаж: " + floor;
    }
}
