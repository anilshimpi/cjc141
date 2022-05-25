package com.HDFC.furnitureLoan.serviceImpl;

import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.HDFC.furnitureLoan.Respository.LoanAdvanceDetailRepository;
import com.HDFC.furnitureLoan.Respository.SanctionDetailRepository;
import com.HDFC.furnitureLoan.Service.EmailSenderService;
import com.HDFC.furnitureLoan.model.CompanyAdvanceDetail;
import com.HDFC.furnitureLoan.model.EmailSender;
import com.HDFC.furnitureLoan.model.SanctionDetails;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class EmailSenderServiceImpl implements EmailSenderService {

	@Autowired
	JavaMailSender mailSender;
	@Autowired
	SanctionDetailRepository sdr;
	@Autowired
	LoanAdvanceDetailRepository lar;
	@Override
	public void mailSend(EmailSender e,  SanctionDetails sd, int companyId) {
		
	
		
		Optional<CompanyAdvanceDetail> optionalCompanyAdvanceDetail = lar.findById(companyId);
		CompanyAdvanceDetail companyAdvanceDetail = optionalCompanyAdvanceDetail.get();
		String companyEmail=companyAdvanceDetail.getBasicDetail().getCompanyEmail();
		e.setToEmail(companyEmail);
		
		sd.setCompanyId(companyAdvanceDetail.getCompanyId());
		sd.setCompanyName(companyAdvanceDetail.getBasicDetail().getCompanyName());
		sd.setOwnerName(companyAdvanceDetail.getOwnerDetail().getOwnerName());
		log.info(sd.getOwnerName());
		log.info(e.getToEmail());
		
		e.setSd(sd);
		e.setTextMessage("Your loan request has been Approved!!! \n\n Loan Details are as follows :\n\n"+e.getSd().toString()
				+"\n\nCheck Attachment Sanction document.\nSign the sanction letter and submit it to RE Desk to Avail loan amount!"
				+ "\n\n\nFor any inconsistencies contact - abcfutureloans@gmail.com ");
		
		
		MimeMessage message=mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			
			helper.setFrom(e.getFromEmail());
			helper.setTo(e.getToEmail());
			helper.setSubject(e.getSubject());
			helper.setText(e.getTextMessage());
				
			FileSystemResource file = new FileSystemResource("E:\\CJC notes\\sample Sanction Letter.jpg");
			//E:\CJC\FinalProjectTasks\FurnitureLoanApplication\src\main\resources\static
			helper.addAttachment(file.getFilename(), file);
			
		} catch (MessagingException err) {
			
			log.error("Error in MimeMessageHelper."+err.getStackTrace());
		}
		
		mailSender.send(message);
		log.info("Email Sent Successfully.");
		
		sdr.save(e.getSd());
		
	}
	
	
	@Override
	public void mailSendDisburse(EmailSender e,SanctionDetails sd, int companyId) {
		
		e.setTextMessage("Your loan amount has been transferred!!! "+
				"\n\n\nFor any inconsistencies contact - abcfutureloans@gmail.com");
		Optional<CompanyAdvanceDetail> optionalCompanyAdvanceDetail = lar.findById(companyId);
		
		CompanyAdvanceDetail companyAdvanceDetail = optionalCompanyAdvanceDetail.get();
		
		String companyEmail=companyAdvanceDetail.getBasicDetail().getCompanyEmail();
		
		e.setToEmail(companyEmail);
		
			SimpleMailMessage msg = new SimpleMailMessage();
			msg.setFrom(e.getFromEmail());
			msg.setTo(e.getToEmail());
			msg.setSubject("Loan Amount Disbursement");
			msg.setText(e.getTextMessage());
			mailSender.send(msg);
		}
		
	}


