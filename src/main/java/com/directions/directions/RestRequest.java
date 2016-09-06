package com.directions.directions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;





/**
 * @author Keith Gibson
 * @since 2016-09-06 (my birthday)
 *
 */
public class RestRequest {

	protected static String endpoint = "https://maps.googleapis.com/maps/api/directions/";
	
//	The URL of the API we want to connect to
	
	protected static String charset = "UTF-8";
	
//	The character set to use when encoding URL parameters
	
	protected static String key = "AIzaSyAI-b0OwKFzq2tHeLht0JiYzgN2kF6k_l8";
	
//	API key used for makig requests to API
	
	public static void main(String[] args) {
		
		try {
			
			//The origin or starting point for directions
			String origin = "Baltimore MD";
			
			//The destination or end point for directions
			String destination = "San Francisco CA";
			
			//The mode of transportation (driving, walking, bicycling...)
			String mode = "transit";
			
			//The type of transit
			String transit_mode = "bus|train";
			
			//output language
			String language = "italian";
			
			//The return type of theresponse xml|json
			String returnType = "json";
			
			//creates the URL parameters as a string encoding them with the defined charset
			String queryString = String.format("origin=%s&destination=%s&key=%s&mode=%s&transit_mode=%s",
					URLEncoder.encode(origin, charset),
					URLEncoder.encode(destination, charset),
					URLEncoder.encode(key, charset),
					URLEncoder.encode(mode, charset),
					URLEncoder.encode(transit_mode, charset),
					URLEncoder.encode(language, charset)
					);
			
			//creates a new URL out of the endpoint, returnType and queryString
			URL googleDirections = new URL(endpoint + returnType + "?" + queryString);
			HttpURLConnection connection = (HttpURLConnection) googleDirections.openConnection();
			connection.setRequestMethod("GET");
			
			//if we did not get a 200 (success) throw an exception
			if (connection.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + connection.getResponseCode());

			}
					
			//read response into buffer
			BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));
			
			//loop of buffer line by line until it returns null meaning there are no more lines
			while (br.readLine() != null) {
				
				System.out.println(br.readLine());
			}
			
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
			
		} catch (IOException e) {
			e.printStackTrace();
			
		} 
		
		
		
	}
	
	
	
}
