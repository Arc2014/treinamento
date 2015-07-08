package br.com.box.test.tiporecurso;

import javax.ejb.EJBException;
import javax.inject.Inject;

import org.jboss.arquillian.persistence.Cleanup;
import org.jboss.arquillian.persistence.CleanupStrategy;
import org.jboss.arquillian.persistence.TestExecutionPhase;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.junit.Test;

import br.com.box.model.TipoRecurso;
import br.com.box.service.TipoService;
import br.com.box.test.TestGenerico;

public class BuscarPorIdTipoTest extends TestGenerico {

    @Inject
    private TipoService tipoService;

    @Test
    public void buscarPorIdVazioTest() {
        // Act
        TipoRecurso tipo = tipoService.buscarPorId(99L);
        // Assert
        assertNull(tipo);
    }

    @Test(expected = EJBException.class)
    public void buscarPorIdNullTest() {
        // Act
        tipoService.buscarPorId(null);

    }

    @Test
    @UsingDataSet("datasets/tb_tipo_recurso.xml")
    @Cleanup(phase = TestExecutionPhase.AFTER, strategy = CleanupStrategy.USED_ROWS_ONLY)
    public void buscarPorIdTest() {
        // Act
        TipoRecurso tipo = tipoService.buscarPorId(1L);
        // Assert
        assertEquals(new Long(1), tipo.getId());

    }

}
