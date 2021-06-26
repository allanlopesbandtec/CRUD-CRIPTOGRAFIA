package projeto.crud.repository;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import projeto.crud.model.dto.EnderecoViaCep;

@FeignClient(name = "consula-viacep", url = "http://viacep.com.br/ws/")
public interface ViaCepRepository {

    @GetMapping(path = "{cep}/json/")
    EnderecoViaCep buscarCep(@PathVariable String cep);
    //Consulta do Api via cep usando a classe feita para receber seus dados

}
