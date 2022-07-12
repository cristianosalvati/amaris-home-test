package com.amaris.hometest.service;

import java.time.ZonedDateTime;

import com.amaris.hometest.domainobject.TransactionLogDO;
import com.amaris.hometest.domainvalue.OperationStatus;
import com.amaris.hometest.domainvalue.OperationType;
import com.amaris.hometest.exception.ConstraintsViolationException;
import com.amaris.hometest.exception.EntityNotFoundException;

public interface TransactionLogServiceI {

	TransactionLogDO findById(Long transactionId) throws EntityNotFoundException;

	TransactionLogDO create(TransactionLogDO transactionDO) throws ConstraintsViolationException;

    void delete(Long transactionId) throws EntityNotFoundException;

    void update(long transactionId, ZonedDateTime operationDate, 
			OperationType operationType,
			OperationStatus operationStatus,
			String description) throws EntityNotFoundException;

}
