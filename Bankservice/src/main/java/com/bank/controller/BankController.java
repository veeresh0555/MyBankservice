package com.bank.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.exception.AlreadyExistException;
import com.bank.exception.RecordNotFoundException;
import com.bank.model.Customer;
import com.bank.model.TransactionHistory;
import com.bank.service.BankService;

@RestController
@RequestMapping("/bankservice")
public class BankController {

	@Autowired
	Environment env;
	
	@Autowired
	BankService bankservice;
	
	@GetMapping("/info")
	public String info() {
		String port= env.getProperty("local.server.port");
		return "Bankservice running port on "+port;
	}
	
	@PostMapping
	public ResponseEntity<Customer> createUser(@Valid @RequestBody Customer cust) throws AlreadyExistException{
		System.out.println("Enter BankController::createUser() Method");
		Customer createcust=bankservice.createCustomer(cust);
		System.out.println("Exit BankController::createUser() Method");
		return new ResponseEntity<Customer>(createcust, new HttpHeaders(), HttpStatus.OK);
	}
	
	@PostMapping("/payment") 
	public ResponseEntity<TransactionHistory> payment(@RequestParam("cid") long cid,@RequestParam("amount") Double amount) throws Exception{
		System.out.println("CID: "+cid+"\t amount: "+amount);
		TransactionHistory transdata=bankservice.makepayment(cid,amount);
		return new ResponseEntity<TransactionHistory>(transdata,new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/lateststmt")
	public ResponseEntity<List<TransactionHistory>> latest10records(@RequestParam("userid") long userid) throws RecordNotFoundException{
		List<TransactionHistory> top10records=bankservice.getstatement(userid);
		return new ResponseEntity<List<TransactionHistory>>(top10records,new HttpHeaders(),HttpStatus.OK);
	}
	
	
		
}
