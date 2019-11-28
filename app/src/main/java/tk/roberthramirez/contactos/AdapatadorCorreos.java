package tk.roberthramirez.contactos;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import tk.roberthramirez.contactos.clases_correo.Correo;

public class AdapatadorCorreos extends RecyclerView.Adapter<AdapatadorCorreos.CorreosViewHolder> implements View.OnClickListener{
    private Context context;
    private ArrayList<Correo> correos;
    private View.OnClickListener listener;

    public AdapatadorCorreos(Context context, ArrayList<Correo> correos) {
        this.context = context;
        this.correos = correos;
    }

    @NonNull
    @Override
    public CorreosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.correo_interfaz,parent,false);
        itemView.setOnClickListener(this);
        return new CorreosViewHolder(itemView, context);
    }

    @Override
    public void onBindViewHolder(@NonNull CorreosViewHolder holder, int position) {
        Correo correo = correos.get(position);
        holder.bindCorreo(correo);
    }

    @Override
    public int getItemCount() {
        return correos.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if(listener != null) {
            listener.onClick(v);
        }
    }

    public class CorreosViewHolder extends RecyclerView.ViewHolder{
        private ImageView ivImagen;
        private TextView tvNombre;
        private TextView tvAsunto;
        private TextView tvMensaje;
        private TextView tvFecha;
        private Context context;

        public CorreosViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            this.context= context;
            ivImagen = itemView.findViewById(R.id.ivImagen);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvAsunto = itemView.findViewById(R.id.tvAsunto);
            tvMensaje = itemView.findViewById(R.id.tvMensaje);
            tvFecha = itemView.findViewById(R.id.tvFecha);
        }

        public void bindCorreo(Correo correo) {

            //Comprobamos si el correo recibido o enviado tiene un contacto asociado
            if(correo.getContacto()==null){
                //TODO redondear una imagen
                //extraemos el drawable en un bitmap
                Drawable originalDrawable = context.getResources().getDrawable(R.drawable.unknown);
                Bitmap originalBitmap = ((BitmapDrawable) originalDrawable).getBitmap();
                //creamos el drawable redondeado
                RoundedBitmapDrawable roundedDrawable =
                        RoundedBitmapDrawableFactory.create(context.getResources(), originalBitmap);
                //asignamos el CornerRadius
                roundedDrawable.setCornerRadius(originalBitmap.getHeight());
                ivImagen.setImageDrawable(roundedDrawable);
                //Fin del redondeo

                tvNombre.setText(correo.getEmisor());


            }else {
                String profileNumber = "c"+correo.getContacto().getId_foto();
                int id = context.getResources().getIdentifier(profileNumber,"drawable", context.getPackageName());

                Drawable originalDrawable = context.getResources().getDrawable(id);
                Bitmap originalBitmap = ((BitmapDrawable) originalDrawable).getBitmap();
                //creamos el drawable redondeado
                RoundedBitmapDrawable roundedDrawable =
                        RoundedBitmapDrawableFactory.create(context.getResources(), originalBitmap);
                //asignamos el CornerRadius
                roundedDrawable.setCornerRadius(originalBitmap.getHeight());
                ivImagen.setImageDrawable(roundedDrawable);
                //ivImagen.setImageResource(id);
                tvNombre.setText(correo.getContacto().getName()+correo.getContacto().getFirstSurname()+correo.getContacto().getSecondSurname());
            }
            tvAsunto.setText(correo.getAsunto());
            tvMensaje.setText(correo.getMensaje());
            tvFecha.setText(correo.fechaToString());

            //En caso de que no lo haya leido parte del mensaje estara en otro estilo
            if(!correo.isReaded()){
                tvNombre.setTypeface(null, Typeface.BOLD);
                tvFecha.setTypeface(null, Typeface.BOLD);
                tvFecha.setTextColor(Color.CYAN);
            }
        }
    }
}
