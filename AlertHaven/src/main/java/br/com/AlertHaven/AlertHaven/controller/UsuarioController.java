package br.com.AlertHaven.AlertHaven.controller;

import br.com.AlertHaven.AlertHaven.dto.request.AtualizarUsuarioRequestDto;
import br.com.AlertHaven.AlertHaven.dto.request.ObterUsuarioRequestDto;
import br.com.AlertHaven.AlertHaven.dto.response.ObterUsuarioResponseDto;
import br.com.AlertHaven.AlertHaven.entity.Usuario;
import br.com.AlertHaven.AlertHaven.repository.UsuarioRepository;
import br.com.AlertHaven.AlertHaven.service.UsuarioService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private UsuarioService service;
    private ModelMapper mapper;

    @PostMapping
    public ResponseEntity<ObterUsuarioResponseDto>cadastrarUsuario(@RequestBody ObterUsuarioRequestDto dto) {
        Usuario usuario = mapper.map(dto, Usuario.class);
        LocalDate dataNasc = LocalDate.of(dto.getAno(), dto.getMes(), dto.getDia());

        ObterUsuarioResponseDto responseDto = mapper.map(service.cadastrarUsuario(usuario, dataNasc), ObterUsuarioResponseDto.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping
    public List<ObterUsuarioResponseDto> listarUsuarios() {
        List<Usuario> usuarios = service.listarUsuarios();
        return usuarios.stream()
                .map(usuario -> mapper.map(usuario, ObterUsuarioResponseDto.class))
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ObterUsuarioResponseDto> listarUsuarioPorId(@PathVariable String id){
        Usuario usuario = service.obterUsuarioPorId(id);

        ObterUsuarioResponseDto responseDto = mapper.map(usuario, ObterUsuarioResponseDto.class);

        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ObterUsuarioResponseDto> atualizarUsuario(@PathVariable String id, @Valid @RequestBody AtualizarUsuarioRequestDto dto) {
        Usuario usuario = service.atualizarUsuario(id, dto);

        return ResponseEntity.ok(mapper.map(usuario, ObterUsuarioResponseDto.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable String id) {
        service.deletarUsuarioPorId(id);

        return ResponseEntity.notFound().build();
    }

}
