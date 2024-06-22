package dh.backend.clinicamvc.service.impl;

import dh.backend.clinicamvc.entity.Odontologo;
import dh.backend.clinicamvc.exception.BadRequestException;
import dh.backend.clinicamvc.exception.ResourceNotFoundException;
import dh.backend.clinicamvc.repository.IOdontologoRepository;
import dh.backend.clinicamvc.service.IOdontologoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class OdontologoService implements IOdontologoService {

    private static Logger LOGGER = LoggerFactory.getLogger(OdontologoService.class);
    private IOdontologoRepository odontologoRepository;

    public OdontologoService(IOdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    public Odontologo registrarOdontologo(Odontologo odontologo) throws BadRequestException {

        if(odontologo.getNombre().isEmpty() && odontologo.getApellido().isEmpty() && odontologo.getNumeroMatricula().isEmpty()){
            throw new BadRequestException("{\"mensaje\":\"Odontologo no se registro\"}");
        } else{
            LOGGER.info("Odontologo registrado: " + odontologo.getNombre() + " " + odontologo.getApellido());
            return odontologoRepository.save(odontologo);
        }
    }

    public Optional<Odontologo> buscarPorId(Integer id) {
        LOGGER.info("Odontologo encontrado ");
        return odontologoRepository.findById(id);
    }

    public List<Odontologo> buscarTodos() {
        LOGGER.info("Todos los Odontologos");
        return odontologoRepository.findAll();
    }

    @Override
    public void actualizarOdontologo(Odontologo odontologo) {
        LOGGER.info("Odontologo Actualizado ");
        odontologoRepository.save(odontologo);
    }

    @Override
    public void eliminarOdontologo(Integer id) throws ResourceNotFoundException {
        Optional<Odontologo> odontologoOptional = buscarPorId(id);
        if(odontologoOptional.isPresent())
            odontologoRepository.deleteById(id );
        else throw new ResourceNotFoundException("{\"message\": \"Odontologo no encontrado\"}");
    }

    @Override
    public List<Odontologo> buscarPorApellido(String apellido) {
        return odontologoRepository.buscarPorApellido(apellido);
    }

    @Override
    public List<Odontologo> buscarPorNombre(String nombre) {
        return odontologoRepository.findByNombreLike(nombre);
    }

}
