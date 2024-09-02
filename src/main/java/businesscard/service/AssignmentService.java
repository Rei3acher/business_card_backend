package businesscard.service;

import businesscard.entity.Assignment;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.UUID;



@ApplicationScoped
@Transactional
public class AssignmentService {

    public boolean userExists(UUID uuid) {
        return Assignment.getAssigmentByUuid(uuid) != null;
    }
    public String getAccountName(UUID uuid) {
        return Assignment.getAssigmentByUuid(uuid).getAccountName();
    }

    public UUID addNewUser(String accountName) {
        UUID uuid = UUID.nameUUIDFromBytes(accountName.getBytes());
        Assignment assignment = new Assignment(uuid,accountName);
        if (Assignment.getAssigmentByUuid(uuid) == null) {
            assignment.persist();
        }
        return uuid;
    }
}
