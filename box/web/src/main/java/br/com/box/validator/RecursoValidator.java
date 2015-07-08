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

import br.com.box.model.Recurso;
import br.com.box.service.RecursoService;

@FacesValidator("recursoValidator")
@ViewScoped
@ManagedBean
public class RecursoValidator implements Validator {

    @Inject
    private RecursoService recursoService;

    @Override
    public void validate(FacesContext context, UIComponent component, Object patrimonio) {
        Long id = (Long) component.getAttributes().get("idRecurso");

        if (verificaPatrimonioCadastrado(id, patrimonio.toString())) {
            this.avisoDuplicidade();
        }
    }

    private boolean verificaPatrimonioCadastrado(Long id, String patrimonio) {
        Recurso recurso = recursoService.buscarPorPatrimonio(patrimonio);
        return recurso != null && !recurso.getId().equals(id);
    }

    private void avisoDuplicidade() {
        FacesMessage msg = new FacesMessage("Patrimônio já Cadastrado",
                "Patrimônio já Cadastrado");
        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
        throw new ValidatorException(msg);
    }
}
