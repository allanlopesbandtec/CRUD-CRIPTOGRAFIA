package projeto.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.crud.model.Endereco;
import projeto.crud.model.dto.EnderecoViaCep;
import projeto.crud.repository.EnderecoRepository;
import projeto.crud.repository.ViaCepRepository;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    EnderecoRepository enderecoRepository;

    @Autowired
    ViaCepRepository viaCepRepository;

//    @PostMapping("/{cep}")
//    public ResponseEntity<Endereco> consultarCep(
//            @RequestParam(required = false) String complemento,
//            @RequestParam(required = true) String numero,
//            @PathVariable String cep){
//
//        EnderecoViaCep consultaViaCep = viaCepRepository.buscarCep(cep);
//        Endereco endereco = new Endereco(consultaViaCep, numero, complemento);
//
//        if (!endereco.getCep().equals(null)){
//            enderecoRepository.save(endereco);
//            return ResponseEntity.ok(endereco);
//        }else {
//            return ResponseEntity.notFound().build();
//        }
//    }

}
