package esp.adivinanzas.trabalenguas.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import esp.adivinanzas.trabalenguas.Adivinanza;

public class AdivinanzasDB {
    private SQLiteDatabase db;

    public AdivinanzasDB(Context context) {
        AdivinanzasDBHelper helper = new AdivinanzasDBHelper(context);
        db = helper.getWritableDatabase();
    }

    public void insert(Adivinanza adivinanza) {
        ContentValues values = new ContentValues();
        values.put(AdivinanzasEntry.SECTION_COLUMN, adivinanza.getSection());
        values.put(AdivinanzasEntry.ADIVINANZA_COLUMN, adivinanza.getAdivinanza());
        values.put(AdivinanzasEntry.SOLUCION_COLUMN, adivinanza.getSolucion());

        db.insert(AdivinanzasEntry.ADIVINANZAS_TABLE_NAME, null, values);
    }

    public List<Adivinanza> get(String filterSection) {
        String WHERE = "section is \"" + filterSection + "\"";
        String ORDERBY = "RANDOM()";
        Cursor cursor = db.query(
                AdivinanzasEntry.ADIVINANZAS_TABLE_NAME,
                new String[]{
                        AdivinanzasEntry.SECTION_COLUMN,
                        AdivinanzasEntry.ADIVINANZA_COLUMN,
                        AdivinanzasEntry.SOLUCION_COLUMN
                },
                WHERE,
                null,
                null,
                null,
                ORDERBY,
                null
        );

        List<Adivinanza> result = new ArrayList<>();

        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String section = cursor.getString(0);
                String text_adivinanza = cursor.getString(1);
                String solucion = cursor.getString(2);

                Adivinanza adivinanza = new Adivinanza();
                adivinanza.setSection(section);
                adivinanza.setAdivinanza(text_adivinanza);
                adivinanza.setSolucion(solucion);

                result.add(adivinanza);
            }
        }
        cursor.close();
        return result;
    }

    public List<Adivinanza> getAll() {
        Cursor cursor = db.query(
                AdivinanzasEntry.ADIVINANZAS_TABLE_NAME,
                new String[]{
                        AdivinanzasEntry.SECTION_COLUMN,
                        AdivinanzasEntry.ADIVINANZA_COLUMN,
                        AdivinanzasEntry.SOLUCION_COLUMN
                },
                null,
                null,
                null,
                null,
                null,
                null
        );

        List<Adivinanza> result = new ArrayList<>();

        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String section = cursor.getString(0);
                String text_adivinanza = cursor.getString(1);
                String solucion = cursor.getString(2);

                Adivinanza adivinanza = new Adivinanza();
                adivinanza.setSection(section);
                adivinanza.setAdivinanza(text_adivinanza);
                adivinanza.setSolucion(solucion);

                result.add(adivinanza);
            }
        }
        cursor.close();
        return result;
    }

    public void delete(String section, String adivinanza_text){
        db.execSQL("DELETE FROM adivinanzas WHERE section = '" + section + "' AND adivinanza_text = '" + adivinanza_text + "'");
    }
}
