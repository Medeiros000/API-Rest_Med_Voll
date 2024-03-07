package med.voll.api.domain.medico;

import jakarta.validation.constraints.NotNull;
import med.voll.api.dto.EnderecoDto;

public record DadosAtualizacaoMedico(
        @NotNull
        Long id,
        String nome,
        String telefone,
        EnderecoDto endereco) {
}
