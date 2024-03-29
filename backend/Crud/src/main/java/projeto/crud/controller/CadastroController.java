package projeto.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import projeto.crud.model.Cadastro;
import projeto.crud.model.Endereco;
import projeto.crud.model.dto.CadastroDto;
import projeto.crud.model.dto.EnderecoViaCep;
import projeto.crud.repository.CadastroRepository;
import projeto.crud.repository.EnderecoRepository;
import projeto.crud.repository.ViaCepRepository;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/cadastros")
public class CadastroController {

    @Autowired
    CadastroRepository cadastroRepository;

    @Autowired
    EnderecoRepository enderecoRepository;

    @Autowired
    ViaCepRepository viaCepRepository;

    @PostMapping("/{cep}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Cadastro> cadastrar(@RequestBody @Valid Cadastro cadastro,
                                              @RequestParam(required = false) String complemento,
                                              @RequestParam(required = true) String numero,
                                              @PathVariable String cep) {

            EnderecoViaCep consultaViaCep = viaCepRepository.buscarCep(cep);
            //Endereço do via CEP

        if (consultaViaCep.getCep().equals(null)){
            return ResponseEntity.notFound().build();
        }else {

            Endereco endereco = new Endereco(consultaViaCep, numero, complemento);

            if (enderecoRepository.exists(Example.of(endereco))){

                Optional<Endereco> enderecoBanco = enderecoRepository.findOne(Example.of(endereco));
                cadastro.setEndereco(enderecoBanco.get());
                cadastroRepository.save(cadastro);

            }else{
                enderecoRepository.save(endereco);
                cadastro.setEndereco(endereco);
                cadastroRepository.save(cadastro);
            }

            return ResponseEntity.created(null).build();
        }
    }

    @GetMapping
    public Page<CadastroDto> listarCadastrosDto(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String cpf,
            @PageableDefault(sort = "nomeCompleto",
            direction = Sort.Direction.ASC,
            page = 0,
            size = 10) Pageable paginacao) {

        Cadastro cadastro = new Cadastro(nome,cpf);
        Page<Cadastro> cadastros = cadastroRepository.findAll(Example.of(cadastro), paginacao);
        return CadastroDto.converter(cadastros);
    }

    @GetMapping("/detalhados")
    public Page<Cadastro> listaCadastrosDetalhada(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String cpf,
            @PageableDefault(sort = "nomeCompleto",
                    direction = Sort.Direction.ASC,
                    page = 0,
                    size = 2) Pageable paginacao) {

        Cadastro cadastro = new Cadastro(nome,cpf);

        Page<Cadastro> cadastros = cadastroRepository.findAll(Example.of(cadastro), paginacao);
        return cadastros;
    }

    @GetMapping("/inativos")
    public Page<CadastroDto> listarCadastrosDtoInativos(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String cpf,
            @PageableDefault(sort = "nomeCompleto",
                    direction = Sort.Direction.ASC,
                    page = 0,
                    size = 10) Pageable paginacao) {

        Cadastro cadastro = new Cadastro(nome,cpf,false);

        Page<Cadastro> cadastros = cadastroRepository.findAll(Example.of(cadastro), paginacao);
        return CadastroDto.converter(cadastros);
    }

    @PutMapping("/remover/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<HttpStatus> atualizarStatusCadastro(@PathVariable Integer id){

        if (cadastroRepository.existsById(id)){

            Cadastro cadastro = cadastroRepository.getOne(id);
            cadastro.setCadastrado(false);
            cadastroRepository.save(cadastro);
            return ResponseEntity.ok().build();

        }else {
            return ResponseEntity.notFound().build();
        }
    }

//    @PutMapping("/{id}")
//    @PreAuthorize("hasAuthority('ADMIN')")
//    public ResponseEntity<HttpStatus> atualizarCadastro(@PathVariable Integer id){
//
//        if (cadastroRepository.existsById(id)){
//            Cadastro cadastro = cadastroRepository.getOne(id);
//            cadastro.setCadastrado(false);
//            cadastroRepository.save(cadastro);
//            return ResponseEntity.ok().build();
//
//        }else {
//            return ResponseEntity.notFound().build();
//        }
//    }

}
