package br.com.AlertHaven.AlertHaven.dto.response;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AtualizarAbrigoResponseDTO {
    private String nomeAbrigo;
    private String emailAbrigo;
    private int capacidadeSuportadaAbrigo;

}
