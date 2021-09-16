package projeto.crud.configuracoes.security;

import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import projeto.crud.model.Usuario;
import projeto.crud.repository.UsuarioRepository;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Profile("prod")
public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {
//Filter é com o extends OncePerRequestFilter

    private TokenService tokenService;

    private UsuarioRepository usuarioRepository;

    public AutenticacaoViaTokenFilter(TokenService tokenService, UsuarioRepository usuarioRepository) {
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        //Método que recupera o token e efetua a filtragem

        String token = recuperarToken(request);

        Boolean valido = tokenService.isTokenValid(token);

        if (valido){
            autenticarUsuario(token);
        }

        filterChain.doFilter(request, response);
    }

    private void autenticarUsuario(String token) {

        Integer idUsuario = tokenService.getIdUsuario(token);

        Usuario usuario = usuarioRepository.findById(idUsuario).get();

        UsernamePasswordAuthenticationToken
                authentication = new UsernamePasswordAuthenticationToken
                (usuario, null, usuario.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);
        //fAz o BAguio funcionar
    }

    private String recuperarToken(HttpServletRequest request) {

        //HttpServletRequest request
        //Qualquer requisição feita, vai verificar se existe o token

        String token = request.getHeader("Authorization");

        if (token == null || token.isEmpty() || !token.startsWith("Bearer ")){
            return null;
        }

        int tamanhoToken = token.length();
        return token.substring(7, tamanhoToken);
    }
}
