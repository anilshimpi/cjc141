package com.HDFC.furnitureLoan.Respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.HDFC.furnitureLoan.model.City;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {

	List<City> findAllByStateId(int stateId);

}
