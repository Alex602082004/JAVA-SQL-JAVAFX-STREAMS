package Domain;

import java.io.IOException;
import java.util.Objects;

public class Tort extends ID {
    private String CakeType;

    // Constructorul implicit pentru JAXB
    public Tort() throws IOException {
        super();
        this.CakeType = "";
    }

    public Tort(int id, String cakeType) throws IOException {
        this.CakeType = cakeType;
        this.id = id;
    }

    public Tort(String CakeType) throws IOException {
        super();
        this.CakeType = CakeType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tort tort = (Tort) o;
        return id == tort.id && Objects.equals(CakeType, tort.getCakeType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, CakeType);
    }

    // Aplicați @XmlElement doar pe getter, nu și pe setter
    public String getCakeType() {
        return CakeType;
    }

    // Eliminați @XmlElement de pe setter
    public void setCakeType(String CakeType) {
        this.CakeType = CakeType;
    }

    @Override
    public String toString() {
        return "\nTort{" +
                "id=" + id +
                ", CakeType='" + CakeType + '\'' +
                '}';
    }
}
