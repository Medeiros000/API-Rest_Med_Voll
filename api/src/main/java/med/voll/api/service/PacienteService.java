package med.voll.api.service;

import med.voll.api.model.Paciente;
import med.voll.api.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public Paciente buscarPacientePorId(Long paciente_id) {
        return pacienteRepository.findById(paciente_id).orElseThrow(()-> new RuntimeException("Paciente não encontrado"));
    }

    public void excluir(Long id) {
        pacienteRepository.deleteById(id);
    }

    public Page<Paciente> findAllByNomeContains(String nome, Pageable paginacao) {
        return pacienteRepository.findAllByNomeContainsIgnoreCase(nome, paginacao);
    }

    public Paciente findById(Long id) {
        return pacienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Paciente não encontrado"));
    }

    public Page<Paciente> findAllByCpfStartingWith(String cpf, Pageable paginacao) {
        return pacienteRepository.findAllByCpfStartingWithIgnoreCase(cpf, paginacao);
    }

    public void save(Paciente paciente) {
        pacienteRepository.save(paciente);
    }

    public Page<Paciente> findAllByAtivoTrue(Pageable paginacao) {
        return pacienteRepository.findAllByAtivoTrue(paginacao);
    }
}
