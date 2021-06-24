package com.example.foodorderapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.foodorderapp.Models.OrderModel;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    final static String DBName= "myDataBase.db";
    final static int DBVersion= 1;

    public DBHelper(@Nullable Context context) {
        super(context, DBName,null, DBVersion);
    }

    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table orderingfood"+
                        "(id integer primary key autoincrement," +
                        "name text," +
                        "phone text," +
                        "price integer," +
                        "image integer," +
                        "description text," +
                        "foodname text," +
                        "quantity integer)"
        );
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("Drop table if exists orderingfood");
    onCreate(db);
    }

    public boolean insertOrder(String name, String phone,String description,String foodname,int image, int price, int quantity){
        SQLiteDatabase database= getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",name);
        values.put("phone",phone);
        values.put("description",description);
        values.put("foodname",foodname);
        values.put("image",image);
        values.put("price",price);
        values.put("quantity",quantity);
        long id= database.insert("orderingfood",null, values);
        return id > 0;

    }

    public ArrayList<OrderModel> getOrders(){
        ArrayList<OrderModel> order =new ArrayList<>();
        SQLiteDatabase database =this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select id, foodname, image,price from orderingfood", null);
        if (cursor.moveToFirst()){
            while(cursor.moveToNext()){
                OrderModel model = new OrderModel();
                model.setOrderNumber(cursor.getInt(0)+"");
                model.setSoldItemName(cursor.getString(1)+"");
                model.setOrderImage(cursor.getInt(2));
                model.setPrice(cursor.getInt(3)+"");
                order.add(model);
            }
        }
            cursor.close();
            database.close();
            return order;
    }
        public Cursor getOrderById(int id){
            SQLiteDatabase database =this.getWritableDatabase();
            Cursor cursor = database.rawQuery("Select * from orderingfood where id=" +id, null);
            if (cursor!= null) cursor.moveToFirst();
            return cursor;
        }

    public boolean updateOrder(String name, String phone,String description,String foodname,int image, int price, int quantity, int id) {
        SQLiteDatabase database = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("phone", phone);
        values.put("description", description);
        values.put("foodname", foodname);
        values.put("image", image);
        values.put("price", price);
        values.put("quantity", quantity);
        long raw = database.update("orderingfood", values, "id =" +id, null);
        return raw > 0;
    }
    public int deleteOrder(String id){
        SQLiteDatabase database= this.getWritableDatabase();
        return database.delete("orderingfood", "id= "+id, null);
    }

}
