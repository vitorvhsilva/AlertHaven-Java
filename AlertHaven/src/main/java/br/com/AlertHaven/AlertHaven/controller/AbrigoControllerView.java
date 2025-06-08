package br.com.AlertHaven.AlertHaven.controller;

import br.com.AlertHaven.AlertHaven.dto.request.CadastrarAbrigoRequestDTO;
import br.com.AlertHaven.AlertHaven.entity.Abrigo;
import br.com.AlertHaven.AlertHaven.repository.AbrigoRepository;
import br.com.AlertHaven.AlertHaven.repository.TipoEmergenciaRepository;
import br.com.AlertHaven.AlertHaven.service.AbrigoService;
import br.com.AlertHaven.AlertHaven.service.LocalizacaoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/abrigosview")
@AllArgsConstructor
public class AbrigoControllerView {

    private AbrigoService abrigoService;
    private LocalizacaoService localizacaoService;
    private TipoEmergenciaRepository tipoEmergenciaRepository;
    private ModelMapper mapper;

    @GetMapping("/listar")
    public String listarAbrigos(Model model) {
        model.addAttribute("abrigos", abrigoService.listarAbrigos());
        return "listarAbrigos";
    }

    @GetMapping("/adicionar")
    public String mostrarFormulario(Model model) {
        model.addAttribute("cadastrarAbrigoRequestDTO", new CadastrarAbrigoRequestDTO());
        model.addAttribute("tiposEmergencia", tipoEmergenciaRepository.findAll());
        return "formularioAbrigo";
    }

    @PostMapping("/salvar")
    public String salvarAbrigo(@Valid @ModelAttribute CadastrarAbrigoRequestDTO dto,
                               BindingResult result,
                               Model model) {
        if (result.hasErrors()) {
            model.addAttribute("tiposEmergencia", tipoEmergenciaRepository.findAll());
            return "formularioAbrigo";
        }
        var abrigoCadastrado = abrigoService.cadastrarAbrigo(mapper.map(dto, Abrigo.class), dto.getIdsTipoEmergencia());
        localizacaoService.persistirLocalizacao(dto.getCep(), abrigoCadastrado);
        return "redirect:/abrigosview/listar";
    }

    @GetMapping("/editar/{id}")
    public String editarAbrigo(@PathVariable String id, Model model) {
        Abrigo abrigo = abrigoService.obterAbrigoPorId(id);
        model.addAttribute("abrigo", abrigo);
        return "formularioAbrigo";
    }

    @GetMapping("/excluir/{id}")
    public String excluirAbrigo(@PathVariable String id) {
        abrigoService.deletarAbrigoPorId(id);
        return "redirect:/listarAbrigos";
    }
}