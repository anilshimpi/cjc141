package com.HDFC.furnitureLoan.Respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.HDFC.furnitureLoan.model.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {

}
