package med.voll.api.domain.paciente;

import med.voll.api.domain.endereco.Endereco;
import med.voll.api.model.Paciente;

public record PacienteResponse(Long id, String nome, String email, String telefone, String cpf, Endereco endereco) {
    public PacienteResponse(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getTelefone(), paciente.getCpf(), paciente.getEndereco());
    }
}

