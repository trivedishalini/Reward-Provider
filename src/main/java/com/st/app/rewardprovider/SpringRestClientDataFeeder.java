package com.st.app.rewardprovider;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.st.app.rewardprovider.entity.Customer;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * The Class SpringRestClientDataFeeder to add Mock data in databse
 */
public class SpringRestClientDataFeeder {

	private static final String GET_CUSTOMER_ENDPOINT_URL = "http://localhost:8080/api/v1/customer";
	private static final String GET_CUSTOMER_BY_ID_ENDPOINT_URL = "http://localhost:8080/api/v1/customer/{id}";

	private static final String CREATE_CUSTOMER_ENDPOINT_URL = "http://localhost:8080/api/v1/customer";
	private static final String UPDATE_CUSTOMER_ENDPOINT_URL = "http://localhost:8080/api/v1/customer/{id}";
	private static final String DELETE_CUSTOMER_ENDPOINT_URL = "http://localhost:8080/api/v1/customer/{id}";
	private static RestTemplate restTemplate = new RestTemplate();

	/**
	 * Main method to call Customer CRUD end point
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		SpringRestClientDataFeeder springRestClientDataFeeder = new SpringRestClientDataFeeder();
		
		// Step1: first create a new customer
		springRestClientDataFeeder.createCustomer();
		
		// Step 2: get new created customer from step1
		springRestClientDataFeeder.getCustomerById();
		
		// Step3: get all customer
		springRestClientDataFeeder.getCustomer();
		
		// Step4: Update customer with id = 1
		springRestClientDataFeeder.updateCustomer();
		
		// Step5: Delete customer with id = 1
		springRestClientDataFeeder.deleteCustomer();
	}

	private void getCustomer() {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		ResponseEntity<String> result = restTemplate.exchange(GET_CUSTOMER_ENDPOINT_URL, HttpMethod.GET, entity,
				String.class);

		System.out.println(result);
	}

	private void getCustomerById() {

		Map<String, String> params = new HashMap<String, String>();
		params.put("id", "1");

		RestTemplate restTemplate = new RestTemplate();
		Customer result = restTemplate.getForObject(GET_CUSTOMER_BY_ID_ENDPOINT_URL, Customer.class, params);

		System.out.println(result);
	}

	private void createCustomer() {

		Customer newCustomer = new Customer("admin", "admin", "admin@gmail.com");

		RestTemplate restTemplate = new RestTemplate();
		Customer result = restTemplate.postForObject(CREATE_CUSTOMER_ENDPOINT_URL, newCustomer, Customer.class);

		System.out.println(result);
	}

	private void updateCustomer() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", "1");
		Customer updatedCustomer = new Customer("admin123", "admin123", "admin123@gmail.com");
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.put(UPDATE_CUSTOMER_ENDPOINT_URL, updatedCustomer, params);
	}

	private void deleteCustomer() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", "1");
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(DELETE_CUSTOMER_ENDPOINT_URL, params);
	}
}
