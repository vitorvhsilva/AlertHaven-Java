package br.com.AlertHaven.AlertHaven.service;

import br.com.AlertHaven.AlertHaven.entity.Usuario;
import br.com.AlertHaven.AlertHaven.exception.UsuarioNaoEncontradoException;
import br.com.AlertHaven.AlertHaven.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UsuarioService {

    private UsuarioRepository repository;

    public Usuario cadastrarUsuario(Usuario usuario) {
        return repository.save(usuario);
    }

    public List<Usuario> listarUsuario(){
        return repository.findAll();
    }

    public Usuario obterUsuarioPorId(String id) {
        return repository.findById(id).orElseThrow(()-> new UsuarioNaoEncontradoException("Usuario não encontrado"));
    }

    public void deletarUsuarioPorId(String id) {
        repository.deleteById(id);
    }

}
