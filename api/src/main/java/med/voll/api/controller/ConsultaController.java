package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.paciente.Paciente;
import med.voll.api.dto.BuscaDto;
import med.voll.api.dto.ConsultaDto;
import med.voll.api.model.Consulta;
import med.voll.api.response.ConsultaResponse;
import med.voll.api.service.ConsultaService;
import med.voll.api.service.MedicoService;
import med.voll.api.service.PacienteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;
    @Autowired
    private MedicoService medicoService;
    @Autowired
    private PacienteService pacienteService;

    @PostMapping
    public ResponseEntity<Object> marcar(@RequestBody @Valid ConsultaDto dados, UriComponentsBuilder uriBuilder){
        Optional<Medico> medicoOptional = medicoService.buscarMedicoPorId(dados.medico_id());
        Medico medico = medicoOptional.get();
        Optional<Paciente> pacienteOptional = pacienteService.buscarPacientePorId(dados.paciente_id());
        Paciente paciente = pacienteOptional.get();
        Consulta consulta = new Consulta();
        BeanUtils.copyProperties(dados, consulta);
        consulta.setMedico(medico);
        consulta.setPaciente(paciente);
        consultaService.marcar(consulta);
        ConsultaResponse consultaResponse = new ConsultaResponse(consulta);
        var uri = uriBuilder.path("/consultas/{id}").buildAndExpand(consulta.getId()).toUri();
        return ResponseEntity.created(uri).body(consultaResponse);
    }

    @GetMapping("data")
    public ResponseEntity<Page<ConsultaResponse>> buscarConsultas(@RequestBody @Valid BuscaDto buscaDto, @PageableDefault Pageable pageable){
        System.out.println("Busca de Consulta pela data: "+ buscaDto.data());
        Sort multiSort = Sort.by(
                Sort.Order.asc("data"),
                Sort.Order.asc("hora"));
        pageable = PageRequest.of(pageable.getPageNumber(),pageable.getPageSize(), multiSort);
        Page<Consulta> consultas = consultaService.buscarConsultaPorData(buscaDto.data(), pageable);
        Page<ConsultaResponse> page = consultas.map(ConsultaResponse::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping
    public ResponseEntity<Page<ConsultaResponse>> listarConsultas(@PageableDefault Pageable pageable){
        Sort multiSort = Sort.by(
                Sort.Order.asc("data"),
                Sort.Order.asc("hora"));
        pageable = PageRequest.of(pageable.getPageNumber(),pageable.getPageSize(), multiSort);
        Page<Consulta> consultas = consultaService.listarConsulta(pageable);
        Page<ConsultaResponse> page = consultas.map(ConsultaResponse::new);
        return ResponseEntity.ok(page);
    }


}
