package webservices;

import entities.Logement;
import metiers.LogementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("/logements")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LogementRessources {
    LogementBusiness Helper = new LogementBusiness();

    @GET
    public Response getAllLogements() {
        List<Logement> logements = Helper.getLogements();
        return Response.status(200).
                entity(logements).
                build();
    }

    @GET
    @Path("/{reference}")
    public Response getLogementByReference(@PathParam("reference") int reference) {
        Logement logement = Helper.getLogementsByReference(reference);

        return Response.status(200)
                .entity(logement)
                .build();
    }

    @POST
    public Response addLogement(Logement logement) {
        boolean added = Helper.addLogement(logement);

        return Response.status(200)
                .entity("Logement ajouté avec succès !")
                .build();

    }

    @PUT
    @Path("/{reference}")
    public Response updateLogement(@PathParam("reference") int reference, Logement logement) {
        boolean updated = Helper.updateLogement(reference, logement);

        return Response.status(200)
                .entity("Logement mis à jour avec succès !")
                .build();

    }

    @GET
    @Path("/delegation/{delegation}")
    public Response getLogementsByDelegation(@PathParam("delegation") String delegation) {
        List<Logement> logements = Helper.getLogementsByDeleguation(delegation);

        return Response.status(200)
                .entity(logements)
                .build();


    }
}
