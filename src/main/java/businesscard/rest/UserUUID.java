package businesscard.rest;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.UUID;

//This class is used to get the UUID from the REST call
//Also it is used to validate the UUID and secure the input
public class UserUUID {

        @NotEmpty
        @Size(min = 36, max = 36)
        private String uuid;

        public UserUUID() {
        }

        public UserUUID(String uuid) {
            this.uuid = uuid;
        }

        public UUID getUuid() {
            return UUID.fromString(uuid);
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

}

