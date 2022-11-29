package com.myaktion.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myaktion.domain.Campaign;
import com.myaktion.domain.Donation;
import com.myaktion.exceptions.CampaignNotFoundException;
import com.myaktion.service.CampaignService;

@RestController
@RequestMapping(value = "/campaigns")
public class CampaignController {

	private static final Logger LOG = LoggerFactory.getLogger(CampaignController.class);

	@Autowired
	CampaignService service;

	@GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Campaign>> getCampaigns() {
		return ResponseEntity.ok(service.getCampaigns());
	}

	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Campaign> createCampaign(@RequestBody @Valid Campaign request) throws Exception {
		return new ResponseEntity<>(service.addCampaign(request), HttpStatus.CREATED);
	}

	@GetMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Campaign> getCampaign(@PathVariable String id) throws CampaignNotFoundException {
		return ResponseEntity.ok(service.getCampaign(Long.valueOf(id)));
	}

	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Campaign> updateCampaign(@PathVariable String id, @RequestBody @Valid Campaign ro)
			throws CampaignNotFoundException {
		return new ResponseEntity<>(service.updateCampaign(Long.valueOf(id), ro), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCampaign(@PathVariable String id) throws CampaignNotFoundException {
		service.deleteCampaign(Long.valueOf(id));
		return ResponseEntity.noContent().build();
	}

	@PostMapping(value = "/{id}/donations", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Campaign addDonationToCampaign(@PathVariable String id, @RequestBody @Valid Donation request)
			throws CampaignNotFoundException {
		return service.addDonationToCampaign(Long.valueOf(id), request);
	}

}
