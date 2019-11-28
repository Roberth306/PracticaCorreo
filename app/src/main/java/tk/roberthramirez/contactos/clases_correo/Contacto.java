package tk.roberthramirez.contactos.clases_correo;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.SimpleTimeZone;

public class Contacto implements Serializable {
    private int id;
    private String name;
    private String firstSurname;
    private String secondSurname;
    private GregorianCalendar birth;
    private int id_foto;
    private String email;
    private String phone1;
    private String phone2;
    private String address;

    static private SimpleDateFormat sdf;

    public Contacto(int id, String name, String firstSurname, String secondSurname, String birth, int id_foto, String email, String phone1, String phone2, String address) {
        this.id = id;
        this.name = name;
        this.firstSurname = firstSurname;
        this.secondSurname = secondSurname;

        this.birth = new GregorianCalendar();
        try{
            sdf = new SimpleDateFormat("yyyy-MM-dd");
            this.birth.setTime(sdf.parse(birth));
        }catch (ParseException pe){
            System.out.println("Error al parsear la fecha enviado de ");
            pe.printStackTrace();
        }


        this.id_foto = id_foto;
        this.email = email;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.address = address;
    }

    public Contacto() {
    }

    private void parsearFecha(String fecha) {
        try{
            this.birth.setTime(sdf.parse(fecha));
        }catch (ParseException pe){
            System.out.println("Error al instanciar cumpleaños(Birth Contacto)");
        }
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

    public String getSecondSurname() {
        return secondSurname;
    }

    public GregorianCalendar getBirth() {
        return birth;
    }

    public int getId_foto() {
        return id_foto;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone1() {
        return phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public String getAddress() {
        return address;
    }
}
