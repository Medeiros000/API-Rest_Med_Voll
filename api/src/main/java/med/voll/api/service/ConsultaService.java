package med.voll.api.service;

import med.voll.api.dto.ConsultaDto;
import med.voll.api.model.Consulta;
import med.voll.api.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    public void marcar(Consulta consulta) {
        consultaRepository.save(consulta);
    }

    public Object listar(Pageable pageable) {
        return consultaRepository.findAll(pageable);
    }
}
