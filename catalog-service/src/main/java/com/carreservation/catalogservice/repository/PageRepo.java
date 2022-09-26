package com.carreservation.catalogservice.repository;

import com.carreservation.catalogservice.entity.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;

@Repository
public interface PageRepo extends PagingAndSortingRepository<Vehicle, String> {

        static Page<Vehicle> findAll(Pageable paging) {
             return PageRepo.findAll(paging);
        }
}