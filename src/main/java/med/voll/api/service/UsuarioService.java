package med.voll.api.service;

import med.voll.api.model.Usuario;
import med.voll.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;
    public void cadastrar(Usuario usuario) {
        repository.save(usuario);
    }
}
