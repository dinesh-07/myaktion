package com.myaktion.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.myaktion.domain.Account;
import com.myaktion.domain.Campaign;
import com.myaktion.domain.Status;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DonationRequest {
	private Account account;
	private double amount;
	private boolean receiptRequested;
	private String donorName;
	private Status status;
	private Campaign campaign;

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public boolean isReceiptRequested() {
		return receiptRequested;
	}

	public void setReceiptRequested(boolean receiptRequested) {
		this.receiptRequested = receiptRequested;
	}

	public String getDonorName() {
		return donorName;
	}

	public void setDonorName(String donorName) {
		this.donorName = donorName;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Campaign getCampaign() {
		return campaign;
	}

	public void setCampaign(Campaign campaign) {
		this.campaign = campaign;
	}

}
