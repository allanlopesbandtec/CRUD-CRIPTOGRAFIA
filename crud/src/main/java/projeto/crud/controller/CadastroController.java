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
import projeto.crud.model.dto.CadastroDto;
import projeto.crud.repository.CadastroRepository;
import javax.validation.Valid;

@RestController
@RequestMapping("/cadastros")
public class CadastroController {

    @Autowired
    CadastroRepository cadastroRepository;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Cadastro> cadastrar(@RequestBody @Valid Cadastro cadastro) {
        cadastroRepository.save(cadastro);
        return ResponseEntity.created(null).build();
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

    @PutMapping("/{id}")
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

}
