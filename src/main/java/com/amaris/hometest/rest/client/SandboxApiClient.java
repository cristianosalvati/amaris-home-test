package com.amaris.hometest.rest.client;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.amaris.hometest.datatransferobject.AccountDTO;
import com.amaris.hometest.exception.ClientException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

@Component
public class SandboxApiClient {

	@Value("${application.configuration.client.sandbox.connection-timeout}")
	private int clientConnectionTimeoutValue;
	
	@Value("${application.configuration.datePattern}")
	private String datePattern;
	
	@Value("${application.configuration.client.sandbox.base-url}")
	private String clientBaseUrl;
	
	@Value("${application.configuration.client.sandbox.auth-schema}")
	private String clientAuthSchema;
	
	@Value("${application.configuration.client.sandbox.time-zone}")
	private String clientTimezone;
	
	@Value("${application.configuration.client.sandbox.api-key}")
	private String clientApiKey;

	public String balance(long accountId) throws ClientException{
		ResponseBody responseBody = null;
		try {
			OkHttpClient client = new OkHttpClient().newBuilder()
				.connectTimeout(clientConnectionTimeoutValue, TimeUnit.MILLISECONDS)
				.build();
			MediaType mediaType = MediaType.parse("text/plain");
			RequestBody body = null;
			Request request = new Request.Builder()
			  .url(clientBaseUrl+"/api/gbs/banking/v4.0/accounts/"+accountId+"/balance")
			  .method("GET", body)
			  .addHeader("X-Time-Zone", clientTimezone)
			  .addHeader("Auth-Schema", clientAuthSchema)
			  .addHeader("apikey", clientApiKey)
			  .build();
			
			Response response = client.newCall(request).execute();
			responseBody = response.body();
			return responseBody.string();
		} catch (IOException e) {
			throw new ClientException(e);
		}finally{
			if (responseBody != null)
				responseBody.close();
		}
	}
	
//	TODO: this is a scratch, complete this method as client operation
	public String transaction(long accountId, Date dateStart, Date dateEnd) throws ClientException{
		
		String dateStartS = (new SimpleDateFormat(datePattern)).format(dateStart);
		String dateEndS = (new SimpleDateFormat(datePattern)).format(dateEnd);
		
		ResponseBody responseBody = null;
		try{
		    OkHttpClient client = new OkHttpClient().newBuilder()
		    	.connectTimeout(clientConnectionTimeoutValue, TimeUnit.MILLISECONDS)
	    		.build();
		    MediaType mediaType = MediaType.parse("text/plain");
		    RequestBody body = null;
		    Request request = new Request.Builder()
			    .url(clientBaseUrl+"/api/gbs/banking/v4.0/accounts/"+accountId+"/transactions?fromAccountingDate="+dateStartS+"&toAccountingDate="+dateEndS)
			    .method("GET", body)
			    .addHeader("X-Time-Zone", clientTimezone)
			    .addHeader("Auth-Schema", clientAuthSchema)
			    .addHeader("apikey", clientApiKey)
			    .build();
		  
			Response response = client.newCall(request).execute();
			responseBody = response.body();
			return responseBody.string();
		} catch (IOException e) {
			throw new ClientException(e);
		}finally{
			if (responseBody != null)
				responseBody.close();
		}
	}

//	TODO: this is a scratch, complete this method as client payment operation
	public String payment(@Valid AccountDTO accountDTO) throws ClientException {
		ResponseBody responseBody = null;
		try{
			OkHttpClient client = new OkHttpClient().newBuilder()
				.connectTimeout(clientConnectionTimeoutValue, TimeUnit.MILLISECONDS)
				.build();
			MediaType mediaType = MediaType.parse("application/json");
			RequestBody body = RequestBody.create(mediaType, "{\r\n  \"creditor\": {\r\n    \"name\": \"John Doe\",\r\n    \"account\": {\r\n      \"accountCode\": \"IT23A0336844430152923804660\",\r\n      \"bicCode\": \"SELBIT2BXXX\"\r\n    },\r\n    \"address\": {\r\n      \"address\": null,\r\n      \"city\": null,\r\n      \"countryCode\": null\r\n    }\r\n  },\r\n  \"executionDate\": \"2019-04-01\",\r\n  \"uri\": \"REMITTANCE_INFORMATION\",\r\n  \"description\": \"Payment invoice 75/2017\",\r\n  \"amount\": 800,\r\n  \"currency\": \"EUR\",\r\n  \"isUrgent\": false,\r\n  \"isInstant\": false,\r\n  \"feeType\": \"SHA\",\r\n  \"feeAccountId\": null,\r\n  \"taxRelief\": {\r\n    \"taxReliefId\": \"L449\",\r\n    \"isCondoUpgrade\": false,\r\n    \"creditorFiscalCode\": \"56258745832\",\r\n    \"beneficiaryType\": \"NATURAL_PERSON\",\r\n    \"naturalPersonBeneficiary\": {\r\n      \"fiscalCode1\": \"MRLFNC81L04A859L\",\r\n      \"fiscalCode2\": null,\r\n      \"fiscalCode3\": null,\r\n      \"fiscalCode4\": null,\r\n      \"fiscalCode5\": null\r\n    },\r\n    \"legalPersonBeneficiary\": {\r\n      \"fiscalCode\": null,\r\n      \"legalRepresentativeFiscalCode\": null\r\n    }\r\n  }\r\n}");
			Request request = new Request.Builder()
			  .url(clientBaseUrl+"/api/gbs/banking/v4.0/accounts/14537780/payments/money-transfers")
			  .method("POST", body)
			  .addHeader("X-Time-Zone", clientTimezone)
			  .addHeader("Auth-Schema", clientAuthSchema)
			  .addHeader("apikey", clientApiKey)
			  .addHeader("Content-Type", "application/json")
			  .build();
			
			Response response = client.newCall(request).execute();
			responseBody = response.body();
			return responseBody.string();
		} catch (IOException e) {
			throw new ClientException(e);
		}finally{
			if (responseBody != null)
				responseBody.close();
		}
	}
	
}
