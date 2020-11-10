package esp.adivinanzas.trabalenguas.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

import esp.adivinanzas.trabalenguas.R;
import esp.adivinanzas.trabalenguas.Trabalenguas;

public class TrabalenguasAdapter extends PagerAdapter {

    private List<Trabalenguas> trabalenguas;
    private LayoutInflater layoutInflater;
    private Context context;

    public TrabalenguasAdapter(List<Trabalenguas> trabalenguas, Context context) {
        this.trabalenguas = trabalenguas;
        this.context = context;
    }

    @Override
    public int getCount() {
        return trabalenguas.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_trabalenguas, container, false);

        TextView textView = view.findViewById(R.id.tv_trabalenguas);
        textView.setText(trabalenguas.get(position).getText());

        // change text size depending on screen size and/or resolution
        // textView.setTextSize(10 * context.getResources().getDisplayMetrics().density);

        if (context.getResources().getConfiguration().orientation == 2) {
            textView.setTextSize(30);
        }

        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
