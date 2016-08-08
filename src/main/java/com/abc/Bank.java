package com.abc;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class Bank {
    
	private final Map<Integer, Customer> _customerMap = new LinkedHashMap<Integer, Customer>(); // insertion order
    
    public void addCustomer(final Customer customer) {
    	if (_customerMap.containsKey(customer.getSSN())) {
    		throw new RuntimeException("The customer with SSN " + customer.getSSN() + " is already a client in this bank. Operation addCustomer failed."); // can add more logging here to compare old and new customers
    	}
    	
    	System.out.println("Adding Customer " + customer.getFullName());  // TODO: add proper logging here
    	_customerMap.put(customer.getSSN(), customer);
    }

    public Customer getFirstCustomer() {
    	if (!_customerMap.isEmpty()) {
    		return _customerMap.values().iterator().next();
    	}

    	// TODO: if more error handling is needed here, it could be implemented through logging or SNMP Trap
    	return null;
    }

    public Collection<Customer> getAllCustomers() {
    	return Collections.unmodifiableCollection(_customerMap.values());
    }
    
    public double getTotalInterestPaid() {
        double total = 0d;
        for(final Customer customer : _customerMap.values()) {
            total += customer.getTotalInterestEarned();
        }
        return total;
    }

    // TODO: this should convert to reporting framework
    public String printCustomerSummary() {
        final StringBuffer summaryBuffer = new StringBuffer(ABCBankUtil.CUSTOMER_SUMMARY);
        for (final Customer customer : _customerMap.values()) {
        	summaryBuffer.append(ABCBankUtil.BREAK).append(ABCBankUtil.DASH);
        	summaryBuffer.append(customer.getFullName());
        	int numberOfAccounts = customer.getNumberOfAccounts();
        	summaryBuffer.append(ABCBankUtil.OPEN_PAREN).append(numberOfAccounts).append(ABCBankUtil.SPACE).append( (numberOfAccounts == 1) ? ABCBankUtil.ACCONT : ABCBankUtil.ACCOUNTS).append(ABCBankUtil.CLOSE_PAREN);
        }
        return summaryBuffer.toString().trim();
    }

}
