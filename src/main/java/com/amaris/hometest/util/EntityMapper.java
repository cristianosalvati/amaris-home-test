package com.amaris.hometest.util;

import java.text.ParseException;
import java.util.Map;

import com.amaris.hometest.datatransferobject.AbstractDto;
import com.amaris.hometest.datatransferobject.AccountDTO;
import com.amaris.hometest.datatransferobject.BalanceDTO;
import com.amaris.hometest.datatransferobject.BalanceDTO.BalanceDTOBuilder;
import com.amaris.hometest.datatransferobject.ErrorDTO;
import com.amaris.hometest.datatransferobject.PaymentDTO;
import com.amaris.hometest.datatransferobject.TransactionDTO;
import com.amaris.hometest.exception.MapperException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EntityMapper {

	public static AbstractDto makeBalanceDto(String json, String datePattern) throws MapperException {
		try {
			Map<String, Object> entity = (new ObjectMapper()).readValue(json, Map.class);
			BalanceDTO.BalanceDTOBuilder builder = new BalanceDTOBuilder();
			if (entity.get("status") == null || !entity.get("status").equals("OK")) {
				return new ErrorDTO(entity.get("status").toString(), 
						entity.get("error").toString());
			}else {
			Map<String, Object> payload = (Map)entity.get("payload");

			builder.setDatePattern(datePattern).setDate((String)payload.get("date"))
				.setBalance((Double)payload.get("balance"))
				.setAvailableBalance((Double)payload.get("availableBalance"))
				.setCurrency((String)payload.get("currency"));
			
			return builder.createBalanceDTO();
			}
			
		} catch (JsonProcessingException e) {
			throw new MapperException(e);
		} catch (ParseException e) {
			throw new MapperException(e);
		}
		
	}
	
	public static AbstractDto makeAccountDto(String json) {
//		TODO
		return null;
	}
	
	public static AbstractDto makePaymentDTO(String json) {
//		TODO
		return null;
	}
	
	public static AbstractDto makeTransactionDto(String json) {
//		TODO
		return null;
	}
	
}
