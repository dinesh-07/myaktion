package com.myaktion;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.myaktion.domain.Account;
import com.myaktion.domain.Campaign;
import com.myaktion.domain.Donation;
import com.myaktion.domain.Status;
import com.myaktion.service.CampaignService;
import com.myaktion.service.DonationService;

@SpringBootApplication
public class MyaktionApplication {

	private static final Logger LOG = LoggerFactory.getLogger(MyaktionApplication.class);

	@Autowired
	private CampaignService campaignService;
	@Autowired
	private DonationService donationService;

	public static void main(String[] args) {
		SpringApplication.run(MyaktionApplication.class, args);
	}

	@Bean
	CommandLineRunner initLoggerBean() {
		return (args) -> {
			LOG.info("Adding Sample data to DB");
			Account account1 = createAccount("Jogi Löw", "KSK Freiburg", "DE46112312312312345");
			Account account2 = createAccount("Jogi Löw", "KSK Freiburg", "DE46112312312312345");
			Account account3 = createAccount("Berti Vogts", "VoBa Gladbach", "DE41112444312314442");

			Campaign campaign1 = new Campaign();
			campaign1.setName("Trikots A-Jugend");
			campaign1.setTargetAmount(1000d);
			campaign1.setDonationMinimum(1d);
			campaign1.setAccount(account1);

			campaign1 = campaignService.addCampaign(campaign1);

			Donation donation1 = new Donation();
			donation1.setAccount(account3);
			donation1.setAmount(199d);
			donation1.setDonorName("Berti Vogts");
			donation1.setReceiptRequested(false);
			donation1.setStatus(Status.TRANSFERRED);
			donation1 = donationService.addDonation(donation1, campaign1.getId());

			Donation donation2 = new Donation();
			donation2.setAccount(account2);
			donation2.setAmount(200d);
			donation2.setDonorName("Hansi Flick");
			donation2.setReceiptRequested(true);
			donation2.setStatus(Status.IN_PROCESS);
			donation2 = donationService.addDonation(donation2, campaign1.getId());

			List<Campaign> campaigns = campaignService.getCampaigns();
			LOG.info("Read all campaigns again");
			LOG.info("Number of campaign: " + campaigns.size());
			LOG.info("Number of Donation: " + campaigns.get(0).getDonations().size());
		};
	}

	private Account createAccount(String name, String nameOfBank, String iBan) {
		Account account = new Account();
		account.setName(name);
		account.setNameOfBank(nameOfBank);
		account.setIban(iBan);
		return account;
	}

}
