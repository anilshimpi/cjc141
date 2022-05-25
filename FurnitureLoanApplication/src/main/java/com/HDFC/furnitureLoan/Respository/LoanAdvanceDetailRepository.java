package com.HDFC.furnitureLoan.Respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.HDFC.furnitureLoan.model.CompanyAdvanceDetail;

@Repository
public interface LoanAdvanceDetailRepository extends JpaRepository<CompanyAdvanceDetail, Integer> {

}
