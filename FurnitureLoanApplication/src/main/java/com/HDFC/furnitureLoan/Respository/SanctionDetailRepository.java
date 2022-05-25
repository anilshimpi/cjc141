package com.HDFC.furnitureLoan.Respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.HDFC.furnitureLoan.model.SanctionDetails;

@Repository
public interface SanctionDetailRepository extends JpaRepository<SanctionDetails, Integer> {

}
