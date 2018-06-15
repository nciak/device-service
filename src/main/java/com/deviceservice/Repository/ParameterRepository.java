package com.deviceservice.Repository;

import com.deviceservice.Model.Parameter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ParameterRepository extends JpaRepository<Parameter, Long> {

    @Query("SELECT p FROM Parameter p WHERE p.device.id = :deviceId")
    List<Parameter> findParametersByDeviceId(@Param("deviceId") Long deviceId);

}
