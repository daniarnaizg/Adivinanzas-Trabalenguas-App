package esp.adivinanzas.trabalenguas.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class AdivinanzasDBHelper extends SQLiteAssetHelper {
    private Context context;

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "adivinanzas.db";

    public AdivinanzasDBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + AdivinanzasEntry.ADIVINANZAS_TABLE_NAME + ";");
        onCreate(db);
    }
}
