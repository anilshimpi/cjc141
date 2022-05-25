
package com.HDFC.furnitureLoan.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HDFC.furnitureLoan.Respository.LoanBasicDetailRepository;
import com.HDFC.furnitureLoan.Service.LoanBasicDetailService;
import com.HDFC.furnitureLoan.model.Cibil;
import com.HDFC.furnitureLoan.model.CompanyBasicDetail;

@Service
public class LoanBasicDetailServiceImpl implements LoanBasicDetailService{

		@Autowired
		LoanBasicDetailRepository lbr;

		@Override
		public void saveBasicDetail(CompanyBasicDetail detail) {
			
			lbr.save(detail);	
		}

		@Override
		public CompanyBasicDetail getBasicDetailOfSingleCompany(int cid) {
			
			Optional<CompanyBasicDetail> basicDetail= lbr.findById(cid);
			return basicDetail.get();
		}

		@Override
		public List<CompanyBasicDetail> getBasicDetailOfAllCompany() {
			
			return lbr.findAll();
			
		}

		@Override
		public CompanyBasicDetail updateBasicDetail(int companyId, Cibil cibil) {
			
			Optional<CompanyBasicDetail> optionalCompanyBasicDetail = lbr.findByCompanyId(companyId);
			
			CompanyBasicDetail companyBasicDetail = optionalCompanyBasicDetail.get();
			companyBasicDetail.setCb(cibil);
			lbr.save(companyBasicDetail);
			
			return companyBasicDetail;
			
			
			
		}

}


