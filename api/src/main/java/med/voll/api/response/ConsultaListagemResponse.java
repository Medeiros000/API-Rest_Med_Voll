package med.voll.api.response;

import med.voll.api.enums.StatusConsulta;
import med.voll.api.model.Consulta;

public record ConsultaListagemResponse(Long id, String data, String hora, StatusConsulta statusConsulta, String nomeMedico, String nomePaciente) {

        public ConsultaListagemResponse(Consulta consulta) {
            this(
                    consulta.getId(),
                    consulta.getData(),
                    consulta.getHora(),
                    consulta.getStatus_consulta(),
                    consulta.getMedico().getNome(),
                    consulta.getPaciente().getNome());
        }
}
