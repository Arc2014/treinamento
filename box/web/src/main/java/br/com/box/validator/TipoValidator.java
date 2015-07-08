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

import br.com.box.model.TipoRecurso;
import br.com.box.service.TipoService;

@FacesValidator("tipoValidator")
@ViewScoped
@ManagedBean
public class TipoValidator implements Validator {

    @Inject
    private TipoService tipoService;

    @Override
    public void validate(FacesContext context, UIComponent component, Object tipoNovo) {
        Long id = (Long) component.getAttributes().get("idTipo");

        if (verificaTipoCadastrado(id, tipoNovo.toString())) {
            avisoDuplicidadeLogin();
        }
    }

    private boolean verificaTipoCadastrado(Long id, String tipoNovo) {
        TipoRecurso tipo = tipoService.buscarPorNome(tipoNovo);
        return tipo != null && !tipo.getId().equals(id);
    }

    private void avisoDuplicidadeLogin() {
        FacesMessage msg = new FacesMessage("Tipo já Cadastrado", "Tipo já Cadastrado");
        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
        throw new ValidatorException(msg);
    }
}
