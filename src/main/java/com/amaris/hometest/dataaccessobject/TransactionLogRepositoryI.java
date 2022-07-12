package com.amaris.hometest.dataaccessobject;

import org.springframework.data.repository.CrudRepository;

import com.amaris.hometest.domainobject.TransactionLogDO;

public interface TransactionLogRepositoryI  extends CrudRepository<TransactionLogDO, Long>{


}
