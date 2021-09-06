package com.company.api;

import com.company.dto.customer.UserRespDto;
import com.company.dto.customer.UserUpdateDto;
import com.company.service.UserService;
import com.company.util.ApiPaths;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPaths.CustomerCtrl.CTRL)
public class UserController {

    private final UserService userService;

    @GetMapping("/{uuid}")
    public ResponseEntity<UserRespDto> getByUuid(@PathVariable("uuid") String uuid) {
        return ResponseEntity.ok(userService.findByUserUuid(uuid));
    }

    @GetMapping("/searchNameOrSurnameOrUsername")
    public ResponseEntity<List<UserRespDto>>
    searchNameOrSurnameOrUsername(@RequestParam(required = false) String name,
                                  @RequestParam(required = false) String surname,
                                  @RequestParam(required = false) String username) {

        return ResponseEntity.ok(userService.searchNameOrSurnameOrUsername(name, surname, username));
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<HttpStatus> update(@PathVariable(name = "uuid") String uuid,
                                              @RequestBody UserUpdateDto updateDto) {
        userService.update(uuid,updateDto);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("uuid") String uuid) {
        userService.delete(uuid);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
