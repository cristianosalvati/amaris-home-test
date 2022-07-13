package com.amaris.hometest.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;

import com.amaris.hometest.datatransferobject.BalanceDTO;
import com.amaris.hometest.datatransferobject.PaymentDTO;
import com.amaris.hometest.datatransferobject.TransactionDTO;
import com.amaris.hometest.rest.client.SandboxApiClient;
import com.amaris.hometest.service.BalanceServiceI;
import com.amaris.hometest.service.PaymentServiceI;
import com.amaris.hometest.service.TransactionLogServiceI;
import com.amaris.hometest.service.TransactionServiceI;
import com.amaris.hometest.util.EntityMapper;

class AccountControllerTest {

	private static final Logger LOG = LoggerFactory.getLogger(AccountControllerTest.class);
	
	@Autowired
	private WebApplicationContext context;
	 
	@Mock
	private SandboxApiClient apiClient;
	
	@Mock
	private TransactionLogServiceI transactionLogService;
	
	@Mock
	private BalanceServiceI balanceService;
	
	@Mock
	private PaymentServiceI paymentServiceI;
	
	@Mock
	private TransactionServiceI transactionService;
	
	@InjectMocks
	private AccountController accountController;
	 
	private BalanceDTO balanceReponse;
	
	private PaymentDTO paymentResponse;
	
	private List<TransactionDTO> transactionsResponse;
	
	@BeforeEach
	void setMock() {
			try {
//				here some initialization
				MockitoAnnotations.initMocks(this);
				
				balanceReponse = (new BalanceDTO.BalanceDTOBuilder())
						.setAvailableBalance("50007.32")
						.setBalance("50007.32")
						.setCurrency("EUR")
						.setDate("2022-07-13")
						.setDatePattern("yyyy-MM-dd").createBalanceDTO();
				
	    		accountController.setApiClient(apiClient);
	    		accountController.getApiClient().setClientApiKey("FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP");
				accountController.getApiClient().setClientAuthSchema("S2S");
				accountController.getApiClient().setClientBaseUrl("https://mock-api.platfr.io");
				accountController.getApiClient().setClientConnectionTimeoutValue(90000);
				accountController.getApiClient().setClientTimezone("Europe/Rome");
				accountController.getApiClient().setDatePattern("yyyy-MM-dd");
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
	 }
	
	@Test
	@DisplayName("test get Balance resume Operation")
	void testGetBalance() {
		try {
			Long accountId = (long)14537780;
			
			String clientResponse = "{\r\n"
					+ "    \"status\": \"OK\",\r\n"
					+ "    \"error\": [],\r\n"
					+ "    \"payload\": {\r\n"
					+ "        \"date\": \"2022-07-13\",\r\n"
					+ "        \"balance\": 50007.32,\r\n"
					+ "        \"availableBalance\": 50007.32,\r\n"
					+ "        \"currency\": \"EUR\"\r\n"
					+ "    }\r\n"
					+ "}";
			
    		lenient().when(apiClient.balance(accountId)).thenReturn(clientResponse);
//    			EntityMapper.makeBalanceDto(response, datePattern);

    		BalanceDTO reponse = accountController.getBalance(accountId);
    		verify(apiClient,times(1)).balance(accountId);
    		assertEquals(balanceReponse, reponse);
//    		assertEquals(balanceReponse.getBalance(), reponse.getBalance());
		}catch(Exception e) {
			fail(e.getMessage());
		}
		
	}
	
	@Test
	@DisplayName("test payment transfer operation")
	void testPaymentTranfer() {
//		fail("Not yet implemented");
		LOG.warn("TODO: IMPLEMENT THIS TEST");
	}
	
	@Test
	@DisplayName("test transaction List ")
	void testTransactionList() {
//		fail("Not yet implemented");
		LOG.warn("TODO: IMPLEMENT THIS TEST");
	}
	

}
