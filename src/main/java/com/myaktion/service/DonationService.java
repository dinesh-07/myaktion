package com.myaktion.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myaktion.domain.Campaign;
import com.myaktion.domain.CampaignRepository;
import com.myaktion.domain.Donation;
import com.myaktion.domain.DonationRepository;
import com.myaktion.exceptions.CampaignNotFoundException;

@Service
public class DonationService {
	private static final Logger LOG = LoggerFactory.getLogger(DonationService.class);

	@Autowired
	DonationRepository donationRepository;
	@Autowired
	CampaignRepository campaignRepository;

	public Donation addDonation(Donation donation, Long campaignId) throws CampaignNotFoundException {
		Optional<Campaign> campOpt = campaignRepository.findById(campaignId);

		if (campOpt.isEmpty()) {
			LOG.trace("Campaign not found!");
			throw new CampaignNotFoundException("Campaign not found!");
		}
		Campaign campaign = campOpt.get();
		donation.setCampaign(campaign);
		campaign.addDonation(donation);
		return donationRepository.save(donation);
	}

	private Donation setAttributes(Donation donation, Donation request) {
		if (donation == null) {
			donation = new Donation();
		}
		donation.setDonorName(request.getDonorName());
		donation.setAccount(request.getAccount());
		donation.setAmount(request.getAmount());
		donation.setReceiptRequested(request.isReceiptRequested());
		donation.setStatus(request.getStatus());
		return donation;
	}
}
