package br.com.box.test;

import junit.framework.TestCase;

import org.apache.velocity.context.Context;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.OverProtocol;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.joda.time.ReadableInstant;
import org.joda.time.base.BaseDateTime;
import org.joda.time.chrono.BaseChronology;
import org.joda.time.field.AbstractPartialFieldProperty;
import org.junit.runner.RunWith;

import br.com.box.dao.GenericDAO;
import br.com.box.enuns.Cor;
import br.com.box.exception.AgendamentoNaoDisponivelException;
import br.com.box.model.EntidadeComum;
import br.com.box.service.TipoService;
import br.com.box.util.Util;

import com.google.common.base.Preconditions;

@RunWith(Arquillian.class)
public abstract class TestGenerico extends TestCase {

    public static JavaArchive jar;

    @Deployment
    @OverProtocol("Servlet 3.0")
    public static JavaArchive createDeployment() {
        if (jar == null) {
            jar = ShrinkWrap.create(JavaArchive.class, "testBox.jar")
                    .addPackage(ReadableInstant.class.getPackage())
                    .addPackage(BaseDateTime.class.getPackage())
                    .addPackage(BaseChronology.class.getPackage())
                    .addPackage(Context.class.getPackage())
                    .addPackage(AbstractPartialFieldProperty.class.getPackage())
                    .addPackage(GenericDAO.class.getPackage())
                    .addPackage(Cor.class.getPackage())
                    .addPackage(EntidadeComum.class.getPackage())
                    .addPackage(Util.class.getPackage())
                    .addPackage(AgendamentoNaoDisponivelException.class.getPackage())
                    .addPackage(Preconditions.class.getPackage())
                    .addClass(TipoService.class)
                    .addClass(TestGenerico.class)
                    .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                    .addAsManifestResource("META-INF/persistence.xml", "persistence.xml");

            System.out.println(jar.toString(true));
        }
        return jar;
    }
}
