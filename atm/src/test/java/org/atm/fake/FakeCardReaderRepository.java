package org.atm.fake;

import org.atm.repository.CardReaderRepository;

public class FakeCardReaderRepository implements CardReaderRepository {
    private final String pinNumber;

    public FakeCardReaderRepository(String pinNumber) {
        this.pinNumber = pinNumber;
    }

    @Override
    public String getPINNumber() {
        return pinNumber;
    }
}
