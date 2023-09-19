package med.voll.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioDto (
    @NotBlank
    @Email
    String login,
    @NotBlank
    String senha
){
}
