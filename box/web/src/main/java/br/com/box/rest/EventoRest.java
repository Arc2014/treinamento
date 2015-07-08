package br.com.box.rest;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.logging.Logger;

import br.com.box.facade.AgendaFacade;
import br.com.box.model.Agenda;
import br.com.box.service.AgendaService;

import com.google.common.base.Strings;

@Path("/evento")
@PermitAll
public class EventoRest {

    @Inject
    private AgendaService agendaService;

    @Inject
    private AgendaFacade agendaFacade;

    private static final Logger LOGGER = Logger.getLogger(EventoRest.class);

    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<Agenda> buscar() {
        return agendaService.listar();
    }

    @GET
    @Path("/{id:[0-9][0-9]*}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Agenda buscarPorId(@PathParam("id") String id) {
        Agenda retorno = null;
        if (!Strings.isNullOrEmpty(id)) {
            retorno = agendaService.buscarPorId(new Long(id));
        }
        return retorno;
    }

    @GET
    @Path("/descricao/{descricao}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<Agenda> buscarPorDescricao(@PathParam("descricao") String descricao) {
        List<Agenda> retorno = null;
        if (!Strings.isNullOrEmpty(descricao)) {
            retorno = agendaService.listarPorDescricaoEvento(descricao);
        }
        return retorno;
    }

    @GET
    @Path("/pessoa/{nome}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<Agenda> buscarPorPessoa(@PathParam("nome") String nome) {
        List<Agenda> retorno = null;
        if (!Strings.isNullOrEmpty(nome)) {
            retorno = agendaService.listarPorPessoaNome(nome);
        }
        return retorno;
    }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response atualizar(Agenda agenda) {
        try {
            agendaFacade.salvarAgenda(agenda);
            return Response.status(Response.Status.CREATED).entity("Evento Atualizado!").build();
        } catch (Exception e) {
            LOGGER.error(e);
            return Response.serverError().entity("Ocorreu um erro atualizar Evento! Erro:" + e.getMessage()).build();
        }

    }

    @PUT
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response salvar(Agenda agenda) {
        try {
            agendaFacade.salvarAgenda(agenda);
            return Response.status(Response.Status.CREATED).entity("Evento Salvo!").build();
        } catch (Exception e) {
            LOGGER.error(e);
            return Response.serverError().entity("Ocorreu um erro ao agendar o Evento! Erro:" + e.getMessage()).build();
        }

    }

    @DELETE
    @Path("/{id:[0-9][0-9]*}")
    public Response deletar(@PathParam("id") String id) {
        try {
            agendaService.deletar(agendaService.buscarPorId(new Long(id)));
            return Response.status(Response.Status.NO_CONTENT).entity("Evento apagado!").build();
        } catch (Exception e) {
            LOGGER.error(e);
            return Response.serverError().entity("Ocorreu um erro ao apagar Evento! Erro:" + e.getMessage()).build();
        }

    }

}
