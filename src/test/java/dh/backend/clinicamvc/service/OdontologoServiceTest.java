package dh.backend.clinicamvc.service;

import dh.backend.clinicamvc.entity.Odontologo;
import dh.backend.clinicamvc.exception.BadRequestException;
import dh.backend.clinicamvc.service.impl.OdontologoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OdontologoServiceTest {
    private static Logger LOGGER = LoggerFactory.getLogger(OdontologoServiceTest.class);

    @Autowired
    private static OdontologoService odontologoService;
    private Odontologo odontologo; //generamos odontólogo para usarlo

    @BeforeEach
    void setUp(){
        odontologo = new Odontologo();
        odontologo.setNombre("Menganito");
        odontologo.setApellido("Cosme");
        odontologo.setNumeroMatricula("123");
    }

    @Test
    @DisplayName("Testear que un odontologo fue guardado")
    void testGuardarOdontologo () throws BadRequestException {
        Odontologo odontologoADevolver = odontologoService.registrarOdontologo(odontologo);

        assertNotNull(odontologoADevolver);
        assertNotNull(odontologoADevolver.getId());
    }

    @Test
    @DisplayName("Testear busqueda todos los Odontologos")
    void testBuscarTodos () {
        List<Odontologo> odontologos = odontologoService.buscarTodos();

        assertTrue(odontologos.size()!=0);
    }

}
