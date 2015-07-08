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

import br.com.box.model.Usuario;
import br.com.box.service.UsuarioService;

@FacesValidator("loginValidator")
@ViewScoped
@ManagedBean
public class LoginValidator implements Validator {

    @Inject
    private UsuarioService usuarioservice;

    @Override
    public void validate(FacesContext context, UIComponent component, Object loginNovo) {
        Long id = (Long) component.getAttributes().get("idUsuario");

        if (verificaLoginCadastrado(id, loginNovo.toString())) {
            avisoDuplicidadeLogin();
        }
    }

    private boolean verificaLoginCadastrado(Long id, String loginNovo) {
        Usuario usuario = usuarioservice.buscarPorLogin(loginNovo);
        return usuario != null && !usuario.getId().equals(id);
    }

    private void avisoDuplicidadeLogin() {
        FacesMessage msg = new FacesMessage("Usuário já Cadastrado",
                "Usuário já Cadastrado");
        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
        throw new ValidatorException(msg);
    }
}
