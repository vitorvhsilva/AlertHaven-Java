package br.com.AlertHaven.AlertHaven.dto.response;

import lombok.Data;

@Data
public class AtualizarUsuarioResponseDto {
    private String nomeUsuario;
    private String emailUsuario;
    private String senhaUsuario;
    private String cpfUsuario;
    private String telefoneUsuario;
    private String dataNascimento;
}
