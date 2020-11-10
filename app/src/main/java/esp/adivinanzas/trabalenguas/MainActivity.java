package esp.adivinanzas.trabalenguas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.flatdialoglibrary.dialog.FlatDialog;
import com.github.fernandodev.easyratingdialog.library.EasyRatingDialog;

public class MainActivity extends AppCompatActivity {

    LinearLayout btn_trabalenguas, btn_adivinanzas, btn_valoracion, btn_info;
    EasyRatingDialog easyRatingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        easyRatingDialog = new EasyRatingDialog(this);

        btn_trabalenguas = findViewById(R.id.btn_trabalenguas);
        btn_adivinanzas = findViewById(R.id.btn_adivinanzas);
        btn_valoracion = findViewById(R.id.btn_valoracion);
        btn_info = findViewById(R.id.btn_info);

        btn_trabalenguas.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), TrabalenguasActivity.class);
            view.getContext().startActivity(intent);
        });

        btn_adivinanzas.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), AdivinanzaActivity.class);
            view.getContext().startActivity(intent);
        });

        btn_valoracion.setOnClickListener(view -> {

            Integer icon_int = this.getResources().getIdentifier("ic_fav", "drawable", this.getPackageName());

            final FlatDialog flatDialog = new FlatDialog(MainActivity.this);
            flatDialog.setIcon(icon_int)
                    .setBackgroundColor(Color.WHITE)
                    .isCancelable(true)
                    .setSubtitle("\n¿Estás disfrutando de la app?\n\nValoranos en Google Play para poder seguir mejorando :)\n")
                    .setSubtitleColor(getResources().getColor(R.color.SecondaryBlue))
                    .setFirstButtonText("Valorar ahora")
                    .setFirstButtonColor(getResources().getColor(R.color.PrimaryBlue))
                    .withFirstButtonListner(v -> {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=ibai.llanos.sounds")));
                        flatDialog.dismiss();
                    })
                    .setSecondButtonText("Cancelar")
                    .withSecondButtonListner(v -> flatDialog.dismiss())
                    .show();


        });

        btn_info.setOnClickListener(view -> {

            Integer icon_int = this.getResources().getIdentifier("ic_info", "drawable", this.getPackageName());

            final FlatDialog flatDialog = new FlatDialog(MainActivity.this);
            flatDialog.setIcon(icon_int)
                    .setBackgroundColor(Color.WHITE)
                    .isCancelable(true)
                    .setSubtitle("\nGracias por usar la aplicación.\n\nPara cualquier sugerencia de mejora deja un comentario en Google Play o contacta con nosotros en xinfiniteapps@gmail.com\n\n\n")
                    .setSubtitleColor(getResources().getColor(R.color.SecondaryBlue))
                    .show();
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        easyRatingDialog.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        easyRatingDialog.showIfNeeded();
    }
}