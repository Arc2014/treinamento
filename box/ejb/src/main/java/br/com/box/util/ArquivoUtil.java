package br.com.box.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.jboss.logging.Logger;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.io.ByteStreams;
import com.google.common.io.Files;

/**
 * Classe utilitária para desenvolvimento JSF
 */
public final class ArquivoUtil {

    private static final Logger LOGGER = Logger.getLogger(ArquivoUtil.class);

    private ArquivoUtil() {

    }

    public static void salvarArquivo(InputStream is, String caminho) {
        try {
            IOUtils.copy(is, new FileOutputStream(new File(caminho)));
        } catch (IOException e) {
            LOGGER.error(e);
            throw new RuntimeException(e.getMessage());
        }
    }

    public static StreamedContent getStreamedContent(String caminho) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(caminho));
        try {
            InputStream is = new FileInputStream(caminho);
            return new DefaultStreamedContent(is);
        } catch (FileNotFoundException e) {
       		LOGGER.error(e);
        	throw new RuntimeException(e.getMessage());            
        }
    }

    public static void salvarArquivo(String fileName, InputStream in) {
        try {
            FileOutputStream outputStream = new FileOutputStream(fileName);
            ByteStreams.copy(in, outputStream);
            outputStream.close();
        } catch (IOException e) {
        	LOGGER.error(e);
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void deletarArquivo(String caminhoArquivo) {
        File arquivo = new File(caminhoArquivo);
        arquivo.delete();
    }

    public static String recuperarExtensao(String nomeArquivo) {
        return Files.getFileExtension(nomeArquivo);
    }

    public static String montarCaminho(String propriedadeCaminho, String nomeArquivo) {
        String caminhoPasta = PropriedadesUtil.getProperty(propriedadeCaminho);
        return caminhoPasta + nomeArquivo;
    }

    public static boolean validaExtensaoImagem(String extensao) {
        List<String> extensoesImagem = Arrays.asList("jpg", "jpeg", "gif", "png");
        return extensoesImagem.contains(extensao.toLowerCase());
    }

}
