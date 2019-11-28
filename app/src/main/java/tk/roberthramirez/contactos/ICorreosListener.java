package tk.roberthramirez.contactos;

import tk.roberthramirez.contactos.clases_correo.Correo;

public interface ICorreosListener {
    void onCorreoSeleccionado(Correo c);
}
