package org.techtown.veganproject.ui.map;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import static android.os.Build.ID;

public class DbHelper extends SQLiteOpenHelper {
    /*
     * 내부 DB 사용하는 함수 = IN
     * 외부 DB에 사용하는 함수 = OUT*/

    /*여기는 내부 DB 사용할 때의 변수들*/

    private int _id;
    private String name;
    private Double lat;
    private Double lon;


    private Context context;
    public static final int DB_VERSION = 6; //DB onCreate String s 바뀔시 숫자 up


    public DbHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    //IN
    //OUT에서는 필요 없음
    @Override
    public void onCreate(SQLiteDatabase db) {
        String s;
        s = " CREATE TABLE IF NOT EXISTS BookmarkMap ( "
                + " _ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + " NAME STRING NOT NULL, "
                + " LAT DOUBLE NOT NULL, "
                + " LON DOUBLE NOT NULL)";

        db.execSQL(s);
        //Toast.makeText(context, "Diary Table Created", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Toast.makeText(context, "버전이 올라갔습니다.", Toast.LENGTH_SHORT).show();
    }


    //IN
    public void testDB() {
        SQLiteDatabase db = getReadableDatabase();
    }

    //IN
    public SQLiteDatabase insertBookmark(bookmark_data bookmarkMap) {

        SQLiteDatabase db = getWritableDatabase();
        String s;
        s = " INSERT INTO BookmarkMap ( "
                + " NAME, LAT, LON)"
                + " VALUES ( ?, ?, ? )";

        db.execSQL(s, new Object[]{
                bookmarkMap.getName(),
                bookmarkMap.getLat(),
                bookmarkMap.getLon()});


        Log.i("DIARY DATA INSERT : ", "SUCCESS");
        // Toast.makeText(context, "Diary Insert 완료", Toast.LENGTH_SHORT).show();

        return db; //왜 리턴을 한건지..?
    }

    // IN
    // 해당 날짜가 가진 모든 diary의  데이터(날짜/아점저/음식/칼로리/사진)를 가져오는 메소드
    public ArrayList<bookmark_data> getBookmarkDataByDate() {
        String M;
        // String d = Integer.toString(data);
        // String n = NAME;
        M = "SELECT _ID, NAME, LAT, LON FROM BookmarkMap" ;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(M, null);
        ArrayList<bookmark_data> bookmarkMapList = new ArrayList<>();
        bookmark_data bookmarkMap = null;

        while (cursor.moveToNext()) {
            bookmarkMap = new bookmark_data();
            Log.i("DIARY DATA : ", String.valueOf(cursor.getInt(0)));
            Log.i("DIARY DATA : ", cursor.getString(1));
            Log.i("DIARY DATA : ", String.valueOf(cursor.getDouble(2)));
            Log.i("DIARY DATA : ", String.valueOf(cursor.getDouble(3)));

            bookmarkMap.set_ID(cursor.getInt(0));
            bookmarkMap.setName(cursor.getString(1));
            bookmarkMap.setLat(cursor.getDouble(2));
            bookmarkMap.setLon(cursor.getDouble(3));


            bookmarkMapList.add(bookmarkMap);
        }
        cursor.close();
        return bookmarkMapList;

    }

    public void delete_column(int Id){
        //  Log.d("삭제시작", String.valueOf(position));
        Log.d("삭제시작", String.valueOf(Id));

        SQLiteDatabase db = getWritableDatabase();
        // position=position+1;
        //SQLiteDatabase db = getWritableDatabase();
        //db.delete("BookmarkMap", ID + "=?" + position , null);


        db.execSQL("DELETE FROM " + "BookmarkMap" + " WHERE " +
                "_ID" + " = " + Id + ";");//삭제를 위해 변경

       /* db.execSQL("DELETE FROM " + "BookmarkMap" + " WHERE " +
                "_ID" + " = " + position + ";");*/

       /* db.execSQL("UPDATE " + "BookmarkMap" + " SET " + "_ID" + " = " +
                "_ID" + " -1 " + " WHERE " + "_ID" + " > " + position + ";");*/

      /*  db.execSQL("UPDATE " + "BookmarkMap" + " SET " + "_ID" + " = " +
                "_ID" + " -1 " + " WHERE " + "_ID" + " > " + position + ";");*/
        db.close();
        Log.d("삭제 완료", String.valueOf(Id));

      /*  db.execSQL("DELETE FROM BookmarkMap WHERE _ID = '" + id + "';");
        db.close();
        Log.d("데이터 삭제 완료", String.valueOf(id));*/
       /* String [] whereArgs = new String[] {String.valueOf(position)};
        db.delete("BookmarkMap","_ID",new String[] {String.valueOf(position)});
        db.close();*/


    }

    public void dropTable() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DROP TABLE BookmarkMap");
    }

}
