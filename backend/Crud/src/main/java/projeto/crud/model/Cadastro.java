package projeto.crud.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.br.CPF;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Cadastro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCadastro;

    @NotNull(message = "Nome não pode ser null")
    @NotBlank(message = "Nome não pode ficar em branco")
    @Size(min = 3)
    private String nomeCompleto;

    @Email
    @NotNull(message = "Email não pode ser null")
    @NotBlank(message = "Email não pode ficar em branco")
    private String email;


    @NotNull(message = "Cpf não pode ser null")
    @NotBlank(message = "Cpf não pode ficar em branco")
    @Column(unique = true)
    @CPF(message = "Cpf inválido")
    @Pattern(regexp = "(\\d{3}\\.\\d{3}\\.)?(\\d{3}\\-\\d{2})")
    private String cpf;


    @Past
    @NotNull(message = "Data de nascimento não pode ser null")
    private LocalDate dataNascimento;

    @Pattern(regexp = "(\\(?\\(\\d{2}\\)?\\))?(\\d{5}\\-\\d{4})", message = "Celular inválido")
    private String celular;

    @Pattern(regexp = "(\\(?\\(\\d{2}\\)?\\))?(\\d{4}\\-\\d{4})", message = "Telefone inválido")
    private String telefone;

    @NotNull(message = "Estado Civil não pode ser null")
    @NotBlank(message = "Estado Civil não pode ficar em branco")
    private String estadoCivil;

    @CreationTimestamp
    private LocalDateTime dataCriacao;

    private Boolean cadastrado;

    @ManyToOne
    //@NotNull(message = "Endereço não pode ser null")
    private Endereco endereco;

    public Cadastro(String nomeCompleto, String cpf) {
        this.nomeCompleto = nomeCompleto;
        this.cpf = cpf;
        this.cadastrado = true;
    }

    public Cadastro(String nomeCompleto, String cpf, Boolean ativo) {
        this.nomeCompleto = nomeCompleto;
        this.cpf = cpf;
        this.cadastrado = ativo;
    }

    public Cadastro() {
        this.cadastrado = true;
    }

    public Integer getIdCadastro() {
        return idCadastro;
    }

    public void setIdCadastro(Integer idCadastro) {
        this.idCadastro = idCadastro;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
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

    public @NotNull @Past LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(@NotNull @Past LocalDate dataNacimento) {
        this.dataNascimento = dataNacimento;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Boolean getCadastrado() {
        return cadastrado;
    }

    public void setCadastrado(Boolean cadastrado) {
        this.cadastrado = cadastrado;
    }
}

