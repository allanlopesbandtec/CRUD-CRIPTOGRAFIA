package projeto.crud.model;

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


    public Endereco(Integer idEndereco, @NotNull String nomeRua, @NotNull String cep, @NotNull String cidade, @NotNull String estado) {
        this.idEndereco = idEndereco;
        this.nomeRua = nomeRua;
        this.cep = cep;
        this.cidade = cidade;
        this.estado = estado;
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
}
