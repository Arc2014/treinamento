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

public class BuscarPorNomeTipoTest extends TestGenerico {

    @Inject
    private TipoService tipoService;

    @Test
    public void lbuscarPorNomeNaoExistenteTest() {
        // Act
        TipoRecurso tipo = tipoService.buscarPorNome("TV");

        // Assert
        assertNull(tipo);
    }

    @Test(expected = EJBException.class)
    public void lbuscarPorNomeNullTest() {
        // Act
        tipoService.buscarPorNome(null);

    }

    @Test
    @UsingDataSet("datasets/tb_tipo_recurso.xml")
    @Cleanup(phase = TestExecutionPhase.AFTER, strategy = CleanupStrategy.USED_ROWS_ONLY)
    public void lbuscarPorNomeTest() {
        // Act
        TipoRecurso tipo = tipoService.buscarPorNome("Data Show");

        // Assert
        assertEquals("Data Show", tipo.getNome());

    }

}
