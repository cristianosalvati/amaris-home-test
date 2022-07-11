package com.amaris.hometest.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amaris.hometest.datatransferobject.AccountDTO;
import com.amaris.hometest.datatransferobject.BalanceDTO;
import com.amaris.hometest.datatransferobject.PaymentDTO;
import com.amaris.hometest.datatransferobject.TransactionDTO;
import com.amaris.hometest.exception.ConstraintsViolationException;
import com.amaris.hometest.exception.EntityNotFoundException;
import com.amaris.hometest.rest.client.SandboxApiClient;
import com.amaris.hometest.rest.exception.ClientException;
import com.amaris.hometest.service.BalanceService;
import com.amaris.hometest.service.PaymentService;
import com.amaris.hometest.service.TransactionService;

import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * </p>
 * Un controller Rest che permette di gestire le seguenti operazioni sul conto:
 * - Lettura saldo;
 * - Lista di transazioni
 * - Bonifico;
 * <p/>
 * 
 * @author cristiano 
 */

@RestController
@RequestMapping("/rest/v1/account")
public class AccountController {

	private static final Logger LOG = LoggerFactory.getLogger(AccountController.class);
	
	private static SandboxApiClient apiClient = new SandboxApiClient();
	
	private final BalanceService balanceService;
	private final PaymentService paymentService;
	private final TransactionService transactionService	;
	
	@Autowired
	public AccountController(final BalanceService balanceService, 
			final PaymentService paymentService,
			final TransactionService transactionService) {
		
		this.balanceService = balanceService;
		this.paymentService = paymentService;
		this.transactionService = transactionService;
	}
	
	@GetMapping("/balance/{accountId}")
	public BalanceDTO getBalance(@PathVariable long accountId) throws EntityNotFoundException
	{
		try {
			Response response = apiClient.balance();
			System.out.println(response.body().toString());
		}catch(ClientException e) {
			
		}
	    return new BalanceDTO(); //CarMapper.makeBalanceDTO(service.findById(accountId));
	}
	
	@PostMapping("payment-transfer/{accountId}")
//	@ResponseStatus(HttpStatus.CREATED)
	public PaymentDTO paymentTranfer(@Valid @RequestBody AccountDTO accountDTO) throws EntityNotFoundException
	{
		try {
			Response response = apiClient.payment();
			System.out.println(response.body().toString());
		}catch(ClientException e) {
			
		}
	    return new PaymentDTO(); //Mapper.makePaymentDTO(service.findById(accountDTO));
	}
	
	@GetMapping("/transactions/{accountId}")
	public List<TransactionDTO> transactions(@PathVariable long accountId) throws ConstraintsViolationException
	{
		try {
			Response response = apiClient.transaction();
			System.out.println(response.body().toString());
		}catch(ClientException e) {
			
		}
	    return new ArrayList<TransactionDTO>();
	}
	
}
