package es.um.sisdist.backend.Service;

import es.um.sisdist.backend.Service.impl.AppLogicImpl;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/u/{id}/db")
// endpoint de bases de datos
/**
 * 
 * En el cuerpo de la petición se incluirá el nombre de la base de datos y otros
 * parámetros que se necesiten, así como unos datos iniciales que introducir en
 * la base de datos, recordad, cada entrada tiene una clave y un valor
 */
public class DataBaseEndpoint {
	private AppLogicImpl impl = AppLogicImpl.getInstance();


	// metodo para que el usuario pueda crear bases de datos
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createDatabase(@PathParam("id") String userId, String databaseName) {
		// Utiliza el ID del usuario y el nombre de la base de datos para crearla
		// También puedes obtener los datos iniciales de databaseRequest.getD()

		// Código para crear la base de datos

		// Construye la URL de la base de datos
		String databaseUrl = "/u/" + userId + "/db/" + databaseName;

		// Construye la respuesta con el código HTTP 201 Created y la cabecera Location
		return Response.status(Response.Status.CREATED).header("Location", databaseUrl).build();
	}

	// metodo consulta de bases de datos
	@GET
    @Path("/{name}")
    public Response getDatabase(@PathParam("id") String userId, @PathParam("name") String databaseName) {
        // Lógica para consultar la base de datos del usuario con el ID y nombre especificados
        
        // Código para consultar la base de datos

        return Response.ok().build();
    }
}
