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

import esp.adivinanzas.trabalenguas.db.TrabalenguasDB;

public class TrabalenguasActivity extends AppCompatActivity {

    private TrabalenguasDB sqlite;
    LinearLayout btn_tl_cortos, btn_tl_ingles, btn_tl_dificiles, btn_tl_custom, btn_add;

    private FrameLayout adContainerView;
    private AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trabalenguas);

        // Initialize the Mobile Ads SDK.
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) { }
        });

        adContainerView = findViewById(R.id.ad_view_container);
        // Step 1 - Create an AdView and set the ad unit ID on it.
        adView = new AdView(this);
        adView.setAdUnitId(getString(R.string.trabalenguas_menu_Banner_id));
        adContainerView.addView(adView);
        loadBanner();

        sqlite = new TrabalenguasDB(this);

        btn_tl_cortos = findViewById(R.id.btn_tl_cortos);
        btn_tl_ingles = findViewById(R.id.btn_tl_ingles);
        btn_tl_dificiles = findViewById(R.id.btn_tl_dificiles);
        btn_tl_custom = findViewById(R.id.btn_tl_custom);
        btn_add = findViewById(R.id.add_tb);

        btn_tl_cortos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String extra_section = "Corto";
                Intent intent = new Intent(view.getContext(), TrabalenguasViewPager.class);
                intent.putExtra("SECTION", extra_section);
                view.getContext().startActivity(intent);
            }
        });

        btn_tl_ingles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String extra_section = "Ingles";
                Intent intent = new Intent(view.getContext(), TrabalenguasViewPager.class);
                intent.putExtra("SECTION", extra_section);
                view.getContext().startActivity(intent);
            }
        });

        btn_tl_dificiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String extra_section = "Dificil";
                Intent intent = new Intent(view.getContext(), TrabalenguasViewPager.class);
                intent.putExtra("SECTION", extra_section);
                view.getContext().startActivity(intent);
            }
        });

        btn_tl_custom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String extra_section = "Custom";
                Intent intent = new Intent(view.getContext(), TrabalenguasViewPager.class);
                intent.putExtra("SECTION", extra_section);
                view.getContext().startActivity(intent);
            }
        });
    }

    public void addTrabalenguas(View view) {
        showDialogInput();
    }

    private void showDialogInput() {

//        final EditText edtText = new EditText(this);
//        edtText.setHint("Escribe aquí tu trabalenguas...");
//        edtText.setInputType(InputType.TYPE_CLASS_TEXT);
//        edtText.setPadding(70, 60, 32, 32);
//        edtText.setBackgroundResource(android.R.color.transparent);
//
//        LinearLayout view = new LinearLayout(this);
//        view.setOrientation(LinearLayout.VERTICAL);
//        view.addView(edtText);
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(this)
//                .setTitle("Nuevo trabalenguas")
//                .setView(view)
//                .setPositiveButton("Añadir", (dialog, which) -> {
//
//                    // if no product name or product price, just do nothing
//                    if (edtText.getText().toString().trim().isEmpty()){
//                        Toast.makeText(TrabalenguasActivity.this, "No se ha añadido", Toast.LENGTH_SHORT).show();
//                        return;
//                    }
//
//
//                    // create product entity to save in database
//                    Trabalenguas trabalenguas = new Trabalenguas();
//                    trabalenguas.setSection("Custom");
//                    trabalenguas.setText(edtText.getText().toString());
//
//                    // save in SQLite
//                    sqlite.insert(trabalenguas);
//
//                    // Show a success message
//                    Toast.makeText(TrabalenguasActivity.this, "Añadido correctamente", Toast.LENGTH_SHORT).show();
//                });
//
//        builder.create()
//                .show();

        final FlatDialog flatDialog = new FlatDialog(TrabalenguasActivity.this);
        flatDialog.setTitle("Nuevo trabalenguas")
                .setTitleColor(getResources().getColor(R.color.SecondaryBlue))
                .isCancelable(true)
                .setFirstTextFieldHint("Escribe aquí tu trabalenguas...")
                .setFirstButtonText("Añadir")
                .setSecondButtonText("Cancelar")
                .setBackgroundColor(Color.WHITE)
                .setFirstTextFieldTextColor(getResources().getColor(R.color.SecondaryBlue))
                .setFirstTextFieldHintColor(getResources().getColor(R.color.SecondaryBlue))
                .setFirstTextFieldBorderColor(getResources().getColor(R.color.SecondaryBlue))
                .setFirstButtonColor(getResources().getColor(R.color.PrimaryBlue))
                .withFirstButtonListner(view -> {

                    // if no product name or product price, just do nothing
                    if (flatDialog.getFirstTextField().isEmpty()) {
                        Toast.makeText(TrabalenguasActivity.this, "No se ha añadido", Toast.LENGTH_SHORT).show();
                        flatDialog.dismiss();
                    }

                    // create product entity to save in database
                    Trabalenguas trabalenguas = new Trabalenguas();
                    trabalenguas.setSection("Custom");
                    trabalenguas.setText(flatDialog.getFirstTextField());

                    // save in SQLite
                    sqlite.insert(trabalenguas);

                    // Show a success message
                    Toast.makeText(TrabalenguasActivity.this, "Añadido correctamente", Toast.LENGTH_SHORT).show();
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