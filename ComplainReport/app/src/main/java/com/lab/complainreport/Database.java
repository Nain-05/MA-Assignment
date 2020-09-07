package com.lab.complainreport;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {

    private static final int dbVersion=1;
    private static final String DATABASENAAM="JReport";
    private static final String JTABLE="user";
    private static final String JCom="complain";
    private static final String category="cat";
    private static final String severity="sev";
    private static final String description="des";
    private static final String CID="cid";
    private static final String ID="id";
    private static final String Name="name";
    private static final String Email="email";
    private static final String Password="pass";
    private static final String  Lattitude="lattitude";
    private static final String Longitude= "longitude";
    private static final String userIdentity= "useridentity";
    private static final String compI="comImage";



    private String CreateTable="CREATE TABLE " + JTABLE + "("
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + Name + " TEXT," + Email + " TEXT," + Password + " TEXT" + ")";
    private String ComplainTable="CREATE TABLE " + JCom + "("
            + CID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + userIdentity + " TEXT,"  + category + " TEXT," + severity + " TEXT," + description + " TEXT," + Longitude + " TEXT," + Lattitude + " TEXT," +  compI + " BLOB Not null" +  ")";

    private String DropTable="DROP TABLE IF EXISTS " + JTABLE;
    private String DropTableC="DROP TABLE IF EXISTS " + JCom;
    Database(Context context){
        super(context,DATABASENAAM,null,dbVersion);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CreateTable);
        db.execSQL(ComplainTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DropTable);
        db.execSQL(DropTableC);

        onCreate(db);

    }
    public void addUser(User user ){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(Name,user.getName());
        values.put(Email,user.getEmail());
        values.put(Password,user.getPassword());
        db.insert(JTABLE,null,values);
        db.close();
    }

    public boolean checkUser(String email, String pass){
        String[] col={ID};
        SQLiteDatabase db=this.getWritableDatabase();
        String selection=Email + " = ?" + " AND " + Password + " = ?";
        String[] selectArg={email,pass};
        Cursor cursor=db.query(JTABLE,col,selection,selectArg,null,null,null);
        int coursorCount =cursor.getCount();
        db.close();
        if(coursorCount > 0){
            return true;
        }
        return false;
    }
    public boolean checkUser(String email){
        String[] col={ID};
        SQLiteDatabase db=this.getWritableDatabase();
        String selection=Email + " = ?";
        String[] selectArg={email};
        Cursor cursor=db.query(JTABLE,col,selection,selectArg,null,null,null);
        int coursorCount =cursor.getCount();
        db.close();
        if(coursorCount > 0){
            Log.e("TAG", "Data Haaaaaaaaaai");
            return true;
        }
        return false;
    }

    public void insertData(User user) throws SQLiteException {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(userIdentity,user.getUserEmail());
        values.put(category,user.getCat());
        values.put(severity,user.getSev());
        values.put(description,user.getDes());
        values.put(Longitude,user.getLon());
        values.put(Lattitude,user.getLat());
        values.put(compI,user.getIm());
        db.insert(JCom,null,values);
        db.close();
    }

    public Cursor ViewData(){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from " + JCom,null);
        return cursor;


    }
    /* Retrive  data from database */
    public List<User> getDataFromDB(){
        List<User> modelList = new ArrayList<User>();
        String query = "select * from "+JCom;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()){
            do {
                User jUser = new User();
                jUser.setUserEmail(cursor.getString(1));
                jUser.setCat(cursor.getString(2));
                jUser.setSev(cursor.getString(3));
                jUser.setDes(cursor.getString(4));
                jUser.setLon(cursor.getDouble(5));
                jUser.setLat(cursor.getDouble(6));
                jUser.setIm(cursor.getString(7));

                modelList.add(jUser);
            }while (cursor.moveToNext());
        }


        Log.d("data", modelList.toString());


        return modelList;
    }
}
