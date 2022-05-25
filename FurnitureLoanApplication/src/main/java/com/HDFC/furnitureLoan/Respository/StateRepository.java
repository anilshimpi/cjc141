package com.HDFC.furnitureLoan.Respository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.HDFC.furnitureLoan.model.Country;
import com.HDFC.furnitureLoan.model.State;

@Repository
public interface StateRepository extends JpaRepository<State ,Integer> {

	 //List<State> findByCountryKey(int countryId);
	List<State> findAllByCountryId(int countryId);
	
	

}
