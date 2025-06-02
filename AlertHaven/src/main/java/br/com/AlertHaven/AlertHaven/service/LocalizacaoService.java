package br.com.AlertHaven.AlertHaven.service;

import br.com.AlertHaven.AlertHaven.dto.response.EnderecoViaCep;
import br.com.AlertHaven.AlertHaven.entity.Abrigo;
import br.com.AlertHaven.AlertHaven.entity.Localizacao;
import br.com.AlertHaven.AlertHaven.http.ViaCepClient;
import br.com.AlertHaven.AlertHaven.repository.LocalizacaoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LocalizacaoService {

    private LocalizacaoRepository repository;
    private ViaCepClient cepClient;

    public Localizacao persistirLocalizacao(String cep, Abrigo abrigo) {

        EnderecoViaCep viaCep;

        try {
            viaCep = cepClient.obterEnderecoAbrigo(cep);
        } catch (Exception e) {
            viaCep = new EnderecoViaCep("", "", "", "", "", "", "",
                    "", "", "", "", "", "");
        }

        Localizacao localizacao = new Localizacao(null, cep, "", "",
                viaCep.getLogradouro(), abrigo);

        abrigo.setLocalizacao(localizacao);
        return repository.save(localizacao);
    }
}
