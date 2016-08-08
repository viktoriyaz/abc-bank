package com.abc.account;

import com.abc.ABCBankUtil.AccountType;

public class CheckingAccount extends Account {

	public CheckingAccount() {
		super(AccountType.CHECKING);
	}

    @Override
	public double getInterestEarned() {
    	double amount = getTotal();
    	return amount * 0.001;  // 0.1% interest
    }

}
