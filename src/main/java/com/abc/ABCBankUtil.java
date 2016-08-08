package com.abc;

import java.util.Date;

public final class ABCBankUtil {

	public static final String SPACE = " ", BREAK = "\n", DASH = " - ", OPEN_PAREN = " (", CLOSE_PAREN = ") ";
	public static final String CUSTOMER_SUMMARY = "Customer Summary";
	public static final String ACCONT = "account", ACCOUNTS = "accounts";
	public static final String STATEMENT_FOR = "Statement for";
	public static final String TOTAL = "Total", TOTAL_ALL_ACCOUNTS = "Total In All Accounts";
	public static final Object TRANSACTION_LOCK = new Object();
	public static final int DAYS_BACK = 10;
	
	public static enum AccountType {
		CHECKING("Checking Account"),
		SAVINGS("Savings Account"),
		MAXI_SAVINGS("Maxi Savings Account");

		private final String _displayName;

		private AccountType(final String displayName) {
			_displayName = displayName;
		}

		public String getDisplayName() {
			return _displayName;
		}

		@Override
		public String toString() {
			return getDisplayName();
		}
	}

	public static enum TransactionType {
		DEPOSIT("deposit"),
		WITHDRAWAL("withdrawal");

		private final String _displayName;

		private TransactionType (final String displayName) {
			_displayName = displayName;
		}

		public String getDisplayName() {
			return _displayName;
		}

		@Override
		public String toString() {
			return getDisplayName();
		}
	}

	public static boolean isDateBeforeXDaysBack(final Date dateToCheck, final int daysBack) {
		if (dateToCheck == null) {
			return true;
		}

		final Date now = DateProvider.getInstance().now();
		final Date xDaysBack = DateProvider.addDays(now, 0 - DAYS_BACK);
		return dateToCheck.before(xDaysBack);
	}
    
}
