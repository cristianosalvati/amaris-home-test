package com.amaris.hometest.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.amaris.hometest.datatransferobject.AbstractDto;
import com.amaris.hometest.datatransferobject.AccountDTO;
import com.amaris.hometest.datatransferobject.BalanceDTO;
import com.amaris.hometest.datatransferobject.PaymentDTO;
import com.amaris.hometest.datatransferobject.TransactionDTO;
import com.amaris.hometest.exception.ClientException;
import com.amaris.hometest.exception.EntityNotFoundException;
import com.amaris.hometest.exception.ErrorException;
import com.amaris.hometest.exception.MapperException;
import com.amaris.hometest.rest.client.SandboxApiClient;
import com.amaris.hometest.service.BalanceServiceI;
import com.amaris.hometest.service.PaymentServiceI;
import com.amaris.hometest.service.TransactionServiceI;
import com.amaris.hometest.util.EntityMapper;

/**
 * </p>
 * Un controller Rest che permette di gestire le seguenti operazioni sul conto:
 * - Lettura saldo (BALANCE); 
 * - Lista di transazioni (TRANSACTION: TO BE DEFINE)
 * - Bonifico (PAYMENT: TBD);
 * <p/>
 * 
 * @author cristiano 
 */

@RestController
@RequestMapping("/rest/v1/account")
public class AccountController {

	@Value("${application.configuration.datePattern}")
	private String datePattern;
	
	private static final Logger LOG = LoggerFactory.getLogger(AccountController.class);
	
	@Autowired
	private SandboxApiClient apiClient;
	
	private final BalanceServiceI balanceService;
	private final PaymentServiceI paymentService;
	private final TransactionServiceI transactionService	;
	
	@Autowired
	public AccountController(
			final BalanceServiceI balanceService, 
			final PaymentServiceI paymentService,
			final TransactionServiceI transactionService
			) {
		
		this.balanceService = balanceService;
		this.paymentService = paymentService;
		this.transactionService = transactionService;
	}
	
/**
 * 	Recupero dei dati di riepilogo del saldo
 *  @param accountId	the unique id related to the account
 *  @throws EntityNotFoundException
 *  @throws ClientException 
 *  @throws ErrorException 
 *  @throws MapperException 
 **/
	@GetMapping("/balance/{accountId}")
	public BalanceDTO getBalance(@PathVariable long accountId) throws EntityNotFoundException, ClientException, ErrorException, MapperException
	{
			String response = apiClient.balance(accountId);
			AbstractDto responseDto = EntityMapper.makeBalanceDto(response, datePattern);
			if (responseDto.getStatus().equals("OK"))
				return (BalanceDTO)responseDto;
			else throw new ErrorException(responseDto.getStatus(), responseDto.getMessage());
		
	}
	
/**
 * 	Definizione e registrazione di un bonifico
 *  @param accountDTO	the account informations
 *  @throws EntityNotFoundException
 *  @throws ClientException 
 **/
	@PostMapping("payment-transfer/{accountId}")
	@ResponseStatus(HttpStatus.CREATED)
	public PaymentDTO paymentTranfer(@Valid @RequestBody AccountDTO accountDTO) throws EntityNotFoundException, ClientException
	{
		try {
			String response = apiClient.payment(accountDTO);
//			TODO: implementare logica di verifica e validazione della risposta
//			...
		}catch(ClientException e) {
//			LOG.error(e.getMessage());
			throw e;
		}
	    return new PaymentDTO(); 
	}

/**
 * 	Recupero delle transazioni relative ad un certo Account
 *  @param accountId	the unique id related to the account
 *  @param startDate   	the starting date to retrieve informations 
 *  @param endDate   	the ending date to retrieve informations 
 *  @throws MapperException 
 *  @throws EntityNotFoundException
 *  @throws ClientException 
 **/
	@GetMapping("/transactions/{accountId}/{startDate}/{endDate}")
	public List<TransactionDTO> transactions(@PathVariable long accountId, @PathVariable String startDate, @PathVariable String endDate) throws EntityNotFoundException, MapperException, ClientException
	{
		try {
			String response = apiClient.transaction(accountId,
					new SimpleDateFormat(datePattern).parse(startDate),
					new SimpleDateFormat(datePattern).parse(endDate));
			
//			TODO: implementare logica di verifica e validazione della risposta.
//			Gestire e validare il formato data in input (2022-07-10)
		}catch(ClientException e) {
			throw e;
		} catch (ParseException e) {
			throw new MapperException(e);
		}
	    return new ArrayList<TransactionDTO>();
	}
	
	public void setApiClient(SandboxApiClient apiClient) {
		if (this.apiClient == null)
			this.apiClient = apiClient;
	}
	
	public SandboxApiClient getApiClient() {
		return this.apiClient;
	}
}
