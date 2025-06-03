package br.com.AlertHaven.AlertHaven.controller;

import br.com.AlertHaven.AlertHaven.dto.request.AtualizarUsuarioRequestDTO;
import br.com.AlertHaven.AlertHaven.dto.request.CadastrarUsuarioRequestDTO;
import br.com.AlertHaven.AlertHaven.dto.response.ObterUsuarioResponseDTO;
import br.com.AlertHaven.AlertHaven.entity.Usuario;
import br.com.AlertHaven.AlertHaven.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Tag(name = "Usuario")
@RestController
@RequestMapping("/usuarios")
@AllArgsConstructor
public class UsuarioController {

    private UsuarioService service;
    private ModelMapper mapper;

    @Operation(summary = "Cadastra um usuário na base de dados", responses = {
            @ApiResponse(responseCode = "201", description = "Usuário cadastrado com sucesso",
                    content = @Content(schema = @Schema(implementation = ObterUsuarioResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Erro ao cadastrar usuário")})
    @PostMapping
    public ResponseEntity<ObterUsuarioResponseDTO>cadastrarUsuario(@RequestBody CadastrarUsuarioRequestDTO dto) {
        Usuario usuario = mapper.map(dto, Usuario.class);
        LocalDate dataNasc = LocalDate.of(dto.getAno(), dto.getMes(), dto.getDia());

        ObterUsuarioResponseDTO responseDto = mapper.map(service.cadastrarUsuario(usuario, dataNasc), ObterUsuarioResponseDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @Operation(summary = "Lista todos os usuários da base de dados", responses = {
            @ApiResponse(responseCode = "200", description = "Lista de usuários retornada com sucesso",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ObterUsuarioResponseDTO.class))))})
    @GetMapping
    public List<ObterUsuarioResponseDTO> listarUsuarios() {
        List<Usuario> usuarios = service.listarUsuarios();
        return usuarios.stream()
                .map(usuario -> mapper.map(usuario, ObterUsuarioResponseDTO.class))
                .toList();
    }

    @Operation(summary = "Busca um usuário específico pelo ID", responses = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso",
                    content = @Content(schema = @Schema(implementation = ObterUsuarioResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")})
    @GetMapping("/{id}")
    public ResponseEntity<ObterUsuarioResponseDTO> listarUsuarioPorId(@PathVariable("id") String id){
        Usuario usuario = service.obterUsuarioPorId(id);

        ObterUsuarioResponseDTO responseDto = mapper.map(usuario, ObterUsuarioResponseDTO.class);

        return ResponseEntity.ok(responseDto);
    }

    @Operation(summary = "Atualiza os dados de um usuário", responses = {
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso",
                    content = @Content(schema = @Schema(implementation = ObterUsuarioResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Erro ao atualizar usuário"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")})
    @PutMapping("/{id}")
    public ResponseEntity<ObterUsuarioResponseDTO> atualizarUsuario(@PathVariable("id") String id, @Valid @RequestBody AtualizarUsuarioRequestDTO dto) {
        Usuario usuario = service.atualizarUsuario(id, dto);

        return ResponseEntity.ok(mapper.map(usuario, ObterUsuarioResponseDTO.class));
    }

    @Operation(summary = "Remove um usuário da base de dados", responses = {
            @ApiResponse(responseCode = "204", description = "Usuário removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable("id") String id) {
        service.deletarUsuarioPorId(id);

        return ResponseEntity.noContent().build();
    }

}
