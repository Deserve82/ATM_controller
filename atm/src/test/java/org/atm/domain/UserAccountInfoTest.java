package org.atm.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserAccountInfoTest {

    @Test
    @DisplayName("계좌 정보를 정상적으로 생성할 수 있다")
    void createUserAccountInfo() {
        // Given & When
        UserAccountInfo accountInfo = new UserAccountInfo("ACC001", "Checking Account");

        // Then
        assertEquals("ACC001", accountInfo.getAccountId());
        assertEquals("Checking Account", accountInfo.getDisplayInfo());
    }

    @Test
    @DisplayName("accountId가 null이면 예외가 발생한다")
    void throwExceptionWhenAccountIdIsNull() {
        // Given & When & Then
        assertThrows(IllegalArgumentException.class, () -> {
            new UserAccountInfo(null, "Checking Account");
        });
    }

    @Test
    @DisplayName("accountId가 빈 문자열이면 예외가 발생한다")
    void throwExceptionWhenAccountIdIsBlank() {
        // Given & When & Then
        assertThrows(IllegalArgumentException.class, () -> {
            new UserAccountInfo("", "Checking Account");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new UserAccountInfo("   ", "Checking Account");
        });
    }

    @Test
    @DisplayName("displayInfo는 null이어도 된다")
    void displayInfoCanBeNull() {
        // Given & When
        UserAccountInfo accountInfo = new UserAccountInfo("ACC001", null);

        // Then
        assertEquals("ACC001", accountInfo.getAccountId());
        assertNull(accountInfo.getDisplayInfo());
    }
}