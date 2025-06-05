package br.com.AlertHaven.AlertHaven.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AtualizarAbrigoRequestDTO {
    @NotBlank(message = "O nome do abrigo não pode ficar em branco")
    private String nomeAbrigo;

    @NotBlank(message = "O email do abrigo não pode ficar em branco")
    private String emailAbrigo;

    @NotNull
    private int capacidadeSuportadaAbrigo;
}
