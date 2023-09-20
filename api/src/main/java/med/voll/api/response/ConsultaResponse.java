package med.voll.api.response;

import lombok.Getter;
import lombok.Setter;
import med.voll.api.model.Consulta;

@Getter
@Setter
public class ConsultaResponse {

    private String data;
    private String hora;
    private String medico;
    private String especialidade;
    private String paciente;
    private String status_consulta;
    private String observacoes;
    public ConsultaResponse(Consulta consulta) {
        this.data = consulta.getData();
        this.hora = consulta.getHora();
        this.medico = consulta.getMedico().getNome();
        this.especialidade = String.valueOf(consulta.getMedico().getEspecialidade());
        this.paciente = consulta.getPaciente().getNome();
        this.status_consulta = String.valueOf(consulta.getStatus_consulta());
        this.observacoes = consulta.getObservacoes();
    }
}
