package org.atm.repository;

public interface CashBinRepository {
    int getCurrentCash();
    boolean deposit(int amount);
    boolean withdraw(int amount);
}
