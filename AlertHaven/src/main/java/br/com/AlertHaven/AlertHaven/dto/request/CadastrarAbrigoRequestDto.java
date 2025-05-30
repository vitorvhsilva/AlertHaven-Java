package br.com.AlertHaven.AlertHaven.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CadastrarAbrigoRequestDto {
    @NotBlank(message = "O nome do abrigo não pode ficar em branco")
    private String nomeAbrigo;

    @NotBlank(message = "A capacidade suportada do abrigo não pode ficar em branco")
    private int capacidadeSuportadaAbrigo;

    @NotBlank(message = "O email do abrigo não pode ficar em branco")
    private String emailAbrigo;
}
