package med.voll.api.dto;

import jakarta.validation.constraints.NotNull;

public record ConsultaDto(
        String data,
        String hora,
        @NotNull
        Long medico_id,
        String especialidade,
        @NotNull
        Long paciente_id,
        String status_consulta,
        String observacoes
) {
}
