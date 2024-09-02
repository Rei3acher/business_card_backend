package businesscard.rest.resource;

import com.google.zxing.WriterException;
import businesscard.service.AssignmentService;
import businesscard.service.QrCodeService;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import org.jboss.resteasy.reactive.RestQuery;

import java.io.IOException;
import java.util.UUID;

@Path("cus")
public class CusRessource {

    @Inject
    AssignmentService assignmentService;

    @Inject
    QrCodeService qrCodeService;

    @POST
    @Path("/addUser")
    @Produces("application/json")
    public UUID addNewUser(@RestQuery String accountName) {
        //Logic to add new user
        UUID uuid = assignmentService.addNewUser(accountName);
        if (uuid != null) {
            try {
                qrCodeService.getQrCodeWithImg(uuid);
            } catch (WriterException | IOException e) {
                e.printStackTrace();
            }
        }
        return uuid;
    }


}


