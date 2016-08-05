package com.abc.account;

import com.abc.ABCBankUtil.AccountType;

public class SavingsAccount extends Account {

	public SavingsAccount() {
		super(AccountType.SAVINGS);
	}

	@Override
    public double interestEarned() {
    	double amount = getTotal();
    	if (amount <= 1000) {
    		return amount * 0.001;
    	}
    	else {
    		return 1 + (amount-1000) * 0.002;  // 1 is interest on the first 1000
    	}
    }

}
