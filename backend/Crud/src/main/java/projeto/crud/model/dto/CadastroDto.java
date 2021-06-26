package projeto.crud.model.dto;

import org.springframework.data.domain.Page;
import projeto.crud.model.Cadastro;

public class CadastroDto {

    public String nome;

    public String email;

    public String cpf;

    public CadastroDto(Cadastro cadastro) {
        this.nome = cadastro.getNomeCompleto();
        this.email = cadastro.getEmail();
        this.cpf = cadastro.getCpf();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public static Page<CadastroDto> converter(Page<Cadastro> cadastros){
        return cadastros.map(CadastroDto::new);
    }
}
