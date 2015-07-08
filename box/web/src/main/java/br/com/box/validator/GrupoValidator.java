package br.com.box.validator;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

import br.com.box.model.Grupo;
import br.com.box.service.GrupoService;

@FacesValidator("GrupoValidator")
@ViewScoped
@ManagedBean
public class GrupoValidator implements Validator {

    @Inject
    private GrupoService gruposervice;

    @Override
    public void validate(FacesContext context, UIComponent component, Object nomeNovo) {
        Long id = (Long) component.getAttributes().get("idGrupo");

        if (verificaGrupoCadastrado(id, nomeNovo.toString())) {
            avisoDuplicidadeNome();
        }
    }

    private boolean verificaGrupoCadastrado(Long id, String nomeNovo) {
        Grupo grupo = gruposervice.buscarPorNome(nomeNovo);
        return grupo != null && !grupo.getId().equals(id);
    }

    private void avisoDuplicidadeNome() {
        FacesMessage msg = new FacesMessage("Nome já Cadastrado",
                "Nome já Cadastrado");
        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
        throw new ValidatorException(msg);
    }
}
