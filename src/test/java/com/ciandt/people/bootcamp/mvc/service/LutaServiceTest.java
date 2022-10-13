package com.ciandt.people.bootcamp.mvc.service;

import com.ciandt.people.bootcamp.cleanarch.domain.exception.LutadorNotFoundException;
import com.ciandt.people.bootcamp.mvc.entity.Lutador;
import com.ciandt.people.bootcamp.mvc.repository.LutadorRepository;
import com.ciandt.people.bootcamp.mvc.repository.model.LutadorModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LutaServiceTest {
    private LutaService lutaService;
    private final LutadorRepository lutadorRepository = mock(LutadorRepository.class);

    @BeforeEach
    public void init() {
        this.lutaService = new LutaService(lutadorRepository);
    }

    @Test
    void executeFindByIdWithValidIdWithAExistingLutadorWillSuccessfully() {
        final Long id = Long.valueOf(1);
        String nome = "Street Fighter";

        when(lutadorRepository.findById(id)).thenReturn(Optional.ofNullable(LutadorModel.builder().nome(nome).build()));

        Optional<Lutador> lutador = lutaService.findByID(id);

        assertTrue(lutador.isPresent());
        assertEquals(nome, lutador.get().getNome());
    }

    @Test
    void executeFindByIdWithValidIdWithANonExistingUserWillSuccessfully() {
        final Long id = Long.valueOf(456);

        when(lutadorRepository.findById(id)).thenReturn(Optional.empty());

        LutadorNotFoundException thrown = assertThrows(
                LutadorNotFoundException.class,
                () -> lutaService.findByID(id),
                "Expected should throw an Exception"
        );
        assertNotNull(thrown.getMessage());
    }

    @Test
    void executeFindByIdWithNullArgumentsWillFail() {
        final Long id = null;

        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> lutaService.findByID(id),
                "Expected should throw an Exception"
        );

        assertTrue(thrown.getMessage().contains("ID não pode estar vazio"));
    }
}