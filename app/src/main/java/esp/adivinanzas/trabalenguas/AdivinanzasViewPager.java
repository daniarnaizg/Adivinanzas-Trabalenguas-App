package esp.adivinanzas.trabalenguas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.animation.ArgbEvaluator;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.flatdialoglibrary.dialog.FlatDialog;
import com.github.zagum.switchicon.SwitchIconView;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.List;

import esp.adivinanzas.trabalenguas.adapter.AdivinanzaAdapter;
import esp.adivinanzas.trabalenguas.db.AdivinanzasDB;

public class AdivinanzasViewPager extends AppCompatActivity {

    private AdivinanzasDB sqlite;
    TextView emptyText;
    SwitchIconView btn_del;
    ViewPager viewPager;
    String extra_section;
    AdivinanzaAdapter adapter;
    List<Adivinanza> lista_adivinanzas;
    Integer[] colors;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    String currentAdivinanza;
    String currentSolucion;

    private FrameLayout adContainerView;
    private AdView adView;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adivinanzas_view_pager);

        // Initialize the Mobile Ads SDK.
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        adContainerView = findViewById(R.id.ad_view_container);
        // Step 1 - Create an AdView and set the ad unit ID on it.
        adView = new AdView(this);
        adView.setAdUnitId(getString(R.string.adivinanzas_menu_Banner_id));
        adContainerView.addView(adView);
        if (this.getResources().getConfiguration().orientation == 1) loadBanner();


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            extra_section = extras.getString("SECTION");
        }

        sqlite = new AdivinanzasDB(this);
        lista_adivinanzas = sqlite.get(extra_section);

        btn_del = findViewById(R.id.btnDel);
        emptyText = findViewById(R.id.EmptyText);

        if (lista_adivinanzas.size() == 0) {
            emptyText.setVisibility(View.VISIBLE);
            btn_del.setVisibility(View.INVISIBLE);
        }

        if (!extra_section.equals("Custom")) {
            btn_del.setVisibility(View.INVISIBLE);
        }

        adapter = new AdivinanzaAdapter(lista_adivinanzas, this);

        viewPager = findViewById(R.id.AdivinanzasviewPager);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(50, 0, 50, 0);

        colors = new Integer[]{
                getResources().getColor(R.color.color1),
                getResources().getColor(R.color.color2),
                getResources().getColor(R.color.color3),
                getResources().getColor(R.color.color4),
                getResources().getColor(R.color.color5),
                getResources().getColor(R.color.color6),
                getResources().getColor(R.color.color7),
                getResources().getColor(R.color.color8),
                getResources().getColor(R.color.color9),
                getResources().getColor(R.color.color10),
                getResources().getColor(R.color.color11),
                getResources().getColor(R.color.color12),
                getResources().getColor(R.color.color13),
                getResources().getColor(R.color.color14),
                getResources().getColor(R.color.color15),
                getResources().getColor(R.color.color16),
                getResources().getColor(R.color.color17),
                getResources().getColor(R.color.color18),
                getResources().getColor(R.color.color19),
                getResources().getColor(R.color.color20),
                getResources().getColor(R.color.color21),
                getResources().getColor(R.color.color22),
                getResources().getColor(R.color.color22),
                getResources().getColor(R.color.color23),
                getResources().getColor(R.color.color24),
                getResources().getColor(R.color.color25),
                getResources().getColor(R.color.color26),
                getResources().getColor(R.color.color27),
                getResources().getColor(R.color.color28),
                getResources().getColor(R.color.color29),
                getResources().getColor(R.color.color30),
                getResources().getColor(R.color.color31),
                getResources().getColor(R.color.color32)
        };

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                currentAdivinanza = lista_adivinanzas.get(position).getAdivinanza();
                currentSolucion = lista_adivinanzas.get(position).getSolucion();

                if (position < (adapter.getCount() - 1) && position < (colors.length - 1)) {
                    viewPager.setBackgroundColor(

                            (Integer) argbEvaluator.evaluate(
                                    positionOffset,
                                    colors[position],
                                    colors[position + 1]
                            )
                    );
                } else {
                    viewPager.setBackgroundColor(

                            (Integer) argbEvaluator.evaluate(
                                    positionOffset,
                                    colors[(int) position / 2],
                                    colors[(int) position / 2 + 1]
                            )
                    );
                }

                // Si hay mas de 32 tarjetitas va a petar...
//                viewPager.setBackgroundColor(
//                        (Integer) argbEvaluator.evaluate(
//                                positionOffset,
//                                colors[position],
//                                colors[position + 1]
//                        )
//                );
            }

            @Override
            public void onPageSelected(int position) {
                // pass
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                // pass
            }
        });
    }

    public void safeDelete(View view) {
        showDialogInput();
    }

    public void showDialogInput() {
//        final TextView tv_delete = new TextView(this);
//        LinearLayout view = new LinearLayout(this);
//        view.setOrientation(LinearLayout.VERTICAL);
//        view.addView(tv_delete);
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(this)
//                .setTitle("¿Desea eliminar definitivamente?")
//                .setView(view)
//                .setPositiveButton("Eliminar", (dialog, which) -> {
//
//                    // delete entry from database
//                    sqlite.delete("Custom", currentAdivinanza);
//
//                    // refresh viwPager
//                    // viewPager.setAdapter(adapter);
//                    // super.onBackPressed();
//                    finish();
//
//                    // Show a success message
//                    Toast.makeText(AdivinanzasViewPager.this, "Eliminado correctamente", Toast.LENGTH_SHORT).show();
//                })
//                .setNegativeButton("Cancelar", (dialog, which) -> {
//                    return;
//                });
//
//        builder.create()
//                .show();

        Integer icon_int = this.getResources().getIdentifier("ic_delete", "drawable", this.getPackageName());

        final FlatDialog flatDialog = new FlatDialog(AdivinanzasViewPager.this);
        flatDialog.setIcon(icon_int)
                .setBackgroundColor(Color.WHITE)
                .isCancelable(true)
                .setSubtitle("\n¿Desea eliminar definitivamente?\n")
                .setSubtitleColor(getResources().getColor(R.color.SecondaryBlue))
                .setFirstButtonText("Eliminar")
                .setFirstButtonColor(getResources().getColor(R.color.PrimaryBlue))
                .setSecondButtonText("Cancelar")
                .withFirstButtonListner(view -> {

                    // delete entry from database
                    sqlite.delete("Custom", currentAdivinanza);

                    // Show a success message
                    Toast.makeText(AdivinanzasViewPager.this, "Eliminado correctamente", Toast.LENGTH_SHORT).show();
                    flatDialog.dismiss();
                    finish();
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // show interstitial
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.trabadivi_interstitial_id));
        AdRequest adRequest = new AdRequest.Builder().build();
        mInterstitialAd.loadAd(adRequest);
        mInterstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
            }
        });
    }
}