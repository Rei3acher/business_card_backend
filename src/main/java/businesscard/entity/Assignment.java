package businesscard.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


import java.util.UUID;

@Entity
@Table(name = "assignment")
public class Assignment extends PanacheEntityBase {

    public Assignment(UUID uuid, String accountName) {
        this.uuid = uuid;
        this.accountName = accountName;
    }

    public Assignment() {

    }

    @Id
    public UUID uuid;

    @Column(name = "account_name", nullable = false)
    public String accountName;


    public static Assignment getAssigmentByUuid(UUID uuid) {
        return find("uuid", uuid).firstResult();
    }

    public Assignment getAssigmentByAccountName(String AccountName) {
        return find("account_name", AccountName).firstResult();
    }

    public boolean isPersistentByName(String accountName) {
        return getAssigmentByAccountName(accountName).isPersistent();
    }

    public String getAccountName() {
        return accountName;
    }

}


