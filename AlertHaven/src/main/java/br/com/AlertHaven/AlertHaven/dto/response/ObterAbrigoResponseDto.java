package br.com.AlertHaven.AlertHaven.dto.response;

import lombok.Data;

@Data
public class ObterAbrigoResponseDto {

    private String idAbrigo;
    private String nomeAbrigo;
    private String capacidadeSuportadaAbrigo;
    private String emailAbrigo;
    private LocalizacaoDto localizacao;
}
