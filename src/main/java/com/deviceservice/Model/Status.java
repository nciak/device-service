package com.deviceservice.Model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "STATUS")
public class Status {



        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        @Column(name = "VALUE")
        private String value;

        @OneToMany(targetEntity=Device.class,mappedBy="status",cascade={CascadeType.ALL},orphanRemoval=true)
        private List<Device> devices;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    @Override
    public String toString() {
        return "Status{" +
                "id=" + id +
                ", value='" + value + '\'' +
                ", devices=" + devices +
                '}';
    }
}
