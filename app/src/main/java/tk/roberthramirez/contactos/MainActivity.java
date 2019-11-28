package tk.roberthramirez.contactos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import tk.roberthramirez.contactos.clases_correo.Correo;
import tk.roberthramirez.contactos.fragments.FragmentDetalle;
import tk.roberthramirez.contactos.fragments.FragmentEnviados;
import tk.roberthramirez.contactos.fragments.FragmentEnviar;
import tk.roberthramirez.contactos.fragments.FragmentNoLeido;
import tk.roberthramirez.contactos.fragments.FragmentPapelera;
import tk.roberthramirez.contactos.fragments.FragmentRecibidos;
import tk.roberthramirez.contactos.fragments.FragmentSpam;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, ICorreosListener {
    private FloatingActionButton bFloating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layaout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawner_open, R.string.navigation_drawber_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        bFloating = findViewById(R.id.bFloating);
        bFloating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentEnviar frag = new FragmentEnviar();
                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, frag).commit();
                setTitle("Enviados");
            }
        });
    }

        public void onBackPressed () {
            DrawerLayout drawer = findViewById(R.id.drawer_layaout);
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
        }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment f;
        int id = item.getItemId();

        if (id == R.id.miRecibidos) {
            f = new FragmentRecibidos();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, f).commit();

            setTitle("Recibidos");
        } else if (id == R.id.miEnviados) {
            f = new FragmentEnviados();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, f).commit();
            setTitle("Enviados");

        } else if (id == R.id.miNoLeido) {
            f = new FragmentNoLeido();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, f).commit();
            setTitle("No Leido");
        } else if (id == R.id.miEnviar) {
            f = new FragmentEnviar();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, f).commit();
            setTitle("Herramientas");
        } else if (id == R.id.miSpam) {
            f = new FragmentSpam();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, f).commit();
            setTitle("Spam");
        } else if (id == R.id.miPapelera) {
            f = new FragmentPapelera();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, f).commit();
            setTitle("Papelera");
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layaout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onCorreoSeleccionado(Correo c) {
        FragmentDetalle prueba = new FragmentDetalle();

            Bundle args = new Bundle();
            args.putSerializable("objeto", c);
            prueba.setArguments(args);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, prueba).commit();

    }
}