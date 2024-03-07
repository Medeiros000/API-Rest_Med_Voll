package med.voll.api.response;

import med.voll.api.model.Consulta;

public record ConsultaResponse( String data, String hora, String medico, String especialidade, String paciente, String andamento, String observacoes) {

    public ConsultaResponse(Consulta consulta) {
        this(
                consulta.getData(),
                consulta.getHora(),
                consulta.getMedico().getNome(),
                consulta.getMedico().getEspecialidade(),
                consulta.getPaciente().getNome(),
                consulta.getAndamento().toString(),
                consulta.getObservacoes());
    }
}
