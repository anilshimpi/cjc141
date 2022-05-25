package com.HDFC.furnitureLoan.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CompanyAdvanceDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int companyId;
	private float yearsOfEstablishment;
	private String gstNo;
	private int noOfPartners;
	private String cmRemark="Under evaluation";
	private String reqLoanAmmount;
	private String sanctionUploadStatus="Not yet uploaded";
	
	@OneToOne(cascade=CascadeType.MERGE)
	private CompanyBasicDetail basicDetail;
	
	@OneToOne(cascade=CascadeType.ALL)
	private OwnerDetail ownerDetail;
	
	@OneToOne(cascade=CascadeType.ALL)
	private BankDetails bankDetails;
	
	@OneToOne(cascade=CascadeType.ALL)
	private PreviousLoanDetail previousLoanDetail;
	
	@OneToOne(cascade=CascadeType.ALL)
	private GuaranterDetails guaranterDetail;
	
	@OneToOne(cascade=CascadeType.ALL)
	private Document document;	
}
