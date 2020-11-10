package esp.adivinanzas.trabalenguas.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.github.zagum.switchicon.SwitchIconView;

import java.util.List;

import esp.adivinanzas.trabalenguas.Adivinanza;
import esp.adivinanzas.trabalenguas.R;

public class AdivinanzaAdapter extends PagerAdapter {
    private List<Adivinanza> adivinanzas;
    private LayoutInflater layoutInflater;
    private Context context;

    public AdivinanzaAdapter(List<Adivinanza> adivinanzas, Context context) {
        this.adivinanzas = adivinanzas;
        this.context = context;
    }

    @Override
    public int getCount() {
        return adivinanzas.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_adivinanza, container, false);

        // Adivinanza
        TextView textView_adivinanza = view.findViewById(R.id.tv_adivinanza);
        textView_adivinanza.setText(adivinanzas.get(position).getAdivinanza());

        // Solucion
        TextView textView_solucion = view.findViewById(R.id.tv_solucion);
        textView_solucion.setText(adivinanzas.get(position).getSolucion());

        // Toggle visibilidad
        SwitchIconView switchIconVer = view.findViewById(R.id.switchIconVer);
        LinearLayout buttonVer = view.findViewById(R.id.buttonVer);
        buttonVer.setOnClickListener(v -> {
            switchIconVer.switchState();
            if (textView_solucion.getVisibility() == View.VISIBLE) {
                textView_solucion.setVisibility(View.INVISIBLE);
            } else {
                textView_solucion.setVisibility(View.VISIBLE);
            }
        });

        // Aumentar tamaño en apaisado
        if (context.getResources().getConfiguration().orientation == 2) {
            textView_adivinanza.setTextSize(30);
            textView_solucion.setTextSize(50);
        }

        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
