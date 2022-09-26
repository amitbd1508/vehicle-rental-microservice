package com.carreservation.catalogservice;

import com.carreservation.catalogservice.service.VehicleService;
import com.carreservation.catalogservice.service.impl.VehicleServiceImp;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@RunnerWith(SpringRunner.class)
public class ServiceTest {

    @TestConfiguration
    static class ServiceTestContextConfiguration {
        @Bean
        public VehicleService vehicleService() {
            return new VehicleServiceImp();
        }
    }

}
