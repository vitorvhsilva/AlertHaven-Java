package br.com.AlertHaven.AlertHaven.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CadastrarAbrigoRequestDto {
    @NotBlank(message = "O nome do abrigo não pode ficar em branco")
    private String nomeAbrigo;

    @NotBlank(message = "A capacidade suportada do abrigo não pode ficar em branco")
    private int capacidadeSuportadaAbrigo;

    @NotBlank(message = "O email do abrigo não pode ficar em branco")
    private String emailAbrigo;

    @NotBlank(message = "A latitude não pode ficar em branco")
    private String latitude;

    @NotBlank(message = "A longitude não pode ficar em branco")
    private String longitude;

    @NotBlank(message = "O cep não pode ficar em branco")
    @Size(min = 8, max = 8, message = "Digite um cep válido")
    private String cep;
}
