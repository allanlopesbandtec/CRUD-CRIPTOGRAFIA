package projeto.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projeto.crud.model.Cadastro;

public interface CadastroRepository extends JpaRepository<Cadastro, Integer> {


}
