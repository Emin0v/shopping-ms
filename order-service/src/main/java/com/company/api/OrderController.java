package com.company.api;

import com.company.dto.OrderCreateReqDto;
import com.company.dto.OrderResDto;
import com.company.service.OrderService;
import com.company.util.ApiPaths;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPaths.OrderCtrl.CTRL)
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody @Validated OrderCreateReqDto reqDto) {
        return ResponseEntity.status(CREATED).body(orderService.create(reqDto));
    }

    @GetMapping("/search/{pageNo}/{pageSize}")
    public ResponseEntity<Page<OrderResDto>> search(@PathVariable("pageNo") int pageNo,
                                                    @PathVariable("pageSize") int pageSize){
        return ResponseEntity.ok(orderService.search(pageNo,pageSize));
    }

    @PostMapping("/{uuid}/cancel")
    public ResponseEntity<Void> cancel(@PathVariable String uuid) {
        orderService.cancel(uuid);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{uuid}/finish")
    public ResponseEntity<Void> finish(@PathVariable String uuid) {
        orderService.finish(uuid);
        return ResponseEntity.ok().build();
    }
}
