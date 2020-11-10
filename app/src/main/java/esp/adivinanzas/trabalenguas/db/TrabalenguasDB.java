package esp.adivinanzas.trabalenguas.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import esp.adivinanzas.trabalenguas.Trabalenguas;

public class TrabalenguasDB {
    private SQLiteDatabase db;

    public TrabalenguasDB(Context context) {
        TrabalenguasDBHelper helper = new TrabalenguasDBHelper(context);
        db = helper.getWritableDatabase();
    }

    public void insert(Trabalenguas trabalenguas) {
        ContentValues values = new ContentValues();
        values.put(TrabalenguasEntry.SECTION_COLUMN, trabalenguas.getSection());
        values.put(TrabalenguasEntry.TEXT_COLUMN, trabalenguas.getText());

        db.insert(TrabalenguasEntry.TRABALENGUAS_TABLE_NAME, null, values);
    }

    public List<Trabalenguas> get(String filterSection) {
        String WHERE = "section is \"" + filterSection + "\"";
        String ORDERBY = "RANDOM()";
        Cursor cursor = db.query(
                TrabalenguasEntry.TRABALENGUAS_TABLE_NAME,
                new String[]{
                        TrabalenguasEntry.SECTION_COLUMN,
                        TrabalenguasEntry.TEXT_COLUMN
                },
                WHERE,
                null,
                null,
                null,
                ORDERBY,
                null
        );

        List<Trabalenguas> result = new ArrayList<>();

        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String section = cursor.getString(0);
                String text = cursor.getString(1);

                Trabalenguas trabalenguas = new Trabalenguas();
                trabalenguas.setSection(section);
                trabalenguas.setText(text);

                result.add(trabalenguas);
            }
        }
        cursor.close();
        return result;
    }

    public List<Trabalenguas> getAll() {
        Cursor cursor = db.query(
                TrabalenguasEntry.TRABALENGUAS_TABLE_NAME,
                new String[]{
                        TrabalenguasEntry.SECTION_COLUMN,
                        TrabalenguasEntry.TEXT_COLUMN
                },
                null,
                null,
                null,
                null,
                null,
                null
        );

        List<Trabalenguas> result = new ArrayList<>();

        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String section = cursor.getString(0);
                String text = cursor.getString(1);

                Trabalenguas trabalenguas = new Trabalenguas();
                trabalenguas.setSection(section);
                trabalenguas.setText(text);

                result.add(trabalenguas);
            }
        }
        cursor.close();
        return result;
    }

    public void delete(String section, String traba_text) {
        db.execSQL("DELETE FROM trabalenguas WHERE section = '" + section + "' AND traba_text = '" + traba_text + "'");
    }
}
