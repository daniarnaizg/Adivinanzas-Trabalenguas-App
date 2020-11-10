package esp.adivinanzas.trabalenguas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.flatdialoglibrary.dialog.FlatDialog;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import esp.adivinanzas.trabalenguas.db.AdivinanzasDB;

public class AdivinanzaActivity extends AppCompatActivity {

    private AdivinanzasDB sqlite;
    LinearLayout btn_av_animales, btn_av_profesiones, btn_av_frutas, btn_av_transporte, btn_av_numeros, btn_av_dificil, btn_av_ingles, btn_av_custom, btn_add;

    private FrameLayout adContainerView;
    private AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adivinanza);

        // Initialize the Mobile Ads SDK.
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) { }
        });

        adContainerView = findViewById(R.id.ad_view_container);
        // Step 1 - Create an AdView and set the ad unit ID on it.
        adView = new AdView(this);
        adView.setAdUnitId(getString(R.string.adivinanzas_menu_Banner_id));
        adContainerView.addView(adView);
        loadBanner();

        sqlite = new AdivinanzasDB(this);

        btn_av_animales = findViewById(R.id.btn_av_animales);
        btn_av_profesiones = findViewById(R.id.btn_av_profesiones);
        btn_av_frutas = findViewById(R.id.btn_av_frutas);
        btn_av_transporte = findViewById(R.id.btn_av_transporte);
        btn_av_numeros = findViewById(R.id.btn_av_numeros);
        btn_av_dificil = findViewById(R.id.btn_av_dificil);
        btn_av_ingles = findViewById(R.id.btn_av_ingles);
        btn_av_custom = findViewById(R.id.btn_av_custom);
        btn_add = findViewById(R.id.add_av);

        btn_av_animales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String extra_section = "Animales";
                Intent intent = new Intent(view.getContext(), AdivinanzasViewPager.class);
                intent.putExtra("SECTION", extra_section);
                view.getContext().startActivity(intent);
            }
        });

        btn_av_profesiones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String extra_section = "Profesiones";
                Intent intent = new Intent(view.getContext(), AdivinanzasViewPager.class);
                intent.putExtra("SECTION", extra_section);
                view.getContext().startActivity(intent);
            }
        });

        btn_av_frutas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String extra_section = "Frutas";
                Intent intent = new Intent(view.getContext(), AdivinanzasViewPager.class);
                intent.putExtra("SECTION", extra_section);
                view.getContext().startActivity(intent);
            }
        });

        btn_av_transporte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String extra_section = "Transportes";
                Intent intent = new Intent(view.getContext(), AdivinanzasViewPager.class);
                intent.putExtra("SECTION", extra_section);
                view.getContext().startActivity(intent);
            }
        });

        btn_av_numeros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String extra_section = "Numeros";
                Intent intent = new Intent(view.getContext(), AdivinanzasViewPager.class);
                intent.putExtra("SECTION", extra_section);
                view.getContext().startActivity(intent);
            }
        });

        btn_av_dificil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String extra_section = "Dificil";
                Intent intent = new Intent(view.getContext(), AdivinanzasViewPager.class);
                intent.putExtra("SECTION", extra_section);
                view.getContext().startActivity(intent);
            }
        });

        btn_av_ingles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String extra_section = "Ingles";
                Intent intent = new Intent(view.getContext(), AdivinanzasViewPager.class);
                intent.putExtra("SECTION", extra_section);
                view.getContext().startActivity(intent);
            }
        });

        btn_av_custom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String extra_section = "Custom";
                Intent intent = new Intent(view.getContext(), AdivinanzasViewPager.class);
                intent.putExtra("SECTION", extra_section);
                view.getContext().startActivity(intent);
            }
        });
    }

    public void addAdivinanza(View view) {
        showDialogInput();
    }

    private void showDialogInput() {

//        final EditText edtText = new EditText(this);
//        edtText.setHint("Escribe aquí tu adivinanza...");
//        edtText.setInputType(InputType.TYPE_CLASS_TEXT);
//        edtText.setPadding(70, 60, 32, 32);
//        edtText.setBackgroundResource(android.R.color.transparent);
//
//        final EditText edtSol = new EditText(this);
//        edtSol.setHint("Escribe aquí tu solución...");
//        edtSol.setInputType(InputType.TYPE_CLASS_TEXT);
//        edtSol.setPadding(70, 60, 32, 32);
//        edtSol.setBackgroundResource(android.R.color.transparent);
//
//        LinearLayout view = new LinearLayout(this);
//        view.setOrientation(LinearLayout.VERTICAL);
//        view.addView(edtText);
//        view.addView(edtSol);
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(this)
//                .setTitle("Nueva adivinanza")
//                .setView(view)
//                .setPositiveButton("Añadir", (dialog, which) -> {
//
//                    // if no product name or product price, just do nothing
//                    if (edtText.getText().toString().trim().isEmpty() || edtSol.getText().toString().trim().isEmpty()) {
//                        Toast.makeText(AdivinanzaActivity.this, "No se ha añadido", Toast.LENGTH_SHORT).show();
//                        return;
//                    }
//
//                    // create product entity to save in database
//                    Adivinanza adivinanza = new Adivinanza();
//                    adivinanza.setSection("Custom");
//                    adivinanza.setAdivinanza(edtText.getText().toString());
//                    adivinanza.setSolucion(edtSol.getText().toString());
//
//                    // save in SQLite
//                    sqlite.insert(adivinanza);
//
//                    // Show a success message
//                    Toast.makeText(AdivinanzaActivity.this, "Añadido correctamente", Toast.LENGTH_SHORT).show();
//                });
//
//        builder.create()
//                .show();

        final FlatDialog flatDialog = new FlatDialog(AdivinanzaActivity.this);
        flatDialog.setTitle("Nueva adivinanza")
                .setTitleColor(getResources().getColor(R.color.SecondaryBlue))
                .isCancelable(true)
                .setFirstTextFieldHint("Escribe aquí la adivinanza...")
                .setSecondTextFieldHint("Escribe aquí la solución...")
                .setFirstButtonText("Añadir")
                .setSecondButtonText("Cancelar")
                .setBackgroundColor(Color.WHITE)
                .setFirstTextFieldTextColor(getResources().getColor(R.color.SecondaryBlue))
                .setSecondTextFieldTextColor(getResources().getColor(R.color.SecondaryBlue))
                .setFirstTextFieldHintColor(getResources().getColor(R.color.SecondaryBlue))
                .setSecondTextFieldHintColor(getResources().getColor(R.color.SecondaryBlue))
                .setFirstTextFieldBorderColor(getResources().getColor(R.color.SecondaryBlue))
                .setSecondTextFieldBorderColor(getResources().getColor(R.color.SecondaryBlue))
                .setFirstButtonColor(getResources().getColor(R.color.PrimaryBlue))
                .withFirstButtonListner(view -> {

                    // if no product name or product price, just do nothing
                    if (flatDialog.getFirstTextField().isEmpty() || flatDialog.getSecondTextField().isEmpty()) {
                        Toast.makeText(AdivinanzaActivity.this, "No se ha añadido", Toast.LENGTH_SHORT).show();
                        flatDialog.dismiss();
                    }

                    // create product entity to save in database
                    Adivinanza adivinanza = new Adivinanza();
                    adivinanza.setSection("Custom");
                    adivinanza.setAdivinanza(flatDialog.getFirstTextField());
                    adivinanza.setSolucion(flatDialog.getSecondTextField());

                    // save in SQLite
                    sqlite.insert(adivinanza);

                    // Show a success message
                    Toast.makeText(AdivinanzaActivity.this, "Añadido correctamente", Toast.LENGTH_SHORT).show();
                    flatDialog.dismiss();
                })
                .withSecondButtonListner(view -> flatDialog.dismiss())
                .show();
    }

    boolean isTestDevice() {
        return Boolean.parseBoolean(Settings.System.getString(getContentResolver(), "firebase.test.lab"));
    }

    private void loadBanner() {
        // Create an ad request. Check your logcat output for the hashed device ID
        // to get test ads on a physical device, e.g.,
        // "Use AdRequest.Builder.addTestDevice("ABCDE0123") to get test ads on this
        // device."
        AdRequest adRequest =
                new AdRequest.Builder().build();
//        AdRequest adRequest =
//                new AdRequest.Builder().build();

        AdSize adSize = getAdSize();
        // Step 4 - Set the adaptive ad size on the ad view.
        adView.setAdSize(adSize);

        // Step 5 - Start loading the ad in the background.
//        if(!isTestDevice())
        adView.loadAd(adRequest);
    }

    private AdSize getAdSize() {
        // Step 2 - Determine the screen width (less decorations) to use for the ad width.
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        float widthPixels = outMetrics.widthPixels;
        float density = outMetrics.density;

        int adWidth = (int) (widthPixels / density);

        // Step 3 - Get adaptive ad size and return for setting on the ad view.
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(this, adWidth);
    }
}