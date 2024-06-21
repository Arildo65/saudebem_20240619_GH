package br.edu.gov.fatec.saudebem.controllers;
import br.edu.gov.fatec.saudebem.dtos.ConsultaRecordDto;
import br.edu.gov.fatec.saudebem.models.ConsultaModel;
import br.edu.gov.fatec.saudebem.models.MedicoModel;
import br.edu.gov.fatec.saudebem.models.PacienteModel;
import br.edu.gov.fatec.saudebem.repositories.ConsultaRepository;
import br.edu.gov.fatec.saudebem.repositories.MedicoRepository;
import br.edu.gov.fatec.saudebem.repositories.PacienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/consultas")
public class ConsultaController {
    @Autowired
    ConsultaRepository consultaRepository;
    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping
    public ResponseEntity<ConsultaModel> agendarConsulta(@RequestBody @Valid ConsultaRecordDto consultaRecordDto) {
        Optional<PacienteModel> pacienteOpt = pacienteRepository.findById(consultaRecordDto.getPacienteId());
        Optional<MedicoModel> medicoOpt = medicoRepository.findById(consultaRecordDto.getMedicoId());

        if (pacienteOpt.isEmpty() || medicoOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        ConsultaModel consultaModel = new ConsultaModel();
        consultaModel.setPaciente(pacienteOpt.get());
        consultaModel.setMedico(medicoOpt.get());
        consultaModel.setDataHora(consultaRecordDto.getDataHora());

        ConsultaModel savedConsulta = consultaRepository.save(consultaModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedConsulta);
    }


    @GetMapping()
    public ResponseEntity<List<ConsultaModel>> getAllConsultas(){
        return ResponseEntity.status(HttpStatus.OK).body(consultaRepository.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> getOneConsulta(@PathVariable(value = "id") int id) {
        Optional<ConsultaModel> consulta0 = consultaRepository.findById(id);
        if (consulta0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Consulta not found.");
        }
        consulta0.get().add(linkTo(methodOn(ConsultaController.class).getAllConsultas()).withRel("Consultas List"));
        return ResponseEntity.status(HttpStatus.OK).body(consulta0.get());
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updateConsulta(@PathVariable(value = "id") int id, @RequestBody @Valid ConsultaRecordDto consultaRecordDto) {
        Optional<ConsultaModel> consulta0 = consultaRepository.findById(id);
        if (consulta0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Consulta not found.");
        }
        var consulta = consulta0.get();
        BeanUtils.copyProperties(consultaRecordDto, consulta, "id");
        return ResponseEntity.status(HttpStatus.OK).body(consultaRepository.save(consulta));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteConsulta(@PathVariable(value = "id") int id) {
        Optional<ConsultaModel> consulta0 = consultaRepository.findById(id);
        if (consulta0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Consulta not found.");
        }
        consultaRepository.delete(consulta0.get());
        return ResponseEntity.status(HttpStatus.OK).body("Consulta deleted successfully.");
    }
}
