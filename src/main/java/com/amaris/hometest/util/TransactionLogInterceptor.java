package com.amaris.hometest.util;

import java.time.ZonedDateTime;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

import com.amaris.hometest.domainobject.TransactionLogDO;
import com.amaris.hometest.domainvalue.OperationStatus;
import com.amaris.hometest.domainvalue.OperationType;
import com.amaris.hometest.exception.ConstraintsViolationException;
import com.amaris.hometest.service.TransactionLogServiceI;

@Component
public class TransactionLogInterceptor implements AsyncHandlerInterceptor
{
    private static final Log LOG = LogFactory.getLog(TransactionLogInterceptor.class);

    @Autowired
    TransactionLogServiceI transactionLogService;
    
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
    {
        StringBuilder logMessage = new StringBuilder();
        logMessage.append("method: ").append(request.getMethod()).append("\t");
        logMessage.append("uri: ").append(request.getRequestURI()).append("\t");
        logMessage.append("status: ").append(response.getStatus()).append("\t");
        logMessage.append("remoteAddress: ").append(request.getRemoteAddr()).append("\t");

        if (ex != null)
        {
            TransactionLogInterceptor.LOG.error(logMessage.toString(), ex);
            logTransaction(OperationStatus.REFUSED, ex.getMessage());
        }
        else
        {
            TransactionLogInterceptor.LOG.info(logMessage.toString());
            logTransaction(OperationStatus.VALIDATED, request.getRequestURI());
			
        }
    }

    private void logTransaction(OperationStatus status, String message) {
    	TransactionLogDO transactionLogDO = new TransactionLogDO(null,  ZonedDateTime.now(), OperationType.SYSTEM, status, message);
		try {
			transactionLogService.create(transactionLogDO);
		} catch (ConstraintsViolationException e) {
			 TransactionLogInterceptor.LOG.warn(e.toString());
		}
    }
}
