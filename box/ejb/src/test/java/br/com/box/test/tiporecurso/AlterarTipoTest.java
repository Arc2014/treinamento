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

public class AlterarTipoTest extends TestGenerico {

    @Inject
    private TipoService tipoService;

    @Test(expected = EJBException.class)
    public void alterarTipoNull() {
        // Act
        tipoService.alterar(null);
    }

    @Test(expected = EJBException.class)
    public void alterarTipoIdNull() {
        // Arrange
        TipoRecurso tipo = new TipoRecurso(null, "TV", Cor.AZUL);

        // Act
        tipoService.alterar(tipo);
    }

    @Test
    @UsingDataSet("datasets/tb_tipo_recurso.xml")
    @ShouldMatchDataSet("datasets/expected-tb_tipo_recurso.xml")
    @Cleanup(phase = TestExecutionPhase.AFTER, strategy = CleanupStrategy.USED_ROWS_ONLY)
    @Transactional(TransactionMode.ROLLBACK)
    public void alterarTipo() {
        // Arrange
        TipoRecurso tipo = tipoService.buscarPorId(2L);
        tipo.setNome("Notebook");

        // Act
        tipoService.alterar(tipo);
        TipoRecurso novoTipo = tipoService.buscarPorId(tipo.getId());

        // Assert
        assertEquals(novoTipo.getNome(), "Notebook");

    }

}
