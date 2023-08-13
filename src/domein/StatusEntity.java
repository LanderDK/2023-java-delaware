package domein;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class StatusEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idstatus;

    private String statusnaam;

    protected StatusEntity() {
    }

    public StatusEntity(String status) {
        this.statusnaam = status;
    }

    // Getters and setters
    public Long getId() {
        return idstatus;
    }

    public void setId(Long id) {
        this.idstatus = id;
    }

    public String getStatus() {
        return statusnaam;
    }

    public void setStatus(String status) {
        this.statusnaam = status;
    }
}
