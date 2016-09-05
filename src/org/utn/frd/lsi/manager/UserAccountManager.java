package org.utn.frd.lsi.manager;

import org.utn.frd.lsi.domain.UserAccount;

public class UserAccountManager extends ObjectManager<UserAccount> {
	
	private static final String EMAIL = "email";
	
	public UserAccountManager() {
		super(UserAccount.class);
	}
	
	public UserAccount getByEmail(String email) {
		return getBy(EMAIL, email);
	}
	
	public UserAccount getByPendingResetCode(String pendingResetCode) {
		return getBy("passwordResetCode", pendingResetCode);
	}
}