package dh.backend.clinicamvc.controller;

import dh.backend.clinicamvc.entity.Paciente;
import dh.backend.clinicamvc.exception.BadRequestException;
import dh.backend.clinicamvc.exception.ResourceNotFoundException;
import dh.backend.clinicamvc.service.IPacienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    private IPacienteService pacienteService;

    public PacienteController(IPacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping
    public ResponseEntity<Paciente> registrarPaciente(@RequestBody Paciente paciente) throws BadRequestException {
        Paciente pacienteARetornar = pacienteService.registrarPaciente(paciente);

        if(pacienteARetornar == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(pacienteARetornar);
        }

    }

    @GetMapping
    public ResponseEntity<List<Paciente>> buscarTodos() {
        return ResponseEntity.ok(pacienteService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPacientePorId(@PathVariable Integer id) {

        Optional<Paciente> paciente = pacienteService.buscarPorId(id);

        if(paciente.isPresent()) {
            Paciente pacienteARetornar = paciente.get();

            return ResponseEntity.ok(pacienteARetornar);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }


    @PutMapping
    public ResponseEntity<String> actuaizarPaciente(@RequestBody Paciente paciente) {
        Optional<Paciente> pacienteOptional = pacienteService.buscarPorId(paciente.getId());

        if(pacienteOptional.isPresent()) {
            pacienteService.actualizarPaciente(paciente);

            return ResponseEntity.ok("{\"message\": \"Paciente modificado\"}");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrarPaciente(@PathVariable Integer id) throws ResourceNotFoundException {
        pacienteService.eliminarPaciente(id);
        return ResponseEntity.ok("{\"message\": \"Paciente eliminado\"}");
    }


}
