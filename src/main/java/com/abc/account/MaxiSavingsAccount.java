package com.abc.account;

import com.abc.ABCBankUtil;
import com.abc.ABCBankUtil.AccountType;

public class MaxiSavingsAccount extends Account {

	public MaxiSavingsAccount() {
		super(AccountType.MAXI_SAVINGS);
	}

	@Override
	public double getInterestEarned() {
		double amount = getTotal();
		if ((_lastWithdrawalTimestamp == null) || (ABCBankUtil.isDateBeforeXDaysBack(_lastWithdrawalTimestamp, ABCBankUtil.DAYS_BACK))) {
			return amount * 0.05; // 5% interest if no withdrawals within the last X days back (currently 10)
		}
		return amount * 0.001;  // 0.1% interest if withdrawals have been made
    }

}
