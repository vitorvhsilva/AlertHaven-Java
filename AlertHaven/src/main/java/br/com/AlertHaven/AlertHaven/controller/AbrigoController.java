package br.com.AlertHaven.AlertHaven.controller;

import br.com.AlertHaven.AlertHaven.dto.request.AtualizarAbrigoRequestDTO;
import br.com.AlertHaven.AlertHaven.dto.request.CadastrarAbrigoRequestDTO;
import br.com.AlertHaven.AlertHaven.dto.response.LocalizacaoDto;
import br.com.AlertHaven.AlertHaven.dto.response.ObterAbrigoResponseDTO;
import br.com.AlertHaven.AlertHaven.entity.Abrigo;
import br.com.AlertHaven.AlertHaven.entity.Localizacao;
import br.com.AlertHaven.AlertHaven.service.AbrigoService;
import br.com.AlertHaven.AlertHaven.service.LocalizacaoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/abrigos")
public class AbrigoController {

    private AbrigoService abrigoService;
    private ModelMapper mapper;
    private LocalizacaoService localizacaoService;

    @PostMapping
    public ResponseEntity<ObterAbrigoResponseDTO> cadastrarAbrigo(@RequestBody CadastrarAbrigoRequestDTO dto) {
        Abrigo abrigo = mapper.map(dto, Abrigo.class);
        Abrigo abrigoCadastrado = abrigoService.cadastrarAbrigo(abrigo, dto.getIdsTipoEmergencia());
        Localizacao localizacao = localizacaoService.persistirLocalizacao(dto.getCep(), abrigoCadastrado);

        ObterAbrigoResponseDTO responseDto = mapper.map(abrigoCadastrado, ObterAbrigoResponseDTO.class);
        responseDto.setLocalizacao(mapper.map(localizacao, LocalizacaoDto.class));

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping
    public List<ObterAbrigoResponseDTO> listarAbrigos(){
        List<Abrigo> abrigos = abrigoService.listarAbrigos();
        return abrigos.stream()
                .map(abrigo -> mapper.map(abrigo, ObterAbrigoResponseDTO.class))
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ObterAbrigoResponseDTO> obterAbrigoPorId(@PathVariable String id) {
        Abrigo abrigo = abrigoService.obterAbrigoPorId(id);

        LocalizacaoDto localizacaoDto = mapper.map(abrigo.getLocalizacao(), LocalizacaoDto.class);

        ObterAbrigoResponseDTO response = mapper.map(abrigo, ObterAbrigoResponseDTO.class);
        response.setLocalizacao(localizacaoDto);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ObterAbrigoResponseDTO> atualizarAbrigo(@PathVariable String id, @Valid @RequestBody AtualizarAbrigoRequestDTO dto) {
        Abrigo abrigo = abrigoService.atualizarAbrigo(id, dto);

        return ResponseEntity.ok(mapper.map(abrigo, ObterAbrigoResponseDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAbrigo(@PathVariable String id){
        abrigoService.deletarAbrigoPorId(id);

        return ResponseEntity.notFound().build();
    }
}
