package org.atm.api;

import org.atm.application.ATMService;
import org.atm.application.Response;

public class ATMController {
    private final ATMService atmService;

    public ATMController(ATMService atmService) {
        this.atmService = atmService;
    }

    public Response insertCard() {
        return atmService.insertCard();
    }
}
