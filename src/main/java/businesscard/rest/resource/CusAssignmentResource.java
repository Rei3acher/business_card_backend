package businesscard.rest.resource;

import businesscard.rest.CusAssignment;
import businesscard.rest.UserUUID;
import businesscard.service.AssignmentService;
import businesscard.service.CusService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.jboss.resteasy.reactive.RestQuery;



@Path("assignment")
public class CusAssignmentResource {

    @Inject
    AssignmentService assignmentService;

    @Inject
    CusService cusService;

    @GET()
    @Path("/uuid")
    public CusAssignment getUserByUuid(@RestQuery UserUUID uuid) {
        if (assignmentService.userExists(uuid.getUuid())){
            //Call for user details to the CUS Backend
            return cusService.getUserData(assignmentService.getAccountName(uuid.getUuid()));
        }
        return null;
    }
}




