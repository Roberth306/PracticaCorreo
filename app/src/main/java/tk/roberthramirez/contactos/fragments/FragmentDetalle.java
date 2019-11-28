package tk.roberthramirez.contactos.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import tk.roberthramirez.contactos.R;
import tk.roberthramirez.contactos.clases_correo.Correo;

public class FragmentDetalle extends Fragment {
    private Correo correo;
    private ImageView ivPerfilDetalle;
    private ImageView ivContactoDetalle;
    private TextView tvNombrePerfilDetalle;
    private TextView tvNombreContactoDetalle;
    private TextView tvApellidoPerfilDetalle;
    private TextView tvApellidoContactoDetalle;
    private TextView tvFechaDetalle;
    private TextView tvAsuntoDetalle;
    private TextView tvMensajeDetalle;

    private static SimpleDateFormat sdf;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.detalle_fragment, container, false);
        Bundle correoB = getArguments();
        correo = (Correo)correoB.getSerializable("objeto");

        ivContactoDetalle = layout.findViewById(R.id.ivContactoDetalle);
        tvNombreContactoDetalle = layout.findViewById(R.id.tvNombreContactoDetalle);
        tvApellidoContactoDetalle = layout.findViewById(R.id.tvApellidoContactoDetalle);

        if (correo.getContacto() == null) {
            ivContactoDetalle.setImageResource(R.drawable.unknown);
            tvNombreContactoDetalle.setText("Privado");
            tvApellidoContactoDetalle.setText("Privado");
        }else {
            String nameId = "c"+correo.getContacto().getId_foto();
            int id = getContext().getResources().getIdentifier(nameId,"drawable", getContext().getPackageName());
            ivContactoDetalle.setImageResource(id);
            tvNombreContactoDetalle.setText(correo.getContacto().getName());
            tvApellidoContactoDetalle.setText(correo.getContacto().getFirstSurname()+correo.getContacto().getSecondSurname());
        }


        ivPerfilDetalle = layout.findViewById(R.id.ivPerfilDetalle);
        ivPerfilDetalle.setImageResource(R.drawable.unknown);

        tvNombrePerfilDetalle = layout.findViewById(R.id.tvNombrePerfilDetalle);
        tvNombrePerfilDetalle.setText(correo.getCuenta().getName());

        tvApellidoPerfilDetalle = layout.findViewById(R.id.tvApellidoPerfilDetalle);
        tvApellidoPerfilDetalle.setText(correo.getCuenta().getFirstSurname());


        sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        tvFechaDetalle = layout.findViewById(R.id.tvFechaDetalle);
        tvFechaDetalle.setText(sdf.format(correo.getEnviadoEl().getTime()));

        tvAsuntoDetalle = layout.findViewById(R.id.tvAsuntoDetalle);
        tvAsuntoDetalle.setText(correo.getAsunto());

        tvMensajeDetalle = layout.findViewById(R.id.tvMensajeDetalle);
        tvMensajeDetalle.setText(correo.getMensaje());

        return layout;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void mostrarDetalle(Correo correo) {
        this.correo = correo;
    }
}
