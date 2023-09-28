package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.dto.ConsultaDto;
import med.voll.api.response.ConsultaListagemResponse;
import med.voll.api.model.Consulta;
import med.voll.api.response.ConsultaResponse;
import med.voll.api.service.ConsultaService;
import med.voll.api.service.MedicoService;
import med.voll.api.service.PacienteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import static org.springframework.data.domain.Sort.Direction.ASC;

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
        var medico = medicoService.buscarMedicoPorId(dados.medico_id());
        var paciente = pacienteService.buscarPacientePorId(dados.paciente_id());
        Consulta consulta = new Consulta();
        BeanUtils.copyProperties(dados, consulta);
        consulta.setMedico(medico);
        consulta.setPaciente(paciente);
        consultaService.marcar(consulta);
        var uri = uriBuilder.path("/consultas/{id}").buildAndExpand(consulta.getId()).toUri();
        ConsultaResponse consultaResponse = new ConsultaResponse(consulta);

        return ResponseEntity.created(uri).body(consultaResponse);
    }

//    @GetMapping("data")
//    public ResponseEntity<Page<ConsultaResponse>> buscarConsultas(@RequestBody @Valid BuscaDto buscaDto, @PageableDefault Pageable pageable){
//        System.out.println("Busca de Consulta pela data: "+ buscaDto.data());
//        Sort multiSort = Sort.by(
//                Sort.Order.asc("data"),
//                Sort.Order.asc("hora"));
//        pageable = PageRequest.of(pageable.getPageNumber(),pageable.getPageSize(), multiSort);
//        Page<Consulta> consultas = consultaService.buscarConsultaPorData(buscaDto.data(), pageable);
//        Page<ConsultaResponse> page = consultas.map(ConsultaResponse::new);
//        return ResponseEntity.ok(page);
//    }

    @GetMapping("/{termo}")
    public ResponseEntity<Object> buscarConsultas(@PathVariable String termo, @PageableDefault(sort = {"data","hora"}, direction = ASC) Pageable pageable){
        System.out.println("Busca de Consulta pelo termo: "+ termo);
        Page<Consulta> consultas = consultaService.buscarConsultaPorTermo(termo, pageable);
        System.out.println("Listando consultas" + consultas.stream().toList().stream().map(ConsultaListagemResponse::new).toList().toString());
        consultas.stream().toList().forEach(System.out::println);
        return ResponseEntity.ok(consultas.stream().map(ConsultaListagemResponse::new).toList());
    }

    @GetMapping
    public ResponseEntity<Object> listarConsultas(@PageableDefault(sort = {"data","hora"}, direction = ASC) Pageable pageable){
        Page<Consulta> consultas = consultaService.listarConsulta(pageable);
        Page<ConsultaListagemResponse> page = consultas.map(ConsultaListagemResponse::new);
        page.stream().toList().forEach(System.out::println);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Object> buscarConsultaPorId(@PathVariable Long id){
        Consulta consulta = consultaService.buscarConsultaPorId(id);
        ConsultaResponse consultaResponse = new ConsultaResponse(consulta);
        return ResponseEntity.ok(consultaResponse);
    }


}
