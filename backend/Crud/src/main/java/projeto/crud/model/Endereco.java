package projeto.crud.model;

import projeto.crud.model.dto.EnderecoViaCep;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEndereco;

    @NotNull(message = "Rua não pode ser null")
    private String nomeRua;

    @NotNull(message = "CEP não pode ser null")
    @NotBlank(message = "CEP não pode ficar em branco")
    private String cep;

    @NotNull(message = "Cidade não pode ser null")
    private String cidade;

    @NotNull(message = "Estado não pode ser null")
    private String estado;

    @NotNull(message = "Bairro não pode ser null")
    private String bairro;

    @NotNull(message = "Número não pode ser null")
    private String numero;

    private String complemento;

    public Endereco(EnderecoViaCep enderecoViaCep, String numero, String complemento) {
        this.nomeRua = enderecoViaCep.getLogradouro();
        this.cep = enderecoViaCep.getCep();
        this.cidade = enderecoViaCep.getLocalidade();
        this.estado = enderecoViaCep.getUf();
        this.bairro = enderecoViaCep.getBairro();
        this.numero = numero;
        this.complemento = complemento;
    }

    public Endereco() {
    }

    public Integer getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Integer idEndereco) {
        this.idEndereco = idEndereco;
    }

    public String getNomeRua() {
        return nomeRua;
    }

    public void setNomeRua(String nomeRua) {
        this.nomeRua = nomeRua;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
}
