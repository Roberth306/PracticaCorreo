package tk.roberthramirez.contactos.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import tk.roberthramirez.contactos.AdapatadorCorreos;
import tk.roberthramirez.contactos.ICorreosListener;
import tk.roberthramirez.contactos.ParserJson;
import tk.roberthramirez.contactos.R;
import tk.roberthramirez.contactos.clases_correo.Correo;

public class FragmentRecibidos extends Fragment {
    private ArrayList<Correo> correos;
    private RecyclerView rvListado;
    Activity activity;
    private ICorreosListener listener;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ParserJson parserJson = new ParserJson(getActivity());
        if(parserJson.parse()) {
            ArrayList<Correo> filtrador = new ArrayList<>();
            for(Correo a:parserJson.getCorreos()) {
                if (!a.isSpam() && !a.getEmisor().equals(a.getCuenta().getEmail()) && !a.isDeleted()) {
                    filtrador.add(a);
                }
            }
            correos = new ArrayList<>();

            for(Correo c : filtrador) {
                correos.add(c);
            }
        }

        return inflater.inflate(R.layout.correo_fragment,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rvListado = getView().findViewById(R.id.rvListado);
        AdapatadorCorreos adaptador = new AdapatadorCorreos(getActivity(), correos);

        adaptador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onCorreoSeleccionado(correos.get(rvListado.getChildAdapterPosition(v)));
            }
        });

        rvListado.setAdapter(adaptador);
        rvListado.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            this.activity =(Activity) context;
            listener = (ICorreosListener)this.activity;
        }
    }
}
