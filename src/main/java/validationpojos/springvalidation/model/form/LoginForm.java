package validationpojos.springvalidation.model.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Getter
@Setter
@AllArgsConstructor
public class LoginForm {
    private String email;
    private String senha;

    public UsernamePasswordAuthenticationToken converter (){
        return new UsernamePasswordAuthenticationToken(email,senha);
    }

}
