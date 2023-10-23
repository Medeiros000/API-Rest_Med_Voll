package med.voll.api.domain.paciente;

import med.voll.api.model.Endereco;
import med.voll.api.model.Paciente;

public record DadosListagemPaciente(Long id, String nome, String telefone, String email, String cpf, Endereco endereco) {
    public DadosListagemPaciente(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getTelefone(), paciente.getEmail(), paciente.getCpf(), paciente.getEndereco() );
    }

}
