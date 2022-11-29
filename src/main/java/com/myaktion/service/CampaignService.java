package com.myaktion.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myaktion.domain.Campaign;
import com.myaktion.domain.CampaignRepository;
import com.myaktion.domain.Donation;
import com.myaktion.exceptions.CampaignNotFoundException;

@Service
public class CampaignService {

	private static final Logger LOG = LoggerFactory.getLogger(CampaignService.class);

	@Autowired
	CampaignRepository campaignRepository;
	@Autowired
	DonationService donationService;

	public Campaign addCampaign(Campaign campaign) throws Exception {
		return campaignRepository.save(campaign);
	}

	public List<Campaign> getCampaigns() {
		return campaignRepository.findAll();
	}

	public Campaign updateCampaign(Long id, Campaign ro) throws CampaignNotFoundException {
		Optional<Campaign> findById = campaignRepository.findById(id);
		if (findById.isEmpty()) {
			LOG.trace("Campaign not found!");
			throw new CampaignNotFoundException("Campaign not found!");
		}
		Campaign campaign = findById.get();
		campaign = setAttributes(campaign, ro);
		return campaignRepository.save(campaign);
	}

	public void deleteCampaign(Long id) throws CampaignNotFoundException {
		Optional<Campaign> findById = campaignRepository.findById(id);
		if (findById.isEmpty()) {
			LOG.trace("Campaign not found!");
			throw new CampaignNotFoundException("Campaign not found!");
		}
		Campaign campaign = findById.get();
		campaignRepository.delete(campaign);
	}

	public Campaign getCampaign(Long id) throws CampaignNotFoundException {
		Optional<Campaign> findById = campaignRepository.findById(id);
		if (findById.isEmpty()) {
			LOG.trace("Campaign not found!");
			throw new CampaignNotFoundException("Campaign not found!");
		}
		return findById.get();
	}

	private Campaign setAttributes(Campaign campaign, Campaign ro) {
		if (campaign == null) {
			campaign = new Campaign();
		}
		campaign.setName(ro.getName());
		campaign.setAccount(ro.getAccount());
		campaign.setDonationMinimum(ro.getDonationMinimum());
		campaign.setTargetAmount(ro.getTargetAmount());
		return campaign;
	}

	public Campaign addDonationToCampaign(Long id, Donation request) throws CampaignNotFoundException {
		Optional<Campaign> findById = campaignRepository.findById(id);
		if (findById.isEmpty()) {
			LOG.trace("Campaign not found!");
			throw new CampaignNotFoundException("Campaign not found!");
		}
		Campaign campaign = findById.get();
		Donation donation = donationService.addDonation(request, id);
		campaign.addDonation(donation);
		return campaignRepository.save(campaign);
	}

}
