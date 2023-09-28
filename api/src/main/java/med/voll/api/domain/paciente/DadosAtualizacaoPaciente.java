package med.voll.api.domain.paciente;

import jakarta.validation.constraints.NotNull;
import med.voll.api.dto.EnderecoDto;

public record DadosAtualizacaoPaciente(
        @NotNull
        Long id,
        String nome,
        String telefone,
        EnderecoDto endereco) {
}
