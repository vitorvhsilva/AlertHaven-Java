package br.com.AlertHaven.AlertHaven.dto.response;

import lombok.Data;

@Data
public class ObterAbrigoResponseDTO {

    private String idAbrigo;
    private String nomeAbrigo;
    private String capacidadeSuportadaAbrigo;
    private String emailAbrigo;
    private LocalizacaoDto localizacao;
}
