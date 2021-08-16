package projeto.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import projeto.crud.configuracoes.security.TokenService;
import projeto.crud.model.dto.TokenDto;
import projeto.crud.model.form.FormLogin;
import javax.validation.Valid;

@RestController
@RequestMapping("/autenticacoes")
public class AutenticacaoController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    TokenService tokenService;

    @PostMapping
    public ResponseEntity<?> autenticar(@RequestBody @Valid FormLogin formLogin){
        UsernamePasswordAuthenticationToken dadosLogin = formLogin.converter();

        try {
            Authentication authentication = authenticationManager.authenticate(dadosLogin);
            String token = tokenService.gerarToken(authentication);
            return ResponseEntity.ok(new TokenDto(token, "Bearer"));
        }catch (AuthenticationException e){
            return ResponseEntity.status(404).body(e);
        }
    }
}
