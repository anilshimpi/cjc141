package com.HDFC.furnitureLoan.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HDFC.furnitureLoan.Respository.LoanAdvanceDetailRepository;
import com.HDFC.furnitureLoan.Service.LoanAdvanceDetailService;
import com.HDFC.furnitureLoan.model.CompanyAdvanceDetail;
import com.HDFC.furnitureLoan.model.CompanyBasicDetail;

@Service
public class LoanAdvanceDetailServiceImpl implements LoanAdvanceDetailService{
	
	@Autowired
	LoanAdvanceDetailRepository lar;

	@Override
	public void saveAdvanceDetail(CompanyAdvanceDetail advDetail) {

		lar.save(advDetail);
		
	}

	@Override
	public CompanyAdvanceDetail getAdvanceDetailOfSingleCompany(int cid) {
		Optional<CompanyAdvanceDetail> advDetail= lar.findById(cid);
		return advDetail.get();
	}
//	public ApplicantAdvanceDetail getAdvanceDetailOfSingleApplicant(int cid) {
//		ApplicantAdvanceDetail advDetail= lar.getById(cid);
//		return advDetail;
//	}

	@Override
	public List<CompanyAdvanceDetail> getAdvanceDetailOfAllCompany() {
		
		return lar.findAll();
	}

	@Override
	public CompanyAdvanceDetail updateAdvanceDetail(int companyId, String cmRemark) {
		
		Optional<CompanyAdvanceDetail> optionalCompanyAdvanceDetail = lar.findById(companyId);
		
		CompanyAdvanceDetail companyAdvanceDetail = optionalCompanyAdvanceDetail.get();
		 companyAdvanceDetail.setCmRemark(cmRemark);
		 lar.save(companyAdvanceDetail);
		 return companyAdvanceDetail;
		
	}

	@Override
	public CompanyAdvanceDetail updateSanctionUploadStatus(int companyId, String status) {
		
		Optional<CompanyAdvanceDetail> optionalCompanyAdvanceDetail = lar.findById(companyId);
		
		CompanyAdvanceDetail companyAdvanceDetail = optionalCompanyAdvanceDetail.get();
		 companyAdvanceDetail.setSanctionUploadStatus(status);
		 lar.save(companyAdvanceDetail);
		 return companyAdvanceDetail;
	}

}
