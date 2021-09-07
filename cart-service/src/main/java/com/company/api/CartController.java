//package com.company.api;
//
//import com.company.dto.CartRespDto;
//import com.company.dto.product.CartProductDto;
//import com.company.service.CartService;
//import com.company.util.ApiPaths;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping(ApiPaths.CartCtrl.CTRL)
//public class CartController {
//
//    private final CartService cartService;
//
//    @GetMapping("/{id}")
//    public ResponseEntity<CartRespDto> findByCartId(@PathVariable("id") String id){
//        return ResponseEntity.ok(cartService.findByCartId(id));
//    }
//
//    @PostMapping("/{id}")
//    public ResponseEntity<HttpStatus> createCart(@PathVariable("id") String id){
//        cartService.createCart(id);
//        return ResponseEntity.ok(HttpStatus.CREATED);
//    }
//
//    @PostMapping("/{id}")
//    public ResponseEntity<CartRespDto> findByCartId(@PathVariable("id") String id,
//                                                    @RequestBody CartProductDto cartProductDto){
//        return ResponseEntity.ok(cartService.addProductToCart(id, cartProductDto));
//    }
//}
