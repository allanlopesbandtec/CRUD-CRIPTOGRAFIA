package projeto.crud.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Optional;

public class Acesso implements UserDetails {

    private String login;

    private String senha;

    public Acesso(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public Acesso() {
        this.login = "Admin";
        this.senha = "admin";
    }

    public Optional<Acesso> buscarUsuario(String login){

        Optional<Acesso> acesso = Optional.of(new Acesso());

        if (this.login.equals(login)){
            return acesso;
        }else {
            return Optional.empty();
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
