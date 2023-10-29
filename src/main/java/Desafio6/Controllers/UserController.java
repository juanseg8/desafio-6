package Desafio6.Controllers;

import Desafio6.Models.Dtos.UserAddDTO;
import Desafio6.Models.Dtos.UserEditDTO;
import Desafio6.Models.Dtos.UserReadDTO;
import Desafio6.Services.UserService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Page<UserReadDTO>> findAll(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size) {
        return ResponseEntity.ok(userService.findAll(page, size));
    }
    @PostMapping
    public ResponseEntity<UserReadDTO> add(@RequestBody UserAddDTO userAddDTO){
        return ResponseEntity.ok(userService.add(userAddDTO));
    }
    @GetMapping("/{userId}")
    public ResponseEntity<UserReadDTO> findById(@PathVariable Integer userId){
        return ResponseEntity.ok(userService.findById(userId));
    }
    @PutMapping("/{userId}")
    public ResponseEntity<UserReadDTO> edit(
            @PathVariable Integer userId,
            @RequestBody UserEditDTO user){
        return ResponseEntity.ok(userService.edit(userId, user));
    }
    @DeleteMapping("/{userId}")
    public ResponseEntity<UserReadDTO> deleteById(@PathVariable Integer userId){
        return ResponseEntity.ok(userService.deleteById(userId));
    }
}
