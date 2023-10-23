package med.voll.api.domain.medico;

import med.voll.api.model.Endereco;
import med.voll.api.model.Medico;

public record DadosListagemMedico(Long id, String nome, String email, String telefone, String crm, String especialidade, Endereco endereco) {

    public DadosListagemMedico(Medico medico) {
        this(
                medico.getId(),
                medico.getNome(),
                medico.getEmail(),
                medico.getTelefone(),
                medico.getCrm(),
                medico.getEspecialidade(),
                medico.getEndereco()
        );
    }

}
