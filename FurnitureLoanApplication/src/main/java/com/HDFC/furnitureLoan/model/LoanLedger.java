package com.HDFC.furnitureLoan.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanLedger {
	
	private int ledgerId;
	private String Date;
	private String transaction;
	private String principal;
	private String interest;
	private String penalty;
	private String balancePrincipal;
	private String balncePenalty;
	private String paymentMode;
	
}
