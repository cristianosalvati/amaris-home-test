package com.amaris.hometest.domainobject;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.amaris.hometest.domainvalue.OperationStatus;
import com.amaris.hometest.domainvalue.OperationType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(
    name = "transaction_log"
)
@Getter
@Setter
public class TransactionLogDO {

	@Id
    @GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	
    @Column(name = "operation_date", nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime operationDate = ZonedDateTime.now();
    
	@Column(name = "operation_type", nullable = false)
	@NotNull(message = "Operation type can not be null!")
    @Enumerated(EnumType.STRING)
	private OperationType operationType;
	
	@Column(name = "operation_status", nullable = false)
	@NotNull(message = "Operation Status can not be null!")
    @Enumerated(EnumType.STRING)
	private OperationStatus operationStatus;
	
	@Column(nullable = false)
	private String description;
	
	public TransactionLogDO() {}
	
	public TransactionLogDO(
			Long transactionId, 
			ZonedDateTime operationDate, 
			OperationType operationType,
			OperationStatus operationStatus,
			String description) {
		this.id = transactionId;
		this.operationDate = operationDate;
		this.operationType = operationType;
		this.operationStatus = operationStatus;
		this.description = description;
	}
	
}
