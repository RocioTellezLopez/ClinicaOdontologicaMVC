package dh.backend.clinicamvc.controller;

import dh.backend.clinicamvc.entity.Odontologo;
import dh.backend.clinicamvc.exception.BadRequestException;
import dh.backend.clinicamvc.exception.ResourceNotFoundException;
import dh.backend.clinicamvc.service.IOdontologoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologo")
public class OdontologoController {

    private IOdontologoService odontologoService;

    public OdontologoController(IOdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @PostMapping
    public ResponseEntity<Odontologo> registarOdontologo(@RequestBody Odontologo odontologo) throws BadRequestException {
        Odontologo odontologoARetornar = odontologoService.registrarOdontologo(odontologo);

        if(odontologoARetornar == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(odontologoARetornar);
        }

    }

    @GetMapping
    public ResponseEntity<List<Odontologo>> buscarTodos() {

        return ResponseEntity.ok(odontologoService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscarOdontologoPorId(@PathVariable Integer id) {

        Optional<Odontologo> odontologo = odontologoService.buscarPorId(id);

        if(odontologo.isPresent()) {
            Odontologo odontologoARetornar = odontologo.get();

            return ResponseEntity.ok(odontologoARetornar);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @PutMapping
    public ResponseEntity<String> actualizarOdontologo(@RequestBody Odontologo odontologo) {
        Optional<Odontologo> odontologoOptional = odontologoService.buscarPorId(odontologo.getId());

        if(odontologoOptional.isPresent()) {

            odontologoService.actualizarOdontologo(odontologo);

            return ResponseEntity.ok("{\"message\": \"Odontologo modificado\"}");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Integer id) throws ResourceNotFoundException {
        odontologoService.eliminarOdontologo(id);
        return ResponseEntity.ok("{\"message\": \"Odontologo eliminado\"}");
    }

    @GetMapping("/apellido/{apellido}")
    public ResponseEntity<List<Odontologo>> buscarPorApellido(@PathVariable String apellido){
        List<Odontologo> listaOdontologos =odontologoService.buscarPorApellido(apellido);
        if(listaOdontologos.size()>0){
            return ResponseEntity.ok(listaOdontologos);
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<List<Odontologo>> buscarTodos(@PathVariable String nombre){
        return ResponseEntity.ok(odontologoService.buscarPorNombre(nombre));
    }

}
