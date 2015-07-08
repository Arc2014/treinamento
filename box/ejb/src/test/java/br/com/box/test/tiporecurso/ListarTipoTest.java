package br.com.box.test.tiporecurso;

import java.util.List;

import javax.inject.Inject;

import org.jboss.arquillian.persistence.Cleanup;
import org.jboss.arquillian.persistence.CleanupStrategy;
import org.jboss.arquillian.persistence.TestExecutionPhase;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.junit.Test;

import br.com.box.enuns.Cor;
import br.com.box.model.TipoRecurso;
import br.com.box.service.TipoService;
import br.com.box.test.TestGenerico;

public class ListarTipoTest extends TestGenerico {

    @Inject
    private TipoService tipoService;

    @Test
    public void listarVazioTest() {
        // Atc
        List<TipoRecurso> listaTipoRecursos = tipoService.listar();
        // Assert
        assertEquals(0, listaTipoRecursos.size());
    }

    @UsingDataSet("datasets/tb_tipo_recurso.xml")
    @Cleanup(phase = TestExecutionPhase.AFTER, strategy = CleanupStrategy.USED_ROWS_ONLY)
    @Test
    public void listarPopuladoTest() {
        // Arrange
        TipoRecurso tipo = new TipoRecurso(1L, "Telefone", Cor.AZUL);
        TipoRecurso tipo2 = new TipoRecurso(2L, "Data Show", Cor.VERDE);
        // Act
        List<TipoRecurso> listaTipoRecursos = tipoService.listar();

        // Assert
        assertTrue(listaTipoRecursos.contains(tipo));
        assertTrue(listaTipoRecursos.contains(tipo2));
        assertEquals(2, listaTipoRecursos.size());
    }

}
