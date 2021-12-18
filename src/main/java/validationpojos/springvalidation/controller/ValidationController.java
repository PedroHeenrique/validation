package validationpojos.springvalidation.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import validationpojos.springvalidation.model.UserModel;
import validationpojos.springvalidation.repository.UserRepository;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class ValidationController {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Autowired
    public ValidationController(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<UserModel>> listTodos(){
        return ResponseEntity.ok(userRepository.findAll());
    }

    @PostMapping()
    public ResponseEntity<UserModel>save(@RequestBody @Valid UserModel user){
        user.setPassword(encoder.encode(user.getPassword()));
        return ResponseEntity.ok(userRepository.save(user));
    }


}
