package webservices;

import entities.Logement;
import metiers.LogementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

@Path("/logements")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class LogementRessources {
    LogementBusiness Helper = new LogementBusiness();

    // Handle OPTIONS requests for CORS preflight

    @OPTIONS
    @Path("{path : .*}")
    public Response handleOptions() {
        return Response.ok()
                .header("Access-Control-Allow-Origin", "*") // Allow all origins
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization") // Allowed headers
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD") // Allowed methods
                .header("Access-Control-Allow-Credentials", "true") // Allow credentials if needed
                .build();
    }

    @GET
    public Response getAllLogements() {
        List<Logement> logements = Helper.getLogements();
        return Response.status(200)
                .header("Access-Control-Allow-Origin", "*") // Allow all origins
                .entity(logements)
                .build();
    }

    @GET
    @Path("/{reference}")
    public Response getLogementByReference(@PathParam("reference") int reference) {
        Logement logement = Helper.getLogementsByReference(reference);
        return Response.status(200)
                .header("Access-Control-Allow-Origin", "*") // Allow all origins
                .entity(logement)
                .build();
    }

    @POST
    public Response addLogement(Logement logement) {
        boolean added = Helper.addLogement(logement);
        return Response.status(200)
                .header("Access-Control-Allow-Origin", "*") // Allow all origins, added only once
                .header("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE, OPTIONS") // Optional: Allow specific methods
                .header("Access-Control-Allow-Headers", "Content-Type") // Optional: Allow specific headers
                .entity("Logement ajouté avec succès !")
                .build();
    }


    @PUT
    @Path("/{reference}")
    public Response updateLogement(@PathParam("reference") int reference, Logement logement) {
        boolean updated = Helper.updateLogement(reference, logement);
        return Response.status(200)
                .header("Access-Control-Allow-Origin", "*") // Allow all origins
                .entity("Logement mis à jour avec succès !")
                .build();
    }

    @GET
    @Path("/delegation/{delegation}")
    public Response getLogementsByDelegation(@PathParam("delegation") String delegation) {
        List<Logement> logements = Helper.getLogementsByDeleguation(delegation);
        return Response.status(200)
                .header("Access-Control-Allow-Origin", "*") // Allow all origins
                .entity(logements)
                .build();
    }
}