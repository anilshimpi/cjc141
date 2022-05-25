package com.HDFC.furnitureLoan.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Document {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int document_id;
	private int companyId;
	@Lob
	private byte[] ownerAdhaarCard;
	@Lob
	private byte[] ownerSignature;
	@Lob
	private byte[] companyPanCard;
	@Lob
	private byte[] rentAgriment;
	@Lob
	private byte[] companyPhoto;
	@Lob
	private byte[] bankPassBook;
	@Lob
	private byte[] itrReport;
	@Lob
	private byte[] pdcCheck;
	@Lob
	private byte[] cancelCheck;
	@Lob
	private byte[] certificateOfRegistrationOfIncorporation;
	@Lob
	private byte[] dealerQuotation;
	
	
}
