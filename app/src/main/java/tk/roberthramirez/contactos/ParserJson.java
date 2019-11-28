package tk.roberthramirez.contactos;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStream;

import tk.roberthramirez.contactos.clases_correo.Contacto;
import tk.roberthramirez.contactos.clases_correo.Correo;
import tk.roberthramirez.contactos.clases_correo.Cuenta;

public class ParserJson {
    //private Cuenta cuenta;
    private Correo[] correos;
    //private Contacto[] contactos;

    private InputStream correosFile;

    public ParserJson(Context c) {
        this.correosFile = c.getResources().openRawResource(R.raw.correos);
    }

    public boolean parse() {
        boolean parsed = false;
        //cuenta = null;
        correos = null;
        //contactos = null;

        //
        int bytesArchivo;
        byte[] bytes = null;
        String json;
        try{
            bytesArchivo = correosFile.available();
            bytes = new byte[bytesArchivo];
            correosFile.read(bytes);
            json = new String(bytes, "UTF-8");

            JSONTokener tokener = new JSONTokener(json);
            JSONArray padre = new JSONArray(tokener);

            for (int i=0;i<padre.length();i++){
                JSONObject jsonObject = padre.getJSONObject(i);

                //Creamos la cuenta, despues la añadiremos a las creaciones de correo
                JSONObject jCuenta = jsonObject.getJSONObject("myAccount");
                Cuenta cuenta = new Cuenta(jCuenta.getInt("id"),jCuenta.getString("name"), jCuenta.getString("firstSurname"),jCuenta.getString("email"));

                //Creamos array objetos contactos
                JSONArray arrayContactos = jsonObject.getJSONArray("contacts");

                //Version mala
                /*contactos = new Contacto[arrayContactos.length()];
                for(int j=0;j<arrayContactos.length();j++) {
                    JSONObject objectContacto = arrayContactos.getJSONObject(i);
                    contactos[j] = new Contacto(objectContacto.getInt("id"), objectContacto.getString("name"), objectContacto.getString("firstSurname"), objectContacto.getString("secondSurname"), objectContacto.getString("birth"), objectContacto.getInt("foto"), objectContacto.getString("email"),objectContacto.getString("phone1"), objectContacto.getString("phone2"), objectContacto.getString("address"));
                }*/

                //Creamos los Correos enviados y recibidos
                JSONArray arrayCorreos = jsonObject.getJSONArray("mails");
                correos = new Correo[arrayCorreos.length()];
                //Declaramos el siguiente string para saber si en el correo tenemos que buscar el contacto
                //emisor o receptor
                String focusContacto;

                for(int j=0; j<arrayCorreos.length();j++) {
                    JSONObject objectCorreo = arrayCorreos.getJSONObject(j);
                    if(objectCorreo.getString("from").equals(jCuenta.getString("email"))){
                        focusContacto="to";
                    }else{
                        focusContacto="from";
                    }
                    Contacto contacto = null;
                    for(int k=0;k<arrayContactos.length();k++) {
                        JSONObject objectContacto = arrayContactos.getJSONObject(k);

                        if (objectCorreo.getString(focusContacto).equals(objectContacto.getString("email"))) {
                            contacto = new Contacto(objectContacto.getInt("id"), objectContacto.getString("name"), objectContacto.getString("firstSurname"), objectContacto.getString("secondSurname"), objectContacto.getString("birth"), objectContacto.getInt("foto"), objectContacto.getString("email"), objectContacto.getString("phone1"), objectContacto.getString("phone2"), objectContacto.getString("address"));
                        }
                    }

                    correos[j] = new Correo(objectCorreo.getString("from"), objectCorreo.getString("to"), objectCorreo.getString("subject"), objectCorreo.getString("body"), objectCorreo.getString("sentOn"), objectCorreo.getBoolean("readed"), objectCorreo.getBoolean("deleted"), objectCorreo.getBoolean("spam"), contacto, cuenta);
                }
            }
            parsed = true;
        }catch (IOException ioe) {
            System.out.println("Error en I/O");
            ioe.printStackTrace();
        }catch (JSONException jsonError) {
            System.out.println("Error con .io.JSON");
            jsonError.printStackTrace();
        }
        return parsed;
    }

    public Correo[] getCorreos() {
        return this.correos;
    }

}
