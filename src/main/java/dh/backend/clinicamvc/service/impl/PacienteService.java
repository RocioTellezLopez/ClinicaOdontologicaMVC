package dh.backend.clinicamvc.service.impl;

import dh.backend.clinicamvc.entity.Paciente;
import dh.backend.clinicamvc.exception.BadRequestException;
import dh.backend.clinicamvc.exception.ResourceNotFoundException;
import dh.backend.clinicamvc.repository.IPacienteRepository;
import dh.backend.clinicamvc.service.IPacienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PacienteService implements IPacienteService {

    private static Logger LOGGER = LoggerFactory.getLogger(PacienteService.class);
    private IPacienteRepository pacienteRepository;

    public PacienteService(IPacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public Paciente registrarPaciente(Paciente paciente) throws BadRequestException {
        if (paciente != null) {
            LOGGER.info("Paciente registardo: " + paciente.getNombre() + " " + paciente.getApellido());
            return pacienteRepository.save(paciente);
        } else {
            throw new BadRequestException("{\"mensaje\":\"Paciente no se registro\"}");
        }
    }

    public Optional<Paciente> buscarPorId(Integer id) {
        Optional<Paciente> pacienteADevolver = pacienteRepository.findById(id);
        LOGGER.info("Paciente Encontrado");
        return pacienteADevolver;
    }

    public List<Paciente> buscarTodos() {
        LOGGER.info("Todos los pacientes");
        return pacienteRepository.findAll();
    }

    @Override
    public void actualizarPaciente(Paciente paciente) {
        LOGGER.info("Paciente Actualizado");
        pacienteRepository.save(paciente);
    }

    @Override
    public void eliminarPaciente(Integer id) throws ResourceNotFoundException {
        Optional<Paciente> pacienteOptional = buscarPorId(id);
        if(pacienteOptional.isPresent()) {
            LOGGER.info("Paciente Eliminado");
            pacienteRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("{\"message\": \"paciente no encontrado\"}");
        }
    }


}
