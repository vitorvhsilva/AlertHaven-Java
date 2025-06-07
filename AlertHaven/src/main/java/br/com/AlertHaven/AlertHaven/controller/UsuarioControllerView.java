package br.com.AlertHaven.AlertHaven.controller;

import br.com.AlertHaven.AlertHaven.entity.Usuario;
import br.com.AlertHaven.AlertHaven.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/usuariosview")
public class UsuarioControllerView {

    private UsuarioRepository repository;

    @GetMapping("/listar")
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", repository.findAll());
        return "listarUsuarios";
    }

    @GetMapping("/adicionar")
    public String formularioUsuario(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "formularioUsuario";
    }

    @PostMapping("/salvar")
    public String salvarUsuario(@ModelAttribute Usuario usuario) {
        repository.save(usuario);
        return "redirect:/listarUsuarios";
    }

    @GetMapping("/editar/{id}")
    public String editarUsuario(@PathVariable String id, Model model) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID Inválido: " + id));
        model.addAttribute("usuario", usuario);
        return "formularioAbrigo";
    }

    @GetMapping("/excluir/{id}")
    public String excluirUsuario(@PathVariable String id) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID Inválido: " + id));
        repository.delete(usuario);
        return "redirect:/listarUsuarios";
    }
}