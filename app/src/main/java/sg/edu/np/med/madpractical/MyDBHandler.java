package sg.edu.np.med.madpractical;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;


public class MyDBHandler extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "userDB.db";
    public static final String TABLE_USERS = "users";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_USERNAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_ISFOLLOWED = "followed";
    public MyDBHandler(Context context, String name,
                       SQLiteDatabase.CursorFactory factory,
                       int version)
    {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " +
                TABLE_USERS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USERNAME + " TEXT, " +
                COLUMN_DESCRIPTION + " TEXT, " +
                COLUMN_ISFOLLOWED + " INTEGER)";
        db.execSQL(CREATE_PRODUCTS_TABLE);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    public void addUser(User user) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, user.getName());
        values.put(COLUMN_DESCRIPTION, user.getDescription());
        values.put(COLUMN_ISFOLLOWED, user.isFollowed());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_USERS, null, values);
        db.close(); // Close the database connection after the insert operation
    }
    public ArrayList<User> getUsers() {
        ArrayList<User> userList = new ArrayList<User>();
        String query = "SELECT * FROM " + TABLE_USERS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            User user = new User();
            user.setName(cursor.getString(1));
            user.setDescription(cursor.getString(2));
            user.setFollowed(Boolean.parseBoolean(cursor.getString(3)));
            userList.add(user);
            Log.d("MyDBHandler", "User: " + user.getName() + ", " + user.getDescription() + ", " + user.isFollowed());
        }
        cursor.close();
        db.close();
        return userList;
    }




}
