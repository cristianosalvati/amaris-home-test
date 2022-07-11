package com.amaris.hometest.rest.client;

import java.io.IOException;

import com.amaris.hometest.rest.exception.ClientException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SandboxApiClient {

	public Response balance() throws ClientException{
		Response response = null;
		OkHttpClient client = new OkHttpClient().newBuilder()
		  .build();
		MediaType mediaType = MediaType.parse("text/plain");
		RequestBody body = null;
		Request request = new Request.Builder()
		  .url("https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts/14537780/balance")
		  .method("GET", body)
		  .addHeader("X-Time-Zone", "Europe/Rome")
		  .addHeader("Auth-Schema", "S2S")
		  .addHeader("apikey", "FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP")
		  .build();
		try {
			response = client.newCall(request).execute();
		} catch (IOException e) {
			throw new ClientException(e);
		}
	    return response;
	}
	
	public Response transaction() throws ClientException{
		Response response = null;
	    OkHttpClient client = new OkHttpClient().newBuilder()
    		.build();
	    MediaType mediaType = MediaType.parse("text/plain");
	    RequestBody body = null;
	    Request request = new Request.Builder()
		    .url("https://sandbox.platfr.io//api/gbs/banking/v4.0/accounts/14537780/transactions?fromAccountingDate=2019-07-10&toAccountingDate=2022-07-10")
		    .method("GET", body)
		    .addHeader("X-Time-Zone", "Europe/Rome")
		    .addHeader("Auth-Schema", "S2S")
		    .addHeader("apikey", "FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP")
		    .build();
	    try {
			response = client.newCall(request).execute();
		} catch (IOException e) {
			throw new ClientException(e);
		}
	    return response;
	}
	
	public Response payment() throws ClientException {
		Response response = null;
		OkHttpClient client = new OkHttpClient().newBuilder()
		  .build();
		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(mediaType, "{\r\n  \"creditor\": {\r\n    \"name\": \"John Doe\",\r\n    \"account\": {\r\n      \"accountCode\": \"IT23A0336844430152923804660\",\r\n      \"bicCode\": \"SELBIT2BXXX\"\r\n    },\r\n    \"address\": {\r\n      \"address\": null,\r\n      \"city\": null,\r\n      \"countryCode\": null\r\n    }\r\n  },\r\n  \"executionDate\": \"2019-04-01\",\r\n  \"uri\": \"REMITTANCE_INFORMATION\",\r\n  \"description\": \"Payment invoice 75/2017\",\r\n  \"amount\": 800,\r\n  \"currency\": \"EUR\",\r\n  \"isUrgent\": false,\r\n  \"isInstant\": false,\r\n  \"feeType\": \"SHA\",\r\n  \"feeAccountId\": null,\r\n  \"taxRelief\": {\r\n    \"taxReliefId\": \"L449\",\r\n    \"isCondoUpgrade\": false,\r\n    \"creditorFiscalCode\": \"56258745832\",\r\n    \"beneficiaryType\": \"NATURAL_PERSON\",\r\n    \"naturalPersonBeneficiary\": {\r\n      \"fiscalCode1\": \"MRLFNC81L04A859L\",\r\n      \"fiscalCode2\": null,\r\n      \"fiscalCode3\": null,\r\n      \"fiscalCode4\": null,\r\n      \"fiscalCode5\": null\r\n    },\r\n    \"legalPersonBeneficiary\": {\r\n      \"fiscalCode\": null,\r\n      \"legalRepresentativeFiscalCode\": null\r\n    }\r\n  }\r\n}");
		Request request = new Request.Builder()
		  .url("https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts/14537780/payments/money-transfers")
		  .method("POST", body)
		  .addHeader("X-Time-Zone", "Europe/Rome")
		  .addHeader("Auth-Schema", "S2S")
		  .addHeader("apikey", "FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP")
		  .addHeader("Content-Type", "application/json")
		  .build();
		try {
			response = client.newCall(request).execute();
		} catch (IOException e) {
			throw new ClientException(e);
		}
	    return response;
	}
	
}
