package br.com.AlertHaven.AlertHaven.controller;

import br.com.AlertHaven.AlertHaven.dto.request.AtualizarAbrigoRequestDTO;
import br.com.AlertHaven.AlertHaven.dto.request.CadastrarAbrigoRequestDTO;
import br.com.AlertHaven.AlertHaven.dto.response.LocalizacaoDTO;
import br.com.AlertHaven.AlertHaven.dto.response.ObterAbrigoCompletoResponseDTO;
import br.com.AlertHaven.AlertHaven.dto.response.ObterAbrigoSimplesResponseDTO;
import br.com.AlertHaven.AlertHaven.dto.response.ObterUsuarioResponseDTO;
import br.com.AlertHaven.AlertHaven.entity.Abrigo;
import br.com.AlertHaven.AlertHaven.entity.Localizacao;
import br.com.AlertHaven.AlertHaven.entity.TipoEmergencia;
import br.com.AlertHaven.AlertHaven.service.AbrigoService;
import br.com.AlertHaven.AlertHaven.service.LocalizacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Abrigo")
@AllArgsConstructor
@RestController
@RequestMapping("/abrigos")
public class AbrigoController {

    private AbrigoService abrigoService;
    private ModelMapper mapper;
    private LocalizacaoService localizacaoService;

    @Operation(
            summary = "Cadastra um novo abrigo",
            description = "Registra um abrigo na base de dados com localização e tipos de emergência atendidos",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Abrigo cadastrado com sucesso",
                            content = @Content(schema = @Schema(implementation = ObterAbrigoCompletoResponseDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Dados inválidos fornecidos"
                    )
            }
    )
    @PostMapping
    public ResponseEntity<ObterAbrigoCompletoResponseDTO> cadastrarAbrigo(
            @Valid @RequestBody CadastrarAbrigoRequestDTO dto
    ) {
        Abrigo abrigo = mapper.map(dto, Abrigo.class);
        Abrigo abrigoCadastrado = abrigoService.cadastrarAbrigo(abrigo, dto.getIdsTipoEmergencia());
        Localizacao localizacao = localizacaoService.persistirLocalizacao(dto.getCep(), abrigoCadastrado);
        abrigoCadastrado.setLocalizacao(localizacao);

        return ResponseEntity.status(HttpStatus.CREATED).body(converterEntidadeParaDTOCompleto(abrigoCadastrado));
    }

    @Operation(summary = "Lista todos os abrigos da base de dados", responses = {
            @ApiResponse(responseCode = "200", description = "Lista de abrigos retornada com sucesso",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ObterAbrigoSimplesResponseDTO.class))))})
    @GetMapping
    public List<ObterAbrigoSimplesResponseDTO> listarAbrigos() {
        List<Abrigo> abrigos = abrigoService.listarAbrigos();
        return abrigos.stream()
                .map(abrigo -> mapper.map(abrigo, ObterAbrigoSimplesResponseDTO.class))
                .toList();
    }

    @Operation(
            summary = "Busca um abrigo por ID",
            description = "Retorna detalhes completos de um abrigo, incluindo localização e tipos de emergência",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Abrigo encontrado com sucesso",
                            content = @Content(schema = @Schema(implementation = ObterAbrigoCompletoResponseDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Abrigo não encontrado"
                    )
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<ObterAbrigoCompletoResponseDTO> obterAbrigoPorId(
            @PathVariable String id
    ) {
        Abrigo abrigo = abrigoService.obterAbrigoPorId(id);
        return ResponseEntity.ok(converterEntidadeParaDTOCompleto(abrigo));
    }

    @Operation(
            summary = "Atualiza um abrigo existente",
            description = "Atualiza os dados de um abrigo, exceto localização e tipos de emergência",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Abrigo atualizado com sucesso",
                            content = @Content(schema = @Schema(implementation = ObterAbrigoCompletoResponseDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Dados inválidos fornecidos"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Abrigo não encontrado"
                    )
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<ObterAbrigoCompletoResponseDTO> atualizarAbrigo(
            @PathVariable String id,
            @Valid @RequestBody AtualizarAbrigoRequestDTO dto
    ) {
        Abrigo abrigo = abrigoService.atualizarAbrigo(id, dto);
        return ResponseEntity.ok(converterEntidadeParaDTOCompleto(abrigo));
    }

    @Operation(
            summary = "Remove um abrigo",
            description = "Exclui permanentemente um abrigo da base de dados",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Abrigo removido com sucesso"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Abrigo não encontrado"
                    )
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAbrigo(
            @PathVariable("id") String id
    ) {
        abrigoService.deletarAbrigoPorId(id);
        return ResponseEntity.noContent().build();
    }

    private ObterAbrigoCompletoResponseDTO converterEntidadeParaDTOCompleto(Abrigo abrigo) {
        ObterAbrigoCompletoResponseDTO response = mapper.map(abrigo, ObterAbrigoCompletoResponseDTO.class);
        response.setLocalizacao(mapper.map(abrigo.getLocalizacao(), LocalizacaoDTO.class));
        response.setTiposEmergencias(abrigo.getTipoEmergencias().stream()
                .map(TipoEmergencia::getTipoEmergencia)
                .toList());
        return response;
    }
}