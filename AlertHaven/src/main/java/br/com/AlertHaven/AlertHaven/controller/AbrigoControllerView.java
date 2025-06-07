package br.com.AlertHaven.AlertHaven.controller;

import br.com.AlertHaven.AlertHaven.entity.Abrigo;
import br.com.AlertHaven.AlertHaven.repository.AbrigoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/abrigos")
public class AbrigoControllerView {

    private final AbrigoRepository repository;

    public AbrigoControllerView(AbrigoRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/listar")
    public String listarAbrigos(Model model) {
        model.addAttribute("abrigos", repository.findAll());
        return "listarAbrigos";
    }

    @GetMapping("/adicionar")
    public String formularioAbrigo(Model model) {
        model.addAttribute("abrigo", new Abrigo());
        return "formularioAbrigo";
    }

    @PostMapping("/salvar")
    public String salvarAbrigo(@ModelAttribute Abrigo abrigo) {
        repository.save(abrigo);
        return "redirect:/listarAbrigos";
    }

    @GetMapping("/editar/{id}")
    public String editarAbrigo(@PathVariable String id, Model model) {
        Abrigo abrigo = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID Inválido: " + id));
        model.addAttribute("abrigo", abrigo);
        return "formularioAbrigo";
    }

    @GetMapping("/excluir/{id}")
    public String excluirAbrigo(@PathVariable String id) {
        Abrigo abrigo = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID Inválido: " + id));
        repository.delete(abrigo);
        return "redirect:/listarAbrigos";
    }
}