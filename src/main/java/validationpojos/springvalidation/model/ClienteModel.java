package validationpojos.springvalidation.model;

import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
public class ClienteModel {

    @NotBlank
    private String nome;

    @Positive
    private Integer idade;

    @NotBlank
    private String email;

    @CPF
    private String cpf;

}
