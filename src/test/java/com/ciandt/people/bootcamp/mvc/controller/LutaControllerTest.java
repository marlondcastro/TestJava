package com.ciandt.people.bootcamp.mvc.controller;

import com.ciandt.people.bootcamp.mvc.controller.model.LutadorClient;
import com.ciandt.people.bootcamp.mvc.entity.Lutador;
import com.ciandt.people.bootcamp.cleanarch.domain.exception.LutadorNotFoundException;
import com.ciandt.people.bootcamp.mvc.service.LutaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LutaControllerTest {
    private LutaController controller;
    private final LutaService lutaService = mock(LutaService.class);

    @BeforeEach
    public void init() {
        this.controller = new LutaController(lutaService);
    }

    @Test
    void executeGetByIdWithNullArgumentsWillFail() {
        final Long id = null;

        when(lutaService.findByID(id)).thenThrow(new IllegalArgumentException("Id não pode estar vazio"));

        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> controller.getById(id),
                "Expected should throw an Exception"
        );

        assertTrue(thrown.getMessage().contains("Id não pode estar vazio"));
    }

    @Test
    void executeGetByIdWithValidArgumentsButNotFoundLutadorWillFail() {
        final Long id = Long.valueOf(666);

        when(lutaService.findByID(id)).thenThrow(new LutadorNotFoundException(Long.valueOf(666)));

        LutadorNotFoundException thrown = assertThrows(
                LutadorNotFoundException.class,
                () -> controller.getById(id),
                "Expected should throw an Exception"
        );

        assertNotNull(thrown.getMessage());
    }

    /**
     * Execute wil valid name will successfully.
     */
    @Test
    void executeGetByIdWithValidIdWillSuccessfully() {
        final Long id = Long.valueOf(1);
        final Lutador lutador = Lutador.builder()
                .id(id).nome("Mike Tyson")
                .build();

        when(lutaService.findByID(id)).thenReturn(Optional.ofNullable(lutador));

        LutadorClient ucLutadorClient = controller.getById(id).get();

        assertNotNull(ucLutadorClient);
        assertEquals(id, ucLutadorClient.getId());
    }
}