package validationpojos.springvalidation.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import validationpojos.springvalidation.model.ClienteModel;
import validationpojos.springvalidation.model.UserModel;
import validationpojos.springvalidation.repository.UserRepository;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/user")
public class ValidationController {


    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Autowired
    public ValidationController(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @PostMapping("/salvar")
    private ResponseEntity<ClienteModel> salvarCliente(@RequestBody @Valid ClienteModel clienteModel){
        return ResponseEntity.ok(clienteModel);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<UserModel>> listTodos(){
        return ResponseEntity.ok(userRepository.findAll());
    }

    @PostMapping("/save")
    public ResponseEntity<UserModel>salvar(@RequestBody UserModel user){
        user.setPassword(encoder.encode(user.getPassword()));
        return ResponseEntity.ok(userRepository.save(user));
    }

    @GetMapping("/validarSenha")
    public ResponseEntity<Boolean> validarSenha(@RequestParam String login,
                                                @RequestParam String password){

        Optional<UserModel> optUser = userRepository.findByLogin(login);
        if(optUser.isEmpty()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
        }


       boolean valid = encoder.matches(password, optUser.get().getPassword());

        HttpStatus status = (valid) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;

        return ResponseEntity.status(status).body(valid);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handlerValidationException(MethodArgumentNotValidException exception){
        Map<String,String> errors = new HashMap<>();
        exception.getBindingResult()
                .getAllErrors()
                .forEach(erro -> {
                    String fieldName =((FieldError)erro).getField();
                    String errorMessage= erro.getDefaultMessage();
                    errors.put(fieldName,errorMessage);
                });
        return  errors;
    }

}
