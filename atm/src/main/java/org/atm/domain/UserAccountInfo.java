package org.atm.domain;

public class UserAccountInfo {

    private final String accountId;
    private final String displayInfo;

    public UserAccountInfo(String accountId, String displayInfo) {
        if (accountId == null || accountId.isBlank()) {
            throw new IllegalArgumentException("Account ID cannot be null or empty");
        }
        this.accountId = accountId;
        this.displayInfo = displayInfo;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getDisplayInfo() {
        return displayInfo;
    }
}
