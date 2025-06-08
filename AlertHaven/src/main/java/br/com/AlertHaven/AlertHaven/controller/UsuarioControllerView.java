package br.com.AlertHaven.AlertHaven.controller;

import br.com.AlertHaven.AlertHaven.dto.request.AtualizarUsuarioRequestDTO;
import br.com.AlertHaven.AlertHaven.dto.request.AtualizarUsuarioRequestMvcDTO;
import br.com.AlertHaven.AlertHaven.dto.request.CadastrarUsuarioRequestDTO;
import br.com.AlertHaven.AlertHaven.entity.Usuario;
import br.com.AlertHaven.AlertHaven.repository.UsuarioRepository;
import br.com.AlertHaven.AlertHaven.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/usuariosview")
@AllArgsConstructor
public class UsuarioControllerView {

    private final UsuarioRepository repository;
    private final UsuarioService usuarioService;
    private final ModelMapper mapper;

    @GetMapping("/listar")
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioService.listarUsuarios());
        return "listarUsuarios";
    }

    @GetMapping("/adicionar")
    public String mostrarFormularioAdicao(Model model) {
        model.addAttribute("usuario", new CadastrarUsuarioRequestDTO());
        return "formularioUsuario";
    }

    @PostMapping("/salvar")
    public String salvarUsuario(@Valid @ModelAttribute("usuario") CadastrarUsuarioRequestDTO dto,
                                BindingResult result,
                                Model model) {
        if (result.hasErrors()) {
            return "formularioUsuario";
        }
        usuarioService.cadastrarUsuario(mapper.map(dto, Usuario.class), LocalDate.of(dto.getAno(), dto.getMes(), dto.getDia()));
        return "redirect:/usuariosview/listar";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicao(@PathVariable String id, Model model) {
        Usuario usuario = usuarioService.obterUsuarioPorId(id);
        AtualizarUsuarioRequestMvcDTO dto = mapper.map(usuario, AtualizarUsuarioRequestMvcDTO.class);
        dto.setDia(usuario.getDataNascimento().getDayOfMonth());
        dto.setMes(usuario.getDataNascimento().getMonthValue());
        dto.setAno(usuario.getDataNascimento().getYear());
        model.addAttribute("usuario", dto);
        model.addAttribute("id", id);
        return "editarUsuario";
    }

    @PostMapping("/atualizar/{id}")
    public String atualizarUsuario(@PathVariable String id,
                                   @Valid @ModelAttribute("usuario") AtualizarUsuarioRequestMvcDTO dto,
                                   BindingResult result,
                                   Model model) {
        if (result.hasErrors()) {
            return "formularioUsuario";
        }
        usuarioService.atualizarUsuario(id, mapper.map(dto, AtualizarUsuarioRequestDTO.class));
        return "redirect:/usuariosview/listar";
    }

    @GetMapping("/excluir/{id}")
    public String excluirUsuario(@PathVariable String id) {
        usuarioService.deletarUsuarioPorId(id);
        return "redirect:/usuariosview/listar";
    }
}