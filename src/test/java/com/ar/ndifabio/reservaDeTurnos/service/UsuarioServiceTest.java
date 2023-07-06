package com.ar.ndifabio.reservaDeTurnos.service;

import com.ar.ndifabio.reservaDeTurnos.entity.Odontologo;
import com.ar.ndifabio.reservaDeTurnos.entity.Paciente;
import com.ar.ndifabio.reservaDeTurnos.entity.Rol;
import com.ar.ndifabio.reservaDeTurnos.entity.Usuario;
import com.ar.ndifabio.reservaDeTurnos.repositories.IUsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.*;

class UsuarioServiceTest {
    @Mock
    private IUsuarioRepository repository;
    @Mock
    private OdontologoService odontologoService;
    @Mock
    private PacienteService pacienteService;
    @InjectMocks
    private UsuarioService usuarioService;
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateAdminUserWithExistingMatricula() {
        Usuario body = new Usuario();
        body.setUserName("pablovidal");
        body.setIsAdmin(true);
        Odontologo odontologo = new Odontologo();
        when(odontologoService.buscarPorMatricula(body.getIdNumber())).thenReturn(Optional.of(odontologo));
        when(repository.save(body)).thenReturn(body);

        Usuario result = usuarioService.create(body);

        Assertions.assertEquals(Rol.ROLE_ADMIN, result.getRol());
        verify(repository, times(1)).save(body);
    }

    @Test
    public void testCreateAdminUserWithNonExistingMatricula() {
        Usuario body = new Usuario();
        body.setUserName("pablovidal");
        body.setIsAdmin(true);
        when(odontologoService.buscarPorMatricula(body.getIdNumber())).thenReturn(Optional.empty());

        Assertions.assertThrows(IllegalArgumentException.class, () -> usuarioService.create(body));
        verify(repository, never()).save(any());
    }

    @Test
    public void testCreateRegularUserWithExistingDni() {
        Usuario body = new Usuario();
        body.setUserName("123");
        body.setIsAdmin(false);
        Paciente paciente = new Paciente();
        when(pacienteService.buscarPorDni(body.getIdNumber())).thenReturn(Optional.of(paciente));
        when(repository.save(body)).thenReturn(body);

        Usuario result = usuarioService.create(body);

        Assertions.assertEquals(Rol.ROLE_USER, result.getRol());
        verify(repository, times(1)).save(body);
    }

    @Test
    public void testCreateRegularUserWithNonExistingDni() {
        Usuario body = new Usuario();
        body.setUserName("123");
        body.setIsAdmin(false);
        when(pacienteService.buscarPorDni(body.getIdNumber())).thenReturn(Optional.empty());


        Assertions.assertThrows(IllegalArgumentException.class, () -> usuarioService.create(body));
        verify(repository, never()).save(any());
    }
}