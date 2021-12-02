package validationpojos.springvalidation.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import validationpojos.springvalidation.model.ClienteModel;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/cliente")
public class ValidationController {

    @PostMapping("/salvar")
    private ResponseEntity<ClienteModel> salvarCliente(@RequestBody @Valid ClienteModel clienteModel){
        return ResponseEntity.ok(clienteModel);
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
