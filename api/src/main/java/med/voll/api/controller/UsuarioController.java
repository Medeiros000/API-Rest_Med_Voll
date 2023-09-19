package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.dto.UsuarioDto;
import med.voll.api.infra.auth.DadosAutenticacao;
import med.voll.api.model.Usuario;
import med.voll.api.service.UsuarioService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody @Valid UsuarioDto usuarioDto, UriComponentsBuilder uriComponentsBuilder) {
        var usuario = new Usuario();
        BeanUtils.copyProperties(usuarioDto, usuario);
        usuarioService.cadastrar(usuario);
        var uri = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.ok().build();
    }
}
