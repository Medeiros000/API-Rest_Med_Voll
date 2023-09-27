package med.voll.api.dto;

import med.voll.api.enums.StatusConsulta;
import med.voll.api.model.Consulta;

public record ConsultaListagem(Long id, String data, String hora, StatusConsulta statusConsulta, String nomeMedico, String nomePaciente) {

        public ConsultaListagem(Consulta consulta) {
            this(consulta.getId(), consulta.getData(), consulta.getHora(), consulta.getStatus_consulta(), consulta.getMedico().getNome(),
                    consulta.getPaciente().getNome());
        }
}
