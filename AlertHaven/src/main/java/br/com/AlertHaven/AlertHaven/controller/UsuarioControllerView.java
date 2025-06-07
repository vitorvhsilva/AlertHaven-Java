package br.com.AlertHaven.AlertHaven.controller;

import br.com.AlertHaven.AlertHaven.entity.Usuario;
import br.com.AlertHaven.AlertHaven.repository.UsuarioRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsuarioControllerView {

    private UsuarioRepository repository;

    @GetMapping("/usuarios")
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", repository.findAll());
        return "usuarios/listar";
    }

    @GetMapping("/usuarios/adicionar")
    public String formularioUsuario(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "usuarios/formulario";
    }

    @PostMapping("/usuarios/salvar")
    public String salvarUsuario(@ModelAttribute Usuario usuario) {
        repository.save(usuario);
        return "redirect:/usuarios";
    }

    @GetMapping("/usuarios/editar/{id}")
    public String editarUsuario(@PathVariable String id, Model model) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID Inválido: " + id));
        model.addAttribute("usuario", usuario);
        return "usuarios/formulario";
    }

    @GetMapping("/usuarios/excluir/{id}")
    public String excluirUsuario(@PathVariable String id) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID Inválido: " + id));
        repository.delete(usuario);
        return "redirect:/usuarios";
    }
}