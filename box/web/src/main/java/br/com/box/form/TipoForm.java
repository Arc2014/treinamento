package br.com.box.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.box.model.TipoRecurso;
import br.com.box.qualifier.TipoBean;

@Named
public class TipoForm implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<TipoRecurso> listaTipos = new ArrayList<TipoRecurso>();

    @Inject
    @TipoBean
    private TipoRecurso tipo;

    public List<TipoRecurso> getListaTipos() {
        return listaTipos;
    }

    public void setListaTipos(List<TipoRecurso> listaTipos) {
        this.listaTipos = listaTipos;
    }

    public TipoRecurso getTipo() {
        return tipo;
    }

    public void setTipo(TipoRecurso tipo) {
        this.tipo = tipo;
    }
}
