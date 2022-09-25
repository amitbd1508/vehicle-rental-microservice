package com.carreservation.catalogservice.controller;


import com.carreservation.catalogservice.entity.Vehicle;
import com.carreservation.catalogservice.repository.CatalogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carcatalog")
public class CatalogController {

    @Autowired
    private CatalogRepo catalogRepo;

    @GetMapping("/all")
    public List<Vehicle> getAllVehicle(){
        return catalogRepo.findAll();
    }


    //@RequestMapping(value = "/carcatalog/{brand}/)

//    @RequestMapping(value = "/{brand}", method = RequestMethod.GET)
//    public @ResponseBody List getItem(@RequestParam("brand") String brand) {
//
//        public List<Vehicle> getVehicleByBrand (@PathVariable String brand){
//            return catalogRepo.getByBrand(brand);
//        }

//
//    @GetMapping(value = "/{id}")
//    public Vehicle getVehicleById(@PathVariable String Id){
//        return catalogRepo.getVehicleById(Id);
//    }


    }
