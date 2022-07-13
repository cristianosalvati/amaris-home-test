package com.amaris.hometest.datatransferobject;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import net.bytebuddy.description.type.TypeDefinition.SuperClassIterator;

/**
 * A DTO implementing a balance account resume
 * {
  "date": "2018-08-17",
  "balance": 29.64,
  "availableBalance": 29.64,
  "currency": "EUR"
  }
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@EqualsAndHashCode
public class BalanceDTO extends AbstractDto{
	
	@NotNull(message = "Date can not be null!")
	private Date date;
	
	@NotNull(message = "Balance can not be null!")
	private BigDecimal balance;

	private BigDecimal availableBalance;

	@NotNull(message = "Currency can not be null!")
	private String currency;
	
	private BalanceDTO(Date date, String balance, String availableBalance, String currency) throws ParseException {
		super.setStatus("OK");
		this.date = date;
		this.balance = new BigDecimal(balance);
		this.availableBalance = new BigDecimal(availableBalance);
		this.currency = currency;
	}
	
	public static class BalanceDTOBuilder
    {
		private String datePattern;
		private String date;
		private String balance;
		private String availableBalance;
		private String currency; 
		
		public BalanceDTOBuilder setDatePattern(String datePattern)
	    {
	            this.datePattern = datePattern;
	            return this;
	    }
        public BalanceDTOBuilder setDate(String date)
        {
            this.date = date;
            return this;
        }

        public BalanceDTOBuilder setBalance(String balance) {
			this.balance = balance;
			return this;
		}
        
        public BalanceDTOBuilder setBalance(Double balance) {
     			this.balance = balance.toString();
     			return this;
     		}

		public BalanceDTOBuilder setAvailableBalance(String availableBalance) {
			this.availableBalance = availableBalance;
			return this;
		}
		
		public BalanceDTOBuilder setAvailableBalance(Double availableBalance) {
			this.availableBalance = availableBalance.toString();
			return this;
		}

		public BalanceDTOBuilder setCurrency(String currency) {
			this.currency = currency;
			return this;
		}

		public BalanceDTO createBalanceDTO() throws ParseException
        {
			Date dateD = (datePattern != null) ? new SimpleDateFormat(datePattern).parse(date) : new SimpleDateFormat("yyyy-MM-dd").parse(date);
			return new BalanceDTO(dateD, balance, availableBalance, currency);
        }
    } 
}
