package br.com.box.controller;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.servlet.ServletException;

import org.jboss.logging.Logger;

import br.com.box.model.Usuario;
import br.com.box.service.UsuarioService;
import br.com.box.util.FacesUtil;
import br.com.box.util.UtilitarioSeguranca;

@ManagedBean(name = "loginController")
@SessionScoped
public class LoginController {

    private static final Logger LOGGER = Logger.getLogger(LoginController.class);

    @Inject
    private UsuarioService usuarioService;

    private Usuario usuario;

    public LoginController() {
        usuario = new Usuario();
    }

    public void acessarSistema() {
        try {
            login();
        } catch (ServletException e) {
            FacesUtil.mostrarMensagemErro("erro.mensagem.usuario.senha.invalidos");
            LOGGER.error(FacesUtil.getMessage("erro.mensagem.usuario.senha.invalidos"), e);
        }
        String retorno = FacesUtil.getServletRequest().getHeader("Referer");
        if (encalinhaPaginaSolicitada(retorno)) {
            redirecionar(retorno);
        } else {
            redirecionar(FacesUtil.gerarUrl("/pages/protected/index.jsf?faces-redirect=true"));
        }
    }

    public boolean encalinhaPaginaSolicitada(String retorno) {
        return retorno.contains("pages/") && (!retorno.contains("pages/public/login.jsf"));
    }

    public String sair() {
        UtilitarioSeguranca.logout();
        return "/pages/public/login.xhtml?faces-redirect=true";
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    private void login() throws ServletException {
        UtilitarioSeguranca.logout();
        UtilitarioSeguranca.login(usuario);
        usuario = obterUsuarioLogado();
    }

    private Usuario obterUsuarioLogado() {
        return usuarioService.buscarPorLogin(UtilitarioSeguranca.getUsuarioLogado());
    }

    private void redirecionar(String retorno) {
        try {
            FacesUtil.getExternalContext().getFlash().setKeepMessages(true);
            FacesUtil.redirect(retorno);
        } catch (IOException e) {
            FacesUtil.mostrarMensagemErro("erro.mensagem.efetuar.login");
            LOGGER.error(FacesUtil.getMessage("erro.mensagem.efetuar.login"), e);
        }
    }

}
