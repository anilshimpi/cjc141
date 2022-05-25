package com.HDFC.furnitureLoan.Service;

import com.HDFC.furnitureLoan.model.EmailSender;
import com.HDFC.furnitureLoan.model.SanctionDetails;

public interface EmailSenderService {

	void mailSend(EmailSender e, SanctionDetails sd, int companyId);

	void mailSendDisburse(EmailSender e,SanctionDetails sd, int companyId);

	//void mailSend(EmailSender e);

}
