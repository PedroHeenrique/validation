package validationpojos.springvalidation.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.br.CPF;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

@Data
@Builder
@AllArgsConstructor
public class ClienteModel {

    @NotBlank(message = "Campo nao informado")
    @Pattern(regexp = "^[A-Z]+(.)*", message = "Deve Iniciar com letra maiuscula")
    private String nome;

    @Positive
    private Integer idade;

    @Email(message = "Campo invalido")
    @NotBlank(message = "Campo nao informado")
    private String email;

    @CPF(message = "Informe um CPF valido")
    @NotBlank(message = "Campo nao Informado")
    @ToString.Exclude
    private String cpf;

}
