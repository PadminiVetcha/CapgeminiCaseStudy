package org.padmini.railway.entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

public class PaymentDetails 
{
	@Id
	@NotNull
	@Size(min=16,max=16,message="Card Number should of 16 digits")
	private String cardNo;
	
	@NotNull
	@Max(value=999,message="CVV cannot exceed 3 digits")
	private int cvv;
	
	@NotNull
	private String bankName;
	
	@NotNull
	@Min(value=10,message="Please enter amount correctly..!!")
	private int amt;
	
	public PaymentDetails() {
		super();
	}

	public PaymentDetails(String cardNo, int cvv, String bankName, int amt) {
		super();
		this.cardNo = cardNo;
		this.cvv = cvv;
		this.bankName = bankName;
		this.amt = amt;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public int getCvv() {
		return cvv;
	}

	public void setCvv(int cvv) {
		this.cvv = cvv;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public int getAmt() {
		return amt;
	}

	public void setAmt(int amt) {
		this.amt = amt;
	}

	@Override
	public String toString() {
		return "PaymentDetails [cardNo=" + cardNo + ", cvv=" + cvv + ", bankName=" + bankName + ", amt=" + amt + "]";
	}
}
