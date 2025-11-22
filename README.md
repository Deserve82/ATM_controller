# ATM Controller

Simple ATM controller implementation.

## Requirements

- Java 17+
- Maven 3.6+

## Build

```bash
cd atm
mvn clean compile
```

## Run Tests

```bash
cd atm
mvn test
```

## Test Cases

- 정상적인 PIN 입력 시 인증 및 계좌 목록 조회
- 잘못된 PIN 입력 시 에러 처리
- 잔액 조회
- 입금 (은행 잔액 + ATM 현금 보관함)
- 출금 (은행 잔액 - ATM 현금 보관함)
- ATM 현금 부족 시 출금 실패

## Architecture

```
api/
  ATMController - entry point

application/
  ATMService - main business logic
  ATMState - IDLE, CARD_INSERTED, AUTHENTICATED
  Response - code, message, data

domain/
  UserAccountInfo

repository/ (interfaces)
  CardReaderRepository
  BankAPIRepository
  CashBinRepository
```

Repository들은 인터페이스로 정의해서 나중에 실제 은행 API나 하드웨어 연동하기 쉽게 만들었습니다.
테스트에서는 Fake 구현체 사용했습니다.

## Notes

- Response code: 0 = success, 그 외 = error code
- 금액은 integer (1 dollar bills only, no cents)
- State 관리: IDLE -> CARD_INSERTED -> AUTHENTICATED
