package com.HDFC.furnitureLoan.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailSender {
	
	private String fromEmail;
	private String toEmail;
	private String subject = "Loan Status";
	private String textMessage = "-";
	private SanctionDetails sd;

}
