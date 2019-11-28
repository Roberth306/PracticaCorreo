package tk.roberthramirez.contactos.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import tk.roberthramirez.contactos.R;

public class FragmentEnviar extends Fragment {
    private EditText etEmail;

    private Button bEnviar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.enviar_fragment, container, false);
        bEnviar = layout.findViewById(R.id.bEnviar);
        etEmail = layout.findViewById(R.id.etEmail);
        bEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etEmail.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "Indique un email", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getActivity(), "Mensaje enviado", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return layout;
    }
}
