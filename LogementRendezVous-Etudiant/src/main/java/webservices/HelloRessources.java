package webservices;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
@Path("/hello")
public class HelloRessources {
    @GET
    @Path("/hi")
    @Produces(MediaType.TEXT_PLAIN)
    public Response sayHello() {
        return Response.
                status(200).
                entity("Hello World !").
                build();
    }
}
