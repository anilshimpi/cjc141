package com.HDFC.furnitureLoan.Service;

import java.util.List;

import com.HDFC.furnitureLoan.model.CompanyAdvanceDetail;

public interface LoanAdvanceDetailService {

	void saveAdvanceDetail(CompanyAdvanceDetail advDetail);

	CompanyAdvanceDetail getAdvanceDetailOfSingleCompany(int cid);

	List<CompanyAdvanceDetail> getAdvanceDetailOfAllCompany();

	CompanyAdvanceDetail updateAdvanceDetail(int companyId, String cmRemark);

	CompanyAdvanceDetail updateSanctionUploadStatus(int companyId, String status);

}
