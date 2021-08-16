package projeto.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import projeto.crud.model.Usuario;
import projeto.crud.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping
    //@PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario){



        usuarioRepository.save(usuario);
        return ResponseEntity.created(null).build();
    }



}
