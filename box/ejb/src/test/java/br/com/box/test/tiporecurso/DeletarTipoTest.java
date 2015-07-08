package br.com.box.test.tiporecurso;

import javax.ejb.EJBException;
import javax.inject.Inject;

import org.jboss.arquillian.persistence.Cleanup;
import org.jboss.arquillian.persistence.CleanupStrategy;
import org.jboss.arquillian.persistence.ShouldMatchDataSet;
import org.jboss.arquillian.persistence.TestExecutionPhase;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;

import br.com.box.enuns.Cor;
import br.com.box.model.TipoRecurso;
import br.com.box.service.TipoService;
import br.com.box.test.TestGenerico;

public class DeletarTipoTest extends TestGenerico {

    @Inject
    private TipoService tipoService;

    @Test(expected = EJBException.class)
    public void deletarTipoNullTest() {
        // Arrange
        TipoRecurso tipo = new TipoRecurso(null, "TV", Cor.BRANCO);
        // Act
        tipoService.deletar(tipo);
    }

    @Test(expected = EJBException.class)
    public void deletarTipoIdNullTest() {
        // Act
        tipoService.deletar(null);
    }

    @Test
    @UsingDataSet("datasets/tb_tipo_recurso_relacionado.xml")
    @Cleanup(phase = TestExecutionPhase.AFTER, strategy = CleanupStrategy.USED_ROWS_ONLY)
    @Transactional(TransactionMode.ROLLBACK)
    public void deletarTipoRelacionadoTest() {
        // Arrange
        TipoRecurso tipo = tipoService.buscarPorId(1L);

        // Act
        boolean deletado = tipoService.deletar(tipo);

        // Assert
        assertFalse(deletado);
    }

    @Test
    @UsingDataSet("datasets/tb_tipo_recurso_relacionado.xml")
    @ShouldMatchDataSet("datasets/expected-delete-tb_tipo_recurso.xml")
    @Cleanup(phase = TestExecutionPhase.AFTER, strategy = CleanupStrategy.USED_ROWS_ONLY)
    @Transactional(TransactionMode.ROLLBACK)
    public void deletarTipoNaoRelacionadoTest() {
        // Arrange
        TipoRecurso tipo = tipoService.buscarPorId(2L);

        // Act
        boolean deletado = tipoService.deletar(tipo);

        // Assert
        assertTrue(deletado);
    }

}
