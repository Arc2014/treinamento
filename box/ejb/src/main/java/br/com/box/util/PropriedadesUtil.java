package br.com.box.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.jboss.logging.Logger;

public final class PropriedadesUtil {

    private static final Properties PROPS = new Properties();
    private static final Logger LOGGER = Logger.getLogger(PropriedadesUtil.class);

    private PropriedadesUtil() {
    }

    public static String getProperty(String prop) {
        return getProperties().getProperty(prop);
    }

    private static Properties getProperties() {
        if (PROPS.isEmpty()) {
            InputStream is = PropriedadesUtil.class.getClassLoader().getResourceAsStream("config.properties");
            try {
                PROPS.load(is);
            } catch (IOException e) {
            	LOGGER.error(e);
                throw new RuntimeException(e.getMessage());
            }
        }
        return PROPS;
    }

    public static boolean getPropertyAsBoolean(String prop) {
        return Boolean.parseBoolean(getProperty(prop));
    }

}
