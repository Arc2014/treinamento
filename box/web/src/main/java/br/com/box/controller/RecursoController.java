package br.com.box.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import org.jboss.logging.Logger;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import br.com.box.form.RecursoForm;
import br.com.box.interceptor.InterceptadorExcecaoController;
import br.com.box.model.Recurso;
import br.com.box.model.TipoRecurso;
import br.com.box.service.RecursoService;
import br.com.box.service.TipoService;
import br.com.box.util.FacesUtil;
import br.com.box.util.Util;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.Iterables;

@ManagedBean
@ViewScoped
@Interceptors(InterceptadorExcecaoController.class)
public class RecursoController implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final String URLCONSULTA = "/pages/protected/recurso/consultar.jsf";
    private static final Logger LOGGER = Logger.getLogger(RecursoController.class);

    @Inject
    private RecursoForm recursoForm;

    @Inject
    private RecursoService recursoService;

    @Inject
    private TipoService tipoService;

    private UploadedFile file;

    @PostConstruct
    public void iniciar() {
        recursoService.verificarDiretorio();
    }

    public void associarImagemRecurso(FileUploadEvent event) {
        file = event.getFile();
    }

    public String gravar() {
        InputStream is = null;
        if (!Util.objectIsNull(file)) {
            is = getStreamImagemUpload();
            recursoForm.getRecurso().setDescricaoImagem(file.getFileName());
        }
        recursoService.salvar(recursoForm.getRecurso(), is);
        FacesUtil.mostrarMensagemSucesso("cadastro.sucesso");
        return URLCONSULTA + "?faces-redirect=true";
    }

    public List<Recurso> getRecursos() {
        if (Iterables.isEmpty(recursoForm.getRecursos())) {
            this.atualizarListaRecursos();
        }
        return recursoService.listarRecursos(recursoForm.getRecursoFiltro());
    }

    public void filtrarRecursos() {
        recursoForm.setRecursoFiltro(recursoForm.getRecurso());
    }

    private void atualizarListaRecursos() {
        recursoForm.setRecursos(recursoService.recuperarTodos());
    }

    public void deletar(Recurso recurso) {
        recursoService.deletarLogico(recurso);
        FacesUtil.mostrarMensagemSucesso("deletado.sucesso");
        this.atualizarListaUsuarios();
    }

    private void atualizarListaUsuarios() {
        recursoForm.setRecursos(recursoService.recuperarTodos());
    }

    public List<TipoRecurso> getTipos() {
        if (Iterables.isEmpty(recursoForm.getTipos())) {
            recursoForm.setTipos(tipoService.listar());
        }
        return recursoForm.getTipos();
    }

    public String alterar() {
        InputStream is = null;
        if (!Util.objectIsNull(file)) {
            is = getStreamImagemUpload();
            recursoForm.getRecurso().setDescricaoImagem(file.getFileName());
        }
        recursoService.alterar(recursoForm.getRecurso(), is);
        FacesUtil.mostrarMensagemSucesso("cadastro.sucesso");
        return URLCONSULTA + "?faces-redirect=true";
    }

    public RecursoForm getRecursoForm() {
        return recursoForm;
    }

    public void setRecursoForm(RecursoForm recursoForm) {
        this.recursoForm = recursoForm;
    }

    private InputStream getStreamImagemUpload() {
        try {
            return file.getInputstream();
        } catch (IOException e) {
            LOGGER.error(e);
            throw new RuntimeException(e.getMessage());
        }
    }

    public StreamedContent getImagemArquivo() {
        StreamedContent content = null;
        if (getRecursoForm().getRecurso().possuiImagem()) {
            content = getStreamedContent(getRecursoForm().getRecurso().getCaminhoImagem());
        }
        return content;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public static StreamedContent getStreamedContent(String caminho) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(caminho));
        InputStream is = null;
        try {
            is = new FileInputStream(caminho);
            return new DefaultStreamedContent(is);
        } catch (FileNotFoundException e) {
            is = FacesUtil.getServletContext().getResourceAsStream("/resources/img/sem_foto.jpg");
            return new DefaultStreamedContent(is);
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
