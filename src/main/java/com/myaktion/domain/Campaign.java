package com.myaktion.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
public class Campaign {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonProperty(access = Access.READ_ONLY)
	private long id;
	@Size(min = 4, max = 30, message = "Length of campaign name must be at least 4 and at most 30")
	private String name;
	@Min(value = 1, message = "The amount of the donation must be atleast 1")
	private double donationMinimum;
	@Min(value = 1, message = "The target amount of the campaign must be at most 10 Euro")
	private double targetAmount;

	@OneToMany(mappedBy = "campaign", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Donation> donations = new ArrayList<>();

	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "name", column = @Column(name = "account_name")) })
	private Account account;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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

	public List<Donation> getDonations() {
		return donations;
	}

	public void setDonations(List<Donation> donations) {
		this.donations = donations;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public void addDonation(Donation donation) {
		List<Donation> donations2 = getDonations();
		donations2.add(donation);
		this.donations = donations2;
	}

}
