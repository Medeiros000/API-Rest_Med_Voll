package med.voll.api.service;

import med.voll.api.model.Medico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import med.voll.api.repository.MedicoRepository;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository repository;

    public void salvarMedico(Medico medico) {
        repository.save(medico);
    }

    public Page<Medico> buscarMedicoPorCrm(Long crm, Pageable paginacao) {
        return repository.findAllByCrmStartingWith(crm.toString(), paginacao);
    }

    public Medico findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Medico não encontrado"));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public Page<Medico> findAllByAtivoTrue(Pageable paginacao) {
        return repository.findAllByAtivoTrueOrderByNome(paginacao);
    }

    public Page<Medico> procurarMedico(String termo, Pageable paginacao) {
        System.out.println("Buscando por: " + termo);
        return repository.findAllByEspecialidadeIsContainingIgnoreCaseOrNomeContainingIgnoreCaseOrderByNome(termo, termo, paginacao);
    }

}
