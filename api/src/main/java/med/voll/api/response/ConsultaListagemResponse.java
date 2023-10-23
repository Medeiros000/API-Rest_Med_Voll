package med.voll.api.response;

import med.voll.api.enums.Andamento;
import med.voll.api.model.Consulta;

public record ConsultaListagemResponse(Long id, String data, String hora, Andamento andamento, String nomeMedico, String especialidade, String crm, String uf, String nomePaciente, String tipoPaciente) {
        public ConsultaListagemResponse(Consulta consulta) {
            this(
                    consulta.getId(),
                    consulta.getData(),
                    consulta.getHora(),
                    consulta.getAndamento(),
                    consulta.getMedico().getNome(),
                    consulta.getMedico().getEspecialidade(),
                    consulta.getMedico().getCrm(),
                    consulta.getMedico().getEndereco().getUf(),
                    consulta.getPaciente().getNome(),
                    consulta.getPaciente().getClass().getSimpleName()
            );
        }
}
