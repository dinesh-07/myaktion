package com.myaktion.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
public class Donation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false)
	@JsonProperty(access = Access.READ_ONLY)
	private long id;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "campaign_id", nullable = false)
	private Campaign campaign;
	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "name", column = @Column(name = "account_name")) })
	private Account account;

	@Min(value = 1, message = "The amount of the donation must be at least 1")
	private double amount;
	private boolean receiptRequested;
	@Size(min = 5, max = 40, message = "Length of donor name must be at least 5 and at most 40")
	private String donorName;
	@NotNull
	private Status status = Status.IN_PROCESS;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Campaign getCampaign() {
		return campaign;
	}

	public void setCampaign(Campaign campaign) {
		this.campaign = campaign;
	}

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

}
