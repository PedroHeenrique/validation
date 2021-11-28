package validationpojos.springvalidation.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import validationpojos.springvalidation.model.ClienteModel;

import javax.validation.Valid;

@RestController
@RequestMapping("api/cliente")
public class ValidationController {

    @PostMapping("/salvar")
    private ResponseEntity<String> salvarCliente(@RequestBody @Valid ClienteModel clienteModel){
        return ResponseEntity.ok("");
    }

}
