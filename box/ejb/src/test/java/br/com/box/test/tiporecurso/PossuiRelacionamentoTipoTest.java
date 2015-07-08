package br.com.box.test.tiporecurso;

import javax.ejb.EJBException;
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

public class PossuiRelacionamentoTipoTest extends TestGenerico {

    @Inject
    private TipoService tipoService;

    @Test(expected = EJBException.class)
    public void naoPossuiRelacionamentoNullTest() {
        tipoService.possuiRelacionamento(null);
    }

    @Test(expected = EJBException.class)
    public void naoPossuiRelacionamentoIdNullTest() {
        TipoRecurso tipo = new TipoRecurso(null, "TV", Cor.AMARELO);
        tipoService.possuiRelacionamento(tipo);
    }

    @Test
    @UsingDataSet(value = "datasets/tb_tipo_recurso_relacionado.xml")
    @Cleanup(phase = TestExecutionPhase.AFTER, strategy = CleanupStrategy.USED_ROWS_ONLY)
    public void possuiRelacionamentoTest() {
        // Arrange
        TipoRecurso tipo = tipoService.buscarPorId(1L);
        // Act
        boolean retorno = tipoService.possuiRelacionamento(tipo);
        // Assert
        assertTrue(retorno);

    }

    @Test
    @UsingDataSet(value = "datasets/tb_tipo_recurso_relacionado.xml")
    @Cleanup(phase = TestExecutionPhase.AFTER, strategy = CleanupStrategy.USED_ROWS_ONLY)
    public void naoPossuiRelacionamentoTest() {
        // Arrange
        TipoRecurso tipo = tipoService.buscarPorId(2L);
        // Act
        boolean retorno = tipoService.possuiRelacionamento(tipo);
        // Assert
        assertFalse(retorno);
    }

}
