package businesscard.service;

import businesscard.rest.CusAssignment;
import businesscard.rest.CusClient;
import io.quarkus.cache.CacheResult;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;
import java.time.LocalDate;

@ApplicationScoped
public class CusService {

    public CusClient cusClient = new CusClient();

    @CacheResult(cacheName = "user-cache")
    public CusAssignment getUserData(String username) {
        Response res = cusClient.getUserData(username);
        CusAssignment user = new CusAssignment(username,"Max","Mustermann","Dr.","m","MMU","123456789",1980,"SCI","Software Developer","Stuttgart",LocalDate.now());
        return user;
    }
}
