package com.company.api;

import com.company.dto.ProductCreateReqDto;
import com.company.dto.ProductResDto;
import com.company.service.ProductService;
import com.company.util.ApiPaths;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPaths.ProductCtrl.CTRL)
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{uuid}")
    public ResponseEntity<ProductResDto> getById(@PathVariable("uuid") String uuid) {
        return ResponseEntity.ok(productService.getById(uuid));
    }

    @GetMapping
    public ResponseEntity<List<ProductResDto>> getAll(@RequestParam Integer pageNo,
                                                      @RequestParam Integer pageSize) {
        return ResponseEntity.ok(productService.getAll(pageNo, pageSize));
    }

    @GetMapping("/getAllSorted")
    public ResponseEntity<List<ProductResDto>> getAllSorted(){
        return ResponseEntity.ok(productService.getAllSorted());
    }

    @PostMapping
    public ResponseEntity<ProductResDto> create(@RequestBody ProductCreateReqDto productCreateReqDto) {
        return ResponseEntity.status(CREATED).body(productService.create(productCreateReqDto));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> delete(@PathVariable String uuid) {
        productService.delete(uuid);
        return ResponseEntity.noContent().build();
    }
}
