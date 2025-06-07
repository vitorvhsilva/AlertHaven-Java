package br.com.AlertHaven.AlertHaven.controller;

import br.com.AlertHaven.AlertHaven.entity.Abrigo;
import br.com.AlertHaven.AlertHaven.repository.AbrigoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AbrigoControllerView {

    private AbrigoRepository repository;

    @GetMapping("/abrigos")
    public String listarAbrigos(Model model) {
        model.addAttribute("abrigos", repository.findAll());
        return "listarAbrigos";
    }

    @GetMapping("/abrigos/adicionar")
    public String formularioAbrigo(Model model) {
        model.addAttribute("abrigo", new Abrigo());
        return "formularioAbrigo";
    }

    @PostMapping("/abrigos/salvar")
    public String salvarAbrigo(@ModelAttribute Abrigo abrigo) {
        repository.save(abrigo);
        return "redirect:/listarAbrigos";
    }

    @GetMapping("/abrigos/editar/{id}")
    public String editarAbrigo(@PathVariable String id, Model model) {
        Abrigo abrigo = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID Inválido: " + id));
        model.addAttribute("abrigo", abrigo);
        return "formularioAbrigo";
    }

    @GetMapping("/abrigos/excluir/{id}")
    public String excluirAbrigo(@PathVariable String id) {
        Abrigo abrigo = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID Inválido: " + id));
        repository.delete(abrigo);
        return "redirect:/listarAbrigos";
    }
}