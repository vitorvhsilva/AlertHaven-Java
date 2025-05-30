package br.com.AlertHaven.AlertHaven.service;

import br.com.AlertHaven.AlertHaven.dto.request.AtualizarAbrigoRequestDto;
import br.com.AlertHaven.AlertHaven.entity.Abrigo;
import br.com.AlertHaven.AlertHaven.exception.AbrigoNaoEncontradoException;
import br.com.AlertHaven.AlertHaven.repository.AbrigoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AbrigoService {

    private AbrigoRepository repository;

    public Abrigo cadastrarAbrigo(Abrigo abrigo) {
        return repository.save(abrigo);
    }

    public List<Abrigo> listarAbrigos() {
        return repository.findAll();
    }

    public Abrigo obterAbrigoPorId(String id) {
        return repository.findById(id).orElseThrow(() -> new AbrigoNaoEncontradoException("Abrigo não encontrado"));
    }

    public void deletarAbrigoPorId(String id) {
        repository.deleteById(id);
    }

    @Transactional
    public Abrigo atualizarAbrigo(String id, @Valid AtualizarAbrigoRequestDto dto) {
        Abrigo abrigo = obterAbrigoPorId(id);

        abrigo.setNomeAbrigo(dto.getNomeAbrigo());
        abrigo.setEmailAbrigo(dto.getEmailAbrigo());
        abrigo.setCapacidadeSuportadaAbrigo(dto.getCapacidadeSuportadaAbrigo());

        return abrigo;
    }

}
