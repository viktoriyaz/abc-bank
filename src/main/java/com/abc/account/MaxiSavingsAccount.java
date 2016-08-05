package com.abc.account;

import com.abc.ABCBankUtil;
import com.abc.ABCBankUtil.AccountType;

public class MaxiSavingsAccount extends Account {

	public MaxiSavingsAccount() {
		super(AccountType.MAXI_SAVINGS);
	}

	@Override
	public double interestEarned() {
		double amount = getTotal();
		if ((_lastWithdrawalTimestamp == null) || (ABCBankUtil.isDateBeforeXDaysBack(_lastWithdrawalTimestamp, ABCBankUtil.DAYS_BACK))) {
			return amount * 0.05;
		}
		return amount * 0.001;
    }

}
