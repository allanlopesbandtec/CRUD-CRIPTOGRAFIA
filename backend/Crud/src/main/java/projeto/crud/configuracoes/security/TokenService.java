package projeto.crud.configuracoes.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ClaimsMutator;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import projeto.crud.model.Usuario;

import java.util.Date;

@Service
public class TokenService {

    @Value("${crud.jwt.expiration}")
    String expiration;

    @Value("${crud.jwt.secret}")
    String secret;

    public String gerarToken(Authentication authentication){

        Usuario usuarioLogado = (Usuario) authentication.getPrincipal();

        Date hoje = new Date();

        Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));

        return Jwts.builder()
                .setIssuer("TOKEN-CRUD")
                .setSubject(usuarioLogado.getId().toString())
                .setIssuedAt(hoje)
                .setExpiration(dataExpiracao)
                .signWith(SignatureAlgorithm.HS256,secret)
                .compact();
    }

    public Boolean isTokenValid(String token) {
        try {
            Jwts.parser() //Vai descriptografar o token
                    .setSigningKey(secret) //Vai pegar a chave para efetuar a descriptografia
                    .parseClaimsJws(token); // Devolve um objeto (tipo Claims) ou uma exceção
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public Integer getIdUsuario(String token) {

        Claims claims =   Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token).getBody();
        //Tem o corpo que contruiu o token
        return Integer.parseInt(claims.getSubject());
    }
}
