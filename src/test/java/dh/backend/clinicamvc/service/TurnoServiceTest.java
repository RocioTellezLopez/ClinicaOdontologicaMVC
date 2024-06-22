package dh.backend.clinicamvc.service;

import dh.backend.clinicamvc.Dto.request.TurnoRequestDto;
import dh.backend.clinicamvc.Dto.response.TurnoResponseDto;
import dh.backend.clinicamvc.entity.Domicilio;
import dh.backend.clinicamvc.entity.Odontologo;
import dh.backend.clinicamvc.entity.Paciente;
import dh.backend.clinicamvc.exception.BadRequestException;
import dh.backend.clinicamvc.service.impl.OdontologoService;
import dh.backend.clinicamvc.service.impl.PacienteService;
import dh.backend.clinicamvc.service.impl.TurnoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TurnoServiceTest {

    private static Logger LOGGER = LoggerFactory.getLogger(TurnoServiceTest.class);
    @Autowired
    private TurnoService turnoService;

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private OdontologoService odontologoService;

    private Odontologo odontologo;
    private Paciente paciente;
    private TurnoRequestDto turnoRequestDto;

    @BeforeEach
    void setUp1() throws BadRequestException {
        odontologo = new Odontologo();
        odontologo.setNombre("Lorena");
        odontologo.setApellido("Cosme");
        odontologo.setNumeroMatricula("123");

        Odontologo odontologoRegistardo = odontologoService.registrarOdontologo(odontologo);
        Integer idOdontologo = odontologoRegistardo.getId();

        paciente = new Paciente();
        paciente.setNombre("Menganito");
        paciente.setApellido("Cosme");
        paciente.setDni("464646");
        paciente.setFechaIngreso(LocalDate.of(2024,01,12));
        Domicilio domicilio = new Domicilio();
        domicilio.setCalle("Calle siempre viva");
        domicilio.setNumero(444);
        domicilio.setLocalidad("Pueblo libre");
        domicilio.setProvincia("Bio bio");
        paciente.setDomicilio(domicilio);

        Paciente pacienteRegistardo = pacienteService.registrarPaciente(paciente);
        Integer idPaciente = pacienteRegistardo.getId();

        turnoRequestDto = new TurnoRequestDto();
        turnoRequestDto.setOdontologo_id(idOdontologo);
        turnoRequestDto.setPaciente_id(idPaciente);
        turnoRequestDto.setFecha("2024-06-25");
    }

    @Test
    @DisplayName("Testear que un turno fue registrado")
    void testTurnoGuardado() throws BadRequestException {
        TurnoResponseDto turnoDb = turnoService.registrar(turnoRequestDto);

        assertNotNull(turnoDb);
        assertNotNull(turnoDb.getId());
    }


    @Test
    @DisplayName("Testear busqueda todos los turnos")
    void testBusquedaTodosTurnos() {

        List<TurnoResponseDto> turnos = turnoService.buscarTodos();

        assertTrue(turnos.size()!=0);
    }


}