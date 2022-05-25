package com.HDFC.furnitureLoan.Service;

import java.util.List;

import com.HDFC.furnitureLoan.model.Cibil;
import com.HDFC.furnitureLoan.model.CompanyBasicDetail;

public interface LoanBasicDetailService {

	void saveBasicDetail(CompanyBasicDetail detail);

	CompanyBasicDetail getBasicDetailOfSingleCompany(int cid);

	List<CompanyBasicDetail> getBasicDetailOfAllCompany();

	CompanyBasicDetail updateBasicDetail(int companyId, Cibil cibil);

}
