package com.bank.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bank.exception.AlreadyExistException;
import com.bank.exception.RecordNotFoundException;
import com.bank.model.BankAccount;
import com.bank.model.Customer;
import com.bank.model.TransactionHistory;
import com.bank.repository.BankRepository;
import com.bank.repository.CustomerRepository;
import com.bank.repository.TransactionRepository;

@Service
public class BankServiceImpl implements BankService {

	@Autowired
	CustomerRepository custRepository;
	
	@Autowired
	BankRepository bankRepository;
	
	
	@Autowired
	TransactionRepository transrepo;
	
	@Transactional
	@Override
	public Customer createCustomer(Customer cust) throws AlreadyExistException {
		System.out.println("Enter BankServiceImpl::createCustomer() method");
		Optional<Customer> findcustById=custRepository.findById(cust.getCid());
		if(findcustById.isPresent()) {
			throw new AlreadyExistException("customer id "+cust.getCid() +" is Alread Registered ");
		}else {
			
			List<BankAccount> bankaccount=new ArrayList<>();
			BankAccount b=new BankAccount();
			 b.setBalance(10000.00);
			 b.setCardnumber(generatecardcountNumber(16));
			 b.setCvv(generatecardcountNumber(3)); 
			 b.setExpdate(_expdate);
			 b.setCustomer(cust); 
			 bankaccount.add(b);
			cust.setBankaccount(bankaccount);
			System.out.println("Exit BankServiceImpl::createCustomer() method");
			return custRepository.save(cust);
		}
	}
	@Transactional
	@Override
	public TransactionHistory makepayment(long cid,Double amount) throws Exception {
		System.out.println("Enter BankServiceImpl->makePament method: ");
		System.out.println("Customer id: "+cid+"\t amount: "+amount);
		TransactionHistory transhistory=new TransactionHistory();
		Optional<Customer> checkcustById=custRepository.findById(cid);
		BankAccount baccount=new BankAccount();
		if(checkcustById.isPresent()) {
			Customer cprasent=checkcustById.get();
			List<BankAccount> acn=cprasent.getBankaccount();
			acn.stream().forEach(strcaard->baccount.setAcno(strcaard.getAcno()));
			acn.stream().forEach(strcaard->baccount.setBalance(strcaard.getBalance()-amount));
			Optional<BankAccount> findaccount=bankRepository.findById(baccount.getAcno());
			if(findaccount.isPresent()) {
				System.out.println("Account Number prasent block ====>");
				BankAccount updatebal=findaccount.get();
				updatebal.setBalance(baccount.getBalance());
				updatebal=bankRepository.save(updatebal);
				transhistory.setUserid(cprasent.getCid());
				transhistory.setCardno(updatebal.getCardnumber());
				transhistory.setAmount(amount);
				return transrepo.save(transhistory);
			}else {
				throw new RecordNotFoundException("Customer Id Not mapped with bank account");
			}
			}else {
				throw new RecordNotFoundException("Customer/CID Not available");
		}
	}

	@Transactional
	@Override
	public List<TransactionHistory> getstatement(long userid) throws RecordNotFoundException {
		Pageable top10=PageRequest.of(0, 10);
		List<TransactionHistory> stmt=  transrepo.findstmtById(userid, top10);
		if(stmt.size()==0) {
			throw new RecordNotFoundException("Transaction not available ");
		}
		return stmt;
	}

	
	
	
	
	
	
	
	
	
	public static String generatecardcountNumber(int length) {//int length
	    Random random = new Random();
	    char[] digits = new char[length];
	    digits[0] = (char) (random.nextInt(9) + '1');
	    for (int i = 1; i < length; i++) {
	        digits[i] = (char) (random.nextInt(10) + '0');
	    }
	    System.out.println("Digits: "+Long.parseLong(new String(digits)));
	    return new String(digits);
	}
	
	private final String _expdate="24-06-2026";

}
