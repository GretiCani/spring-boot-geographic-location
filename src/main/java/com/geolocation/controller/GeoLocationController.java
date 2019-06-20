package com.geolocation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.geolocation.model.GeoIP;
import com.geolocation.service.GeoLocationService;

@Controller
public class GeoLocationController {

	private GeoLocationService geoLocationService;
	
	public GeoLocationController(GeoLocationService geoLocationService) {
		this.geoLocationService = geoLocationService;
	}
	
	@RequestMapping(value = "/geo-location", method = RequestMethod.POST)
	public @ResponseBody GeoIP getLocation(@RequestParam(value ="ipAddress",required =true) String ipAddress)throws Exception{
		
		return geoLocationService.getLocation(ipAddress);
	}
}
