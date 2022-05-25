package com.HDFC.furnitureLoan.Respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.HDFC.furnitureLoan.model.CompanyBasicDetail;

@Repository
public interface LoanBasicDetailRepository extends JpaRepository<CompanyBasicDetail, Integer>  {

	Optional<CompanyBasicDetail> findByCompanyId(int companyId);
}
