package med.voll.api.service;

import med.voll.api.domain.paciente.Paciente;
import med.voll.api.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public Optional<Paciente> buscarPacientePorId(Long paciente_id) {
        return pacienteRepository.findById(paciente_id);
    }

    public void excluir(Long id) {
        pacienteRepository.deleteById(id);
    }

    public Page<Paciente> findAllByNomeContains(String nome, Pageable paginacao) {
        return pacienteRepository.findAllByNomeContains(nome, paginacao);
    }

    public Paciente getReferenceById(Long id) {
        return pacienteRepository.getReferenceById(id);
    }

    public Page<Paciente> findAllByCpfStartingWith(String cpf, Pageable paginacao) {
        return pacienteRepository.findAllByCpfStartingWith(cpf, paginacao);
    }

    public void save(Paciente paciente) {
        pacienteRepository.save(paciente);
    }

    public Page<Paciente> findAllByAtivoTrue(Pageable paginacao) {
        return pacienteRepository.findAllByAtivoTrue(paginacao);
    }
}
