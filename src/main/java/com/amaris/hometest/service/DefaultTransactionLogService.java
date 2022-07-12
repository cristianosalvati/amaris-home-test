package com.amaris.hometest.service;

import java.time.ZonedDateTime;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amaris.hometest.dataaccessobject.TransactionLogRepositoryI;
import com.amaris.hometest.domainobject.TransactionLogDO;
import com.amaris.hometest.domainvalue.OperationStatus;
import com.amaris.hometest.domainvalue.OperationType;
import com.amaris.hometest.exception.ConstraintsViolationException;
import com.amaris.hometest.exception.EntityNotFoundException;

@Service
public class DefaultTransactionLogService implements TransactionLogServiceI{

	private static final Logger LOG = LoggerFactory.getLogger(DefaultTransactionLogService.class);
	
	private final TransactionLogRepositoryI transactionLogRepository;

    public DefaultTransactionLogService(final TransactionLogRepositoryI transactionLogRepository)
    {
        this.transactionLogRepository = transactionLogRepository;
    }
    
	@Override
	public TransactionLogDO findById(Long transactionId) throws EntityNotFoundException {		
		Optional<TransactionLogDO> optionalTransactionLogDO = this.transactionLogRepository.findById(transactionId);
		if (optionalTransactionLogDO.isPresent()) {
			TransactionLogDO TransactionLogDO = optionalTransactionLogDO.get();
			return TransactionLogDO;	
		}else throw new EntityNotFoundException("Could not find transaction entity with id: " + transactionId);
	}

	@Override
	public TransactionLogDO create(TransactionLogDO TransactionLogDO) throws ConstraintsViolationException {
		TransactionLogDO entity ;
        try
        {
            entity  = transactionLogRepository.save(TransactionLogDO);
        }
        catch (DataIntegrityViolationException e)
        {
            LOG.warn("ConstraintsViolationException while creating a TRANSACTION: {}", TransactionLogDO, e);
            throw new ConstraintsViolationException(e.getMessage());
        }
        return entity ;
	}

	 /**
     * Deletes an existing car by id. This method implements a "LOGICAL" deletion.
     *
     * @param transactionId
     * @throws EntityNotFoundException if no driver with the given id was found.
     */
	@Override
    @Transactional
	public void delete(Long transactionId) throws EntityNotFoundException {
        Optional<TransactionLogDO> carOptionalDO = transactionLogRepository.findById(transactionId);
        if(carOptionalDO.isPresent()) {
        	TransactionLogDO TransactionLogDO = carOptionalDO.get();
        	TransactionLogDO.setOperationStatus(OperationStatus.DELETED);
        }else throw new EntityNotFoundException("TRANSACTION not found for id "+transactionId);
        		
	}

	@Override
    @Transactional
	public void update(long transactionId, ZonedDateTime operationDate, OperationType operationType,
			OperationStatus operationStatus, String description) throws EntityNotFoundException {
		Optional<TransactionLogDO> carOptionalDO = transactionLogRepository.findById(transactionId);
        if(carOptionalDO.isPresent()) {
        	TransactionLogDO TransactionLogDO = carOptionalDO.get();
        	TransactionLogDO.setOperationDate(operationDate);
        	TransactionLogDO.setOperationType(operationType);
        	TransactionLogDO.setOperationStatus(operationStatus);
        	TransactionLogDO.setDescription(description);
        }else throw new EntityNotFoundException("TRANSACTION not found for id "+transactionId);

	}

}
