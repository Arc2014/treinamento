package br.com.box.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.box.model.Grupo;
import br.com.box.qualifier.GrupoBean;

@Named
public class GrupoForm implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    @GrupoBean
    private Grupo grupo;

    private List<Grupo> grupos = new ArrayList<Grupo>();

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public List<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<Grupo> grupos) {
        this.grupos = grupos;
    }

}
