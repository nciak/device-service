package com.deviceservice.Model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "PARAMETER")
public class Parameter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;

    private String name;

    private String value;

    @ManyToOne
    private Device device;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    @Override
    public String toString() {
        return "Parameter{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", device=" + device +
                '}';
    }
}
