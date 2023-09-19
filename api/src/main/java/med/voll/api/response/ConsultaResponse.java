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
        this.data = data;
        this.hora = hora;
        this.medico = medico;
        this.especialidade = especialidade;
        this.paciente = paciente;
        this.status_consulta = status_consulta;
        this.observacoes = observacoes;
    }
}
