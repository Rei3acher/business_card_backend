package businesscard.rest;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class CusClient {

    public Response getUserData(String accountName) {
        //Call to the CUS Backend
        String url = String.format("?accountName=", accountName,"/card");
        return Response.ok().build();
    }
}
