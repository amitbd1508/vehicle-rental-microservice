package com.carreservation.catalogservice;

import com.carreservation.catalogservice.model.entity.Vehicle;
import com.carreservation.catalogservice.model.entity.VehicleStatus;
import com.carreservation.catalogservice.repository.CatalogRepo;
import com.carreservation.catalogservice.service.VehicleService;
import com.carreservation.catalogservice.service.impl.VehicleServiceImp;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class ServiceTest {
    @TestConfiguration
    static class ServiceTestContextConfiguration {
        @Bean
        public VehicleService vehicleService() {
            return new VehicleServiceImp();
        }
    }
    @Autowired
    private VehicleService vehicleService;

    @MockBean
    private CatalogRepo catalogRepo;
    @Before
    public void setUp() {
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand("Toyota");
        vehicle.setModel("Camry");
        vehicle.setYearOfManufacture("2019");
        vehicle.setPrice(20000.00);
        vehicle.setVehicleStatus(VehicleStatus.AVAILABLE);
        vehicleService.save(vehicle);
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(vehicle);
        Page<Vehicle> vehiclePage = new PageImpl<>(vehicles);
        Mockito.when(catalogRepo.getByBrand(vehicle.getBrand(), Pageable.ofSize(1)))
                .thenReturn(vehiclePage);

        Mockito.when(catalogRepo.getByModel(vehicle.getModel(), Pageable.ofSize(1)))
                .thenReturn(vehiclePage);
    }

    @Test
    public void whenValidBrand_thenVehicleShouldBeFound() {
        String brand = "Toyota";
        Vehicle found = vehicleService.getVehicleByBrand(brand, Optional.of(0),Optional.of(1)).getContent().get(0);
        assertThat(found.getBrand()).isEqualTo(brand);
    }


    @Test
    public void whenValidModel_thenVehicleShouldBeFound() {
        String model = "Camry";
        Vehicle found = vehicleService.getVehicleByModel(model, Optional.of(0),Optional.of(1)).getContent().get(0);
        assertThat(found.getModel()).isEqualTo(model);
    }

}
