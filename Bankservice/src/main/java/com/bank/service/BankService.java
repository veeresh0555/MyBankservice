package com.bank.service;

import java.util.List;

import com.bank.exception.AlreadyExistException;
import com.bank.exception.RecordNotFoundException;
import com.bank.model.Customer;
import com.bank.model.TransactionHistory;

public interface BankService {

	public Customer createCustomer(Customer cust) throws AlreadyExistException;
	
	public TransactionHistory makepayment(long cid,Double amount) throws Exception;

	public List<TransactionHistory> getstatement(long userid) throws RecordNotFoundException;

}
