package tk.roberthramirez.contactos.clases_correo;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class Correo implements Serializable {
    private String emisor;
    private String receptor;
    private String asunto;
    private String mensaje;
    private Contacto contacto;
    private Cuenta cuenta;
    //private String enviadoEl;

    //todo Hacer gregorian calendar en cambio de String
    private GregorianCalendar enviadoEl;
    private static SimpleDateFormat sdf;

    private boolean isReaded;
    private boolean isDeleted;
    private boolean isSpam;

    public Correo(String emisor, String receptor, String asunto, String mensaje, String enviadoEl, boolean isReaded, boolean isDeleted, boolean isSpam, Contacto contacto, Cuenta cuenta) {
        this.emisor = emisor;
        this.receptor = receptor;
        this.asunto = asunto;
        this.mensaje = mensaje;


        this.enviadoEl = new GregorianCalendar();
        try{
            sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
            this.enviadoEl.setTime(sdf.parse(enviadoEl));
        }catch (ParseException pe){
            System.out.println("Error al parsear la fecha enviado de "+emisor);
            pe.printStackTrace();
        }
        this.isReaded = isReaded;
        this.isDeleted = isDeleted;
        this.isSpam = isSpam;
        this.contacto = contacto;
        this.cuenta = cuenta;
    }

    public String getEmisor() {
        return emisor;
    }

    public String getReceptor() {
        return receptor;
    }

    public String getAsunto() {
        return asunto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public GregorianCalendar getEnviadoEl() {
        return enviadoEl;
    }

    public boolean isReaded() {
        return isReaded;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public boolean isSpam() {
        return isSpam;
    }

    public Contacto getContacto() {
        return contacto;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public String fechaToString() {
        sdf = new SimpleDateFormat("dd MMM hh:mm");
        return sdf.format(this.enviadoEl.getTime());
    }
}
