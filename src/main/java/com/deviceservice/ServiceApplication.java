package com.deviceservice;

import com.deviceservice.Model.Category;
import com.deviceservice.Model.Device;
import com.deviceservice.Repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class ServiceApplication {

	public static void main(String[] args) {


		SpringApplication.run(ServiceApplication.class, args);




	}


}
