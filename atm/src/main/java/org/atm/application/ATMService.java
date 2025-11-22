package org.atm.application;

import org.atm.domain.ATMState;
import org.atm.domain.UserAccountInfo;
import org.atm.repository.BankAPIRepository;
import org.atm.repository.CardReaderRepository;
import org.atm.repository.CashBinRepository;

import java.util.ArrayList;
import java.util.List;

public class ATMService {
    private ATMState state;
    private List<UserAccountInfo> accounts;

    private final CardReaderRepository cardReaderRepository;
    private final BankAPIRepository bankAPIRepository;
    private final CashBinRepository cashBinRepository;

    public ATMService(
            CardReaderRepository cardReaderRepository,
            BankAPIRepository bankAPIRepository,
            CashBinRepository cashBinRepository
    ) {
        this.cardReaderRepository = cardReaderRepository;
        this.bankAPIRepository = bankAPIRepository;
        this.cashBinRepository = cashBinRepository;
        this.state = ATMState.IDLE;
        this.accounts = new ArrayList<>();
    }

    public Response insertCard() {

        return new Response(0, "Card inserted successfully", accounts);
    }
    public ATMState getState() {
        return state;
    }

    public List<UserAccountInfo> getAccounts() {
        return new ArrayList<>(accounts);
    }
}
