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
public class GuaranterDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int gid;
	private String gname;
	private String mobNo;
	private String email;
	private String guarantorOcccupation;
	@OneToOne(cascade=CascadeType.ALL)
	private Address gaddress;
	private String cmRemark;
}
