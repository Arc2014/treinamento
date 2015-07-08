package br.com.box.test.util;

import junit.framework.TestCase;

import org.junit.Test;

import br.com.box.util.Util;

public class TestUtil extends TestCase {

    @Test
    public void testObjectIsNullComObjetoNulo() {
        // Arrange
        Object objeto = null;

        // Act
        boolean result = Util.objectIsNull(objeto);

        // Assert
        assertEquals(true, result);
    }

    @Test
    public void testObjectIsNullComObjetoNaoNulo() {
        // Arrange
        Object objeto = new Object();

        // Act
        boolean result = Util.objectIsNull(objeto);

        // Assert
        assertEquals(false, result);
    }

}
