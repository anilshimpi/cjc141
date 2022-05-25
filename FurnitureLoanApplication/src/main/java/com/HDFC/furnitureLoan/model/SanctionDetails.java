package com.HDFC.furnitureLoan.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SanctionDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int companyId;
	private String companyName;
	private String ownerName;
	private String loanAmount;
	private String emi;
	private int tenure;
	@Override
	public String toString() {
		return "companyName : " + companyName + "\n ownerName : " + ownerName+ 
				"\n loanAmount : " + loanAmount + " \n emi : " + emi + 
				" \n tenure : " + tenure;
	}
	
	
}
