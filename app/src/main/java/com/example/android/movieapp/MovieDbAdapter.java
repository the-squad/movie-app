package com.example.android.movieapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;



/**
 * Created by mohamed on 11/27/2016.
 */

public class MovieDbAdapter {

    MovieDbHelper helper;

    public MovieDbAdapter(Context context) {
        helper = new MovieDbHelper(context);
    }

    public void insertFavMovie(MovieData movie)
    {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(helper.MOVIE_ID , movie.getId());
        contentValues.put(helper.MOVIE_TITLE , movie.getTitle());
        contentValues.put(helper.MOVIE_YEAR , movie.getRelease_date());
        contentValues.put(helper.MOVIE_RATE , movie.getRate());
        contentValues.put(helper.MOVIE_STORY , movie.getOverview());
        contentValues.put(helper.MOVIE_POSTER , movie.getPoster());
        contentValues.put(helper.MOVIE_COVER , movie.getBackdropPath());

        db.insert(helper.TABLE_NAME,null,contentValues);
        closeDB();
    }

    public void deleteFavMovie (MovieData movie)
    {
        SQLiteDatabase db = helper.getWritableDatabase();

        db.delete(helper.TABLE_NAME,helper.MOVIE_ID + " = ? ",new String[] {String.valueOf(movie.getId())});
        closeDB();
    }

    public ArrayList<MovieData> getAllFavMovies()
    {
        SQLiteDatabase db = helper.getReadableDatabase();

        ArrayList<MovieData> favMoviesList = new ArrayList<>();
        String sql_Query = "SELECT * FROM " + helper.TABLE_NAME;
        Cursor cursor = db.rawQuery(sql_Query,null);

        while(cursor.moveToNext())
        {
            MovieData favMovie = new MovieData();

            favMovie.setId(cursor.getString(cursor.getColumnIndex(helper.MOVIE_ID)));
            favMovie.setTitle(cursor.getString(cursor.getColumnIndex(helper.MOVIE_TITLE)));
            favMovie.setRelease_date(cursor.getString(cursor.getColumnIndex(helper.MOVIE_YEAR)));
            favMovie.setRate(cursor.getString(cursor.getColumnIndex(helper.MOVIE_RATE)));
            favMovie.setOverview(cursor.getString(cursor.getColumnIndex(helper.MOVIE_STORY)));
            favMovie.setPoster(cursor.getString(cursor.getColumnIndex(helper.MOVIE_POSTER)));
            favMovie.setBackdropPath(cursor.getString(cursor.getColumnIndex(helper.MOVIE_COVER)));

            favMoviesList.add(favMovie);
        }
        closeDB();
        return favMoviesList;

    }

    public Boolean checkMovies()
    {
        SQLiteDatabase db = helper.getReadableDatabase();

        String query = "SELECT * FROM  " + helper.TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst())
        {
            closeDB();
            return true;
        }
        else
        {
            closeDB();
            return false;
        }
    }

    public Boolean checkFav(String id)
    {
        SQLiteDatabase db = helper.getReadableDatabase();

        String query = "SELECT * FROM " + helper.TABLE_NAME + " WHERE " + helper.MOVIE_ID + "= " +id;
        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst())
        {
            closeDB();
            return true;
        }
        else
        {
            closeDB();
            return false;
        }
    }

    public void closeDB()
    {
        SQLiteDatabase db = helper.getReadableDatabase();

        if(db != null && db.isOpen())
            db.close();

    }


    static class MovieDbHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "Movies";
        private static final String TABLE_NAME = "Favorites";
        private static final String TABLE_ID = "id";
        private static final String MOVIE_ID = "movie_id";
        private static final String MOVIE_TITLE = "movie_title";
        private static final String MOVIE_YEAR = "movie_year";
        private static final String MOVIE_RATE = "movie_rate";
        private static final String MOVIE_STORY= "movie_story";
        private static final String MOVIE_POSTER = "movie_poster";
        private static final String MOVIE_COVER = "movie_cover";
        private static final int DB_VERSION = 1;
        private Context context;

        private static final String CREATE_TABLE="CREATE TABLE " + TABLE_NAME + " ( " + TABLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + MOVIE_ID + " varchar(20) unique, "
                + MOVIE_TITLE + " varchar(50), "
                + MOVIE_YEAR + " varchar(20), "
                + MOVIE_RATE + " varchar(20), "
                + MOVIE_STORY + " varchar(500), "
                + MOVIE_POSTER + " varchar(500), "
                + MOVIE_COVER + " varchar(500)"  + ")";

        private static final  String DROP_TABLE = "DROP TABLE if exists " + TABLE_NAME;

        public MovieDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DB_VERSION);//context , DBName , cursor , version
            this.context = context;

        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            //called for the first time the database is about to be created
            try {
                db.execSQL(CREATE_TABLE);
                //Message.message(context,"Database created successfuly");
            }
            catch (SQLException e){
                //Message.message(context," Connection failed due to : "+e);
            }

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            //Modifying tables
            try {
                db.execSQL(DROP_TABLE);
                onCreate(db);
                //Message.message(context,"Database upgraded successfuly");
            }
            catch (SQLException e){
                //Message.message(context,"Upgrade failed due to : "+e);
            }
        }
    }
}
