package tk.roberthramirez.contactos.clases_correo;

import java.io.Serializable;

public class Cuenta implements Serializable {
    private int id;
    private String name;
    private String firstSurname;
    private String email;

    public Cuenta(int id, String name, String firstSurname, String email) {
        this.id = id;
        this.name = name;
        this.firstSurname = firstSurname;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFirstSurname() {
        return firstSurname;
    }

    public String getEmail() {
        return email;
    }
}
