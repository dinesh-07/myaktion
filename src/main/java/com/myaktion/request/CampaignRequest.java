package com.myaktion.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.myaktion.domain.Account;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CampaignRequest {
	private String name;
	private double donationMinimum;
	private double targetAmount;
	private Account account;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getDonationMinimum() {
		return donationMinimum;
	}

	public void setDonationMinimum(double donationMinimum) {
		this.donationMinimum = donationMinimum;
	}

	public double getTargetAmount() {
		return targetAmount;
	}

	public void setTargetAmount(double targetAmount) {
		this.targetAmount = targetAmount;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}
