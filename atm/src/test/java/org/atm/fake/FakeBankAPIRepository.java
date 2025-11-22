package org.atm.fake;

import org.atm.domain.UserAccountInfo;
import org.atm.repository.BankAPIRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FakeBankAPIRepository implements BankAPIRepository {
    private final String validPin;
    private final Map<String, Integer> accountBalances;
    private final List<UserAccountInfo> userAccounts;

    public FakeBankAPIRepository(String validPin) {
        this.validPin = validPin;
        this.accountBalances = new HashMap<>();
        this.userAccounts = new ArrayList<>();
    }

    public void addAccount(String accountId, String displayInfo, int initialBalance) {
        userAccounts.add(new UserAccountInfo(accountId, displayInfo));
        accountBalances.put(accountId, initialBalance);
    }

    @Override
    public boolean checkPinNumber(String pin) {
        return validPin.equals(pin);
    }

    @Override
    public List<UserAccountInfo> getUserAccountList(String pin) {
        if (!checkPinNumber(pin)) {
            return new ArrayList<>();
        }
        return new ArrayList<>(userAccounts);
    }

    @Override
    public int getBalance(String accountId) {
        return accountBalances.getOrDefault(accountId, 0);
    }

    @Override
    public boolean depositAccount(String accountId, int amount) {
        if (!accountBalances.containsKey(accountId)) {
            return false;
        }
        int currentBalance = accountBalances.get(accountId);
        accountBalances.put(accountId, currentBalance + amount);
        return true;
    }

    @Override
    public boolean withdrawAccount(String accountId, int amount) {
        if (!accountBalances.containsKey(accountId)) {
            return false;
        }
        int currentBalance = accountBalances.get(accountId);
        if (currentBalance < amount) {
            return false;
        }
        accountBalances.put(accountId, currentBalance - amount);
        return true;
    }
}
