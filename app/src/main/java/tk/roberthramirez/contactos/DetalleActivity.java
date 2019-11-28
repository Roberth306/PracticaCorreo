package tk.roberthramirez.contactos;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import tk.roberthramirez.contactos.clases_correo.Correo;
import tk.roberthramirez.contactos.fragments.FragmentDetalle;

public class DetalleActivity extends AppCompatActivity {
    private Correo correo;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_detalle);
        FragmentDetalle detalle = (FragmentDetalle)getSupportFragmentManager().findFragmentById(R.id.frgDetalle);
        //detalle.mostrarDetalle((Correo)getIntent().getSerializableExtra());
    }
}
