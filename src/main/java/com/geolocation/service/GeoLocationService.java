package com.geolocation.service;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.geolocation.model.GeoIP;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;

@Service
public class GeoLocationService {

	private DatabaseReader databaseReader;
	
	public GeoLocationService() throws IOException {
		
		File database =  new ClassPathResource("/geo-location-db/GeoLite2-City.mmdb").getFile();
		databaseReader = new DatabaseReader.Builder(database).build();
    }
	
	public GeoIP getLocation(String ip)throws IOException,GeoIp2Exception{
		InetAddress ipAddress = InetAddress.getByName(ip);
		CityResponse cityResponse = databaseReader.city(ipAddress);
		
		String cityName = cityResponse.getCity().getName();
		String latitude = cityResponse.getLocation().getLatitude().toString();
		String longitude = cityResponse.getLocation().getLongitude().toString();
		
		return new GeoIP(ip, cityName, latitude, longitude);
	}
	
}
