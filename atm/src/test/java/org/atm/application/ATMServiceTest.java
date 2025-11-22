package org.atm.application;

import org.atm.domain.ATMState;
import org.atm.fake.FakeBankAPIRepository;
import org.atm.fake.FakeCardReaderRepository;
import org.atm.fake.FakeCashBinRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ATMServiceTest {

    private static final String VALID_PIN = "1234";
    private static final String INVALID_PIN = "0000";
    private static final String ACCOUNT_ID_1 = "ACC001";
    private static final String ACCOUNT_ID_2 = "ACC002";

    @Test
    @DisplayName("정상적인 PIN으로 카드를 삽입하면 상태가 AUTHENTICATED로 변경되고 계좌 목록이 설정된다")
    void insertCard_withValidPin_shouldUpdateStateToAuthenticatedAndSetAccounts() {
        // Given
        FakeCardReaderRepository cardReader = new FakeCardReaderRepository(VALID_PIN);
        FakeBankAPIRepository bankAPI = new FakeBankAPIRepository(VALID_PIN);
        bankAPI.addAccount(ACCOUNT_ID_1, "Checking Account", 1000);
        bankAPI.addAccount(ACCOUNT_ID_2, "Savings Account", 5000);
        FakeCashBinRepository cashBin = new FakeCashBinRepository(10000);

        ATMService atmService = new ATMService(cardReader, bankAPI, cashBin);

        // When
        Response response = atmService.insertCard();

        // Then
        assertTrue(response.isSuccess());
        assertEquals(ATMState.AUTHENTICATED, atmService.getState());
        assertEquals(2, atmService.getAccounts().size());
    }

    @Test
    @DisplayName("잘못된 PIN으로 카드를 삽입하면 상태가 CARD_INSERTED로 변경되고 계좌 목록이 비워진다")
    void insertCard_withInvalidPin_shouldUpdateStateToCardInsertedAndClearAccounts() {
        // Given
        FakeCardReaderRepository cardReader = new FakeCardReaderRepository(INVALID_PIN);
        FakeBankAPIRepository bankAPI = new FakeBankAPIRepository(VALID_PIN);
        bankAPI.addAccount(ACCOUNT_ID_1, "Checking Account", 1000);
        FakeCashBinRepository cashBin = new FakeCashBinRepository(10000);

        ATMService atmService = new ATMService(cardReader, bankAPI, cashBin);

        // When
        Response response = atmService.insertCard();

        // Then
        assertFalse(response.isSuccess());
        assertEquals(1, response.getCode());
        assertEquals(ATMState.CARD_INSERTED, atmService.getState());
        assertEquals(0, atmService.getAccounts().size());
    }

    @Test
    @DisplayName("인증된 사용자가 잔액 조회를 하면 계좌 잔액이 정상적으로 반환된다")
    void getBalance_withAuthenticatedUser_shouldReturnBalance() {
        // Given
        FakeCardReaderRepository cardReader = new FakeCardReaderRepository(VALID_PIN);
        FakeBankAPIRepository bankAPI = new FakeBankAPIRepository(VALID_PIN);
        bankAPI.addAccount(ACCOUNT_ID_1, "Checking Account", 1000);
        FakeCashBinRepository cashBin = new FakeCashBinRepository(10000);

        ATMService atmService = new ATMService(cardReader, bankAPI, cashBin);
        atmService.insertCard();

        // When
        Response response = atmService.getBalance(ACCOUNT_ID_1);

        // Then
        assertTrue(response.isSuccess());
        assertEquals(1000, response.getData());
    }
}
