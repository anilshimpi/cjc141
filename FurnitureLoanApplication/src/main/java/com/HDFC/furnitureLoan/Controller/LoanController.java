package com.HDFC.furnitureLoan.Controller;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.HDFC.furnitureLoan.ExceptionHandler.CompanyNotFoundException;
import com.HDFC.furnitureLoan.Respository.CityRepository;
import com.HDFC.furnitureLoan.Respository.CountryRepository;
import com.HDFC.furnitureLoan.Respository.StateRepository;
import com.HDFC.furnitureLoan.Service.EmailSenderService;
import com.HDFC.furnitureLoan.Service.LoanAdvanceDetailService;
import com.HDFC.furnitureLoan.Service.LoanBasicDetailService;
import com.HDFC.furnitureLoan.model.Cibil;
import com.HDFC.furnitureLoan.model.City;
import com.HDFC.furnitureLoan.model.CompanyAdvanceDetail;
import com.HDFC.furnitureLoan.model.CompanyBasicDetail;
import com.HDFC.furnitureLoan.model.Country;
import com.HDFC.furnitureLoan.model.Document;
import com.HDFC.furnitureLoan.model.EmailSender;
import com.HDFC.furnitureLoan.model.SanctionDetails;
import com.HDFC.furnitureLoan.model.State;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin("*")
public class LoanController {
	@Autowired
	LoanBasicDetailService lbs;
	@Autowired
	LoanAdvanceDetailService las;
	@Autowired
	EmailSenderService  es;
	
	@Autowired
	StateRepository stateRepository;
	@Autowired
	CountryRepository countryRepository;
	@Autowired
	CityRepository cityRepository;
	
	
	@PostMapping(value="/basicDetail")
	public ResponseEntity<CompanyBasicDetail>basicDetil(@RequestBody CompanyBasicDetail detail ){
		log.info("Post company basic Details");
		lbs.saveBasicDetail(detail);
		return new ResponseEntity<CompanyBasicDetail>(detail,HttpStatus.CREATED);
	}
	
//	@GetMapping(value="/getBasicDetailOfSingleApplicant/{cid}")
//	public ApplicantBasicDetail getBasicDetilOfSingleApplicant(@PathVariable int cid) {
//		ApplicantBasicDetail basicDetail = lbs.getBasicDetailOfSingleApplicant(cid);
//		return basicDetail;
//	}
	@GetMapping(value="/getBasicDetailOfSingleCompany/{cid}")
	public ResponseEntity<CompanyBasicDetail> getBasicDetilOfSingleCompany(@PathVariable int cid) {
		log.info("Get basic Details single company");
		try {
		CompanyBasicDetail basicDetail = lbs.getBasicDetailOfSingleCompany(cid);
		return new ResponseEntity<CompanyBasicDetail>(basicDetail,HttpStatus.OK);
		}catch(NoSuchElementException e) {
			throw new CompanyNotFoundException("Company with this id is not Available");
	     }
   }	
	@GetMapping(value="/getBasicDetailOfAllCompany")
	public ResponseEntity< List<CompanyBasicDetail>> getBasicDetilOfAllCompany() {
		log.info("get basic Details of all companies ");
		List<CompanyBasicDetail> basicDetailList = lbs.getBasicDetailOfAllCompany();
		
		if(!basicDetailList.isEmpty())
		return new ResponseEntity<List<CompanyBasicDetail>>(basicDetailList,HttpStatus.OK);
		
		else
			return new ResponseEntity<List<CompanyBasicDetail>>(HttpStatus.NO_CONTENT);
	}
	
//	@PostMapping(value="/advanceDetail")
//	public CompanyAdvanceDetail advanceDetail(@RequestBody CompanyAdvanceDetail advDetail){
//		
//		las.saveAdvanceDetail(advDetail);
//		return advDetail;
//	}
	
	@RequestMapping(value = "/uploadDocs", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String fileUplods(@RequestPart("doc")  String doc,
							@RequestPart(required = true, value = "ownerAdhaarCard") MultipartFile files1,
							@RequestPart(required = true, value = "ownerSignature")	MultipartFile files2,
							@RequestPart(required = true, value = "companyPanCard")	MultipartFile files3,
							@RequestPart(required = true, value = "rentAgriment")		MultipartFile files4,
							@RequestPart(required = true, value = "companyPhoto")	MultipartFile files5,
							@RequestPart(required = true, value = "bankPassBook")MultipartFile files6,
							@RequestPart(required = true, value = "itrReport")	MultipartFile files7,
							@RequestPart(required = true, value = "pdcCheck")	MultipartFile files8,
							@RequestPart(required = true, value = "cancelCheck")	MultipartFile files9,
							@RequestPart(required = true, value = "certificateOfRegistrationOfIncorporation")MultipartFile files10,
							@RequestPart(required = true, value = "dealerQuotation")	MultipartFile files11)
throws IOException {
		log.info("post advance Details single company with documents");
		Document d=new Document();
		d.setOwnerAdhaarCard(files1.getBytes());
		d.setOwnerSignature(files2.getBytes());
		d.setCompanyPanCard(files3.getBytes());
		d.setRentAgriment(files4.getBytes());
		d.setCompanyPhoto(files5.getBytes());
		d.setBankPassBook(files6.getBytes());
		d.setItrReport(files7.getBytes());
		d.setPdcCheck(files8.getBytes());
		d.setCancelCheck(files9.getBytes());
		d.setCertificateOfRegistrationOfIncorporation(files10.getBytes());
		d.setDealerQuotation(files11.getBytes());
		
		ObjectMapper om=new ObjectMapper();
		CompanyAdvanceDetail advDetail = om.readValue(doc,CompanyAdvanceDetail.class);
		advDetail.setDocument(d);
		las.saveAdvanceDetail(advDetail);
		return "Document Uploaded Successfully!!!";
	}
	
	
	@GetMapping(value="/getAdvanceDetailOfSingleCompany/{cid}")
	public CompanyAdvanceDetail getAdvanceDetilOfSingleCompany(@PathVariable int cid) {
		log.info("Get advance Details single company");
		try {
		CompanyAdvanceDetail AdvanceDetail = las.getAdvanceDetailOfSingleCompany(cid);
		return AdvanceDetail;
		}catch(NoSuchElementException e) {
			throw new CompanyNotFoundException("Applicant with this id is not Available");
	     }
	}
	@GetMapping(value="/getAdvanceDetailOfAllApplicant")
	public ResponseEntity<List<CompanyAdvanceDetail>> getAdvanceDetilOfAllApplicant() {
		log.info("Get adv Details all companies");
		List<CompanyAdvanceDetail> advDetailList = las.getAdvanceDetailOfAllCompany();
		
		if(!advDetailList.isEmpty())
			return new ResponseEntity<List<CompanyAdvanceDetail>>(advDetailList,HttpStatus.OK);
		
		else
			return new ResponseEntity<List<CompanyAdvanceDetail>>(HttpStatus.NO_CONTENT);
			
	}

	@GetMapping(value="getCibilScore")
	public int getCibilScore(){
		log.info("Get cibil score");
		int max=1000;
		int min= 700;
		int b = (int)(Math.random()*(max-min+1)+min);  
		return b;
	}
	@GetMapping(value="VerifyDocuments")
	public String getDocumentVerification(){
		log.info("Document verification");
		
		return "Verification Successfull !!!";
	}
//	@GetMapping(value="emi")
//	public double getEmi(){
//		principal = a.nextFloat();
//	       rate = a.nextFloat();
//	        time = a.nextFloat(); //in years
//	        rate=rate/(12*100); 
//	        time=time*12;
//	        emi= (principal*rate*Math.pow(1+rate,time))/(Math.pow(1+rate,time)-1);
//		return true;
//	}
	@Value("${spring.mail.username}")
	private String fromEmail;
	
	@PostMapping(value = "sendMailDisburse/{companyId}")
	public String sendEmailDisburse(@RequestBody SanctionDetails sd,@PathVariable int companyId) {
		log.info("Email sending process");
		EmailSender e=new EmailSender();
		e.setFromEmail(fromEmail);
		try {
			es.mailSendDisburse(e,sd, companyId);
			return "Email sent Successfully!!!";
		}catch(Exception exception) {
			return "Email not sent";
		}
		
	}
	
	@PostMapping(value = "sendMail/{companyId}")
	public String sendEmail(@RequestBody SanctionDetails sd, @PathVariable int companyId) {
		log.info("Email sending process");
		EmailSender e=new EmailSender();
		e.setFromEmail(fromEmail);
		try {
			es.mailSend(e,sd,companyId);
			return "Email sent Successfully!!!";
		}catch(NoSuchElementException ee) {
			throw new CompanyNotFoundException("Company with this id is not Available");
		}
	}
	
	@PutMapping(value="addCibil/{companyId}")
	public CompanyBasicDetail addCibil(@PathVariable int companyId,@RequestBody Cibil cibil) {
		log.info("Update cibil score");
		try {
		CompanyBasicDetail companyBasicDetail = lbs.updateBasicDetail(companyId,cibil);
		return companyBasicDetail;
		}catch(NoSuchElementException e) {
			throw new CompanyNotFoundException("Company with this id is not Available");
		}
		
	}
	@RequestMapping(value="addCmRemark/{companyId}/{cmRemark}")
	public CompanyAdvanceDetail addCmRemark(@PathVariable int companyId,@PathVariable String cmRemark) {
		log.info("Update cmRemark");
		try {
			log.info(cmRemark);
			CompanyAdvanceDetail companyAdvanceDetail = las.updateAdvanceDetail(companyId,cmRemark);
		return companyAdvanceDetail;
		}catch(NoSuchElementException e) {
			throw new CompanyNotFoundException("Company with this id is not Available");
		}	
	}
	
	@RequestMapping(value="addSanctionUploadStatus/{companyId}/{status}")
	public CompanyAdvanceDetail addSanctionUploadStatus(@PathVariable int companyId,@PathVariable String status) {
		log.info("Update addSanctionUploadStatus");
		try {
			log.info(status);
			CompanyAdvanceDetail companyAdvanceDetail = las.updateSanctionUploadStatus(companyId,status);
		return companyAdvanceDetail;
		}catch(NoSuchElementException e) {
			throw new CompanyNotFoundException("Company with this id is not Available");
		}	
	}
	
	@GetMapping(value="getStateList/{countryId}")
	public List<State> getStateList(@PathVariable String countryId){
		log.info("get all states with countryId = "+countryId);
		List<State> stateList = stateRepository.findAllByCountryId(Integer.parseInt(countryId));
	    return stateList;
		
	}
	@GetMapping(value="getCountryList")
	public List<Country> getCountryList(){;
		List<Country> countryList = countryRepository.findAll();
	    return countryList;	
	}
	@GetMapping(value="getCityList/{stateId}")
	public List<City> getCityList(@PathVariable int stateId){;
		List<City> cityList = cityRepository.findAllByStateId(stateId);
	    return cityList;
	}
		
}


