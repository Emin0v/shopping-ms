package com.company.api;

import com.company.dto.CardDto;
import com.company.dto.customer.PayReqDto;
import com.company.dto.customer.RollbackReqDto;
import com.company.service.PaymentService;
import com.company.util.ApiPaths;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPaths.PaymentCtrl.CTRL)
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/pay")
    public ResponseEntity<Void> pay(@RequestBody @Validated PayReqDto reqDto) {
        paymentService.pay(reqDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/rollback")
    public ResponseEntity<Void> rollback(@RequestBody @Validated RollbackReqDto reqDto) {
        paymentService.rollback(reqDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/top")
    public ResponseEntity<Void> top(@RequestBody @Validated CardDto cardDto) {
        paymentService.top(cardDto);
        return ResponseEntity.ok().build();
    }
}
