package com.ar.ndifabio.reservaDeTurnos.service;

import com.ar.ndifabio.reservaDeTurnos.entity.Paciente;
import com.ar.ndifabio.reservaDeTurnos.repositories.IPacienteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

class PacienteServiceTest {

    @Mock
    private IPacienteRepository repository;

    @InjectMocks
    private PacienteService pacienteService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAll() {
        List<Paciente> pacientes = new ArrayList<>();
        pacientes.add(new Paciente());
        when(repository.findAll()).thenReturn(pacientes);

        List<Paciente> result = pacienteService.getAll();

        Assertions.assertEquals(1, result.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    public void testGetById() {
        Long id = 1L;
        Paciente paciente = new Paciente();
        when(repository.findById(id)).thenReturn(Optional.of(paciente));

        Optional<Paciente> result = pacienteService.getById(id);

        Assertions.assertTrue(result.isPresent());
        verify(repository, times(1)).findById(id);
    }

    @Test
    public void testCreateNewPaciente() {
        Paciente paciente = new Paciente();
        paciente.setDni(123456789);
        when(repository.buscarPorDni(paciente.getDni())).thenReturn(Optional.empty());
        when(repository.save(paciente)).thenReturn(paciente);

        Paciente result = pacienteService.create(paciente);

        Assertions.assertNotNull(result.getFechaDeAlta());
        verify(repository, times(1)).save(paciente);
    }

    @Test
    public void testCreatePacienteWithExistingDni() {
        Paciente paciente = new Paciente();
        paciente.setDni(123456789);
        when(repository.buscarPorDni(paciente.getDni())).thenReturn(Optional.of(paciente));

        Assertions.assertThrows(IllegalArgumentException.class, () -> pacienteService.create(paciente));
        verify(repository, never()).save(any());
    }

    @Test
    public void testUpdateExistingPaciente() {
        Long id = 1L;
        Paciente paciente = new Paciente();
        paciente.setDni(123456789);
        Optional<Paciente> existingPaciente = Optional.of(paciente);
        when(repository.findById(id)).thenReturn(existingPaciente);
        when(repository.buscarPorDni(paciente.getDni())).thenReturn(Optional.empty());
        when(repository.save(paciente)).thenReturn(paciente);

        Paciente result = pacienteService.update(id, paciente);

        Assertions.assertEquals(paciente.getApellido(), result.getApellido());
        verify(repository, times(1)).save(paciente);
    }

    @Test
    public void testUpdatePacienteWithNonExistingId() {
        Long id = 1L;
        Paciente paciente = new Paciente();
        when(repository.findById(id)).thenReturn(Optional.empty());

        Assertions.assertThrows(IllegalArgumentException.class, () -> pacienteService.update(id, paciente));
        verify(repository, never()).save(any());
    }

    @Test
    public void testUpdatePacienteWithExistingDni() {
        Long id = 1L;
        Paciente paciente = new Paciente();
        paciente.setDni(123456789);
        Optional<Paciente> existingPaciente = Optional.of(new Paciente());
        when(repository.findById(id)).thenReturn(existingPaciente);
        when(repository.buscarPorDni(paciente.getDni())).thenReturn(Optional.of(new Paciente()));

        Assertions.assertThrows(IllegalArgumentException.class, () -> pacienteService.update(id, paciente));
        verify(repository, never()).save(any());
    }

    @Test
    public void testDeleteExistingPaciente() {
        Long id = 1L;
        Paciente paciente = new Paciente();
        when(repository.findById(id)).thenReturn(Optional.of(paciente));

        pacienteService.delete(id);

        verify(repository, times(1)).deleteById(id);
    }

    @Test
    public void testDeletePacienteWithNonExistingId() {
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.empty());

        Assertions.assertThrows(IllegalArgumentException.class, () -> pacienteService.delete(id));
        verify(repository, never()).deleteById(any());
    }
}