package com.bank.model;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name="customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long cid;
	
	@NotEmpty(message = "Please provide a Customer Name")
	@Size(max = 20,min = 1)
	private String cname;
	
	@NotEmpty(message = "Please provide a Mobile Number")
	@Pattern(regexp="(^$|[0-9]{10})",message = "Provide valid Mobile Number")
	private String mobileno;
	
	@NotEmpty(message="Please provide Email id")
	//@Pattern(regexp=".+@.+\\..+", message="Please provide a valid email address")
	@Email
	private String emailid;
	
	@OneToMany(mappedBy = "customer",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<BankAccount> bankaccount;
	
	/*
	 * @NotEmpty(message = "Communication preference is required")
	 * 
	 * @CommPreference private String commPreference;
	 */

	
	public long getCid() {
		return cid;
	}

	public void setCid(long cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}


	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public List<BankAccount> getBankaccount() {
		return bankaccount;
	}

	public void setBankaccount(List<BankAccount> bankaccount) {
		this.bankaccount = bankaccount;
	}
	
}
