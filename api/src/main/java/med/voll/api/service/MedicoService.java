package med.voll.api.service;

import med.voll.api.domain.medico.Medico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import med.voll.api.repository.MedicoRepository;

import java.util.Optional;

@Service

public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    public void cadastrarMedico(Medico medico) {
        medicoRepository.save(medico);
    }

    public Optional<Medico> buscarMedicoPorId(Long id) {
        return medicoRepository.findById(id);
    }

}
