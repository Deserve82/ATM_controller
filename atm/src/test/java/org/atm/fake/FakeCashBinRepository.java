package org.atm.fake;

import org.atm.repository.CashBinRepository;

public class FakeCashBinRepository implements CashBinRepository {
    private int currentCash;

    public FakeCashBinRepository(int initialCash) {
        this.currentCash = initialCash;
    }

    @Override
    public int getCurrentCash() {
        return currentCash;
    }

    @Override
    public boolean deposit(int amount) {
        if (amount <= 0) {
            return false;
        }
        currentCash += amount;
        return true;
    }

    @Override
    public boolean withdraw(int amount) {
        if (amount <= 0 || currentCash < amount) {
            return false;
        }
        currentCash -= amount;
        return true;
    }
}
