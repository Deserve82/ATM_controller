package org.atm.repository;

import org.atm.domain.UserAccountInfo;

import java.util.List;

public interface BankAPIRepository {
    boolean checkPinNumber(String pin);
    List<UserAccountInfo> getUserAccountList(String pin);
    int getBalance(String accountId);
    boolean depositAccount(String accountId, int amount);
    boolean withdrawAccount(String accountId, int amount);
}
