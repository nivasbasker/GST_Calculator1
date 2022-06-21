package com.codewithzio.gstcalculator;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;

import com.codewithzio.gstcalculator.DBConstants.ProductTable;

import java.util.ArrayList;
import java.util.List;

public class StoreDB extends SQLiteOpenHelper {

    //private static String DATABASE_NAME;
    private static final int DATABASE_VERSION = 1;
    private final Context context;
    private final String TABLE_NAME;
    private SQLiteDatabase db;

    public StoreDB(@NonNull Context context, String DatabaseName, String TableName) {
        super(context, DatabaseName, null, DATABASE_VERSION);
        //DATABASE_NAME = DatabaseName;
        this.context = context;
        this.TABLE_NAME = TableName;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        this.db = db;
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void DropTable(String TableName) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public void createProductTable(String name) {

        db = this.getWritableDatabase();

        //create if doesnt exist already----------------------------------------

        final String SQL_CREATE_PRODUCTS_TABLE = "CREATE TABLE IF NOT EXISTS " +
                TABLE_NAME + " ( " +
                ProductTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ProductTable.COL_NAME + " TEXT, " +
                ProductTable.COL_DESC + " TEXT, " +
                ProductTable.COL_NET + " INTEGER, " +
                ProductTable.COL_GST + " INTEGER, " +
                ProductTable.COL_GROSS + " INTEGER " +
                ")";

        try {
            db.execSQL(SQL_CREATE_PRODUCTS_TABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void createInvTable(String name) {

        db = this.getWritableDatabase();

        //create if doesnt exist already----------------------------------------

        final String SQL_CREATE_PRODUCTS_TABLE = "CREATE TABLE IF NOT EXISTS " +
                TABLE_NAME + " ( " +
                DBConstants.InvoiceTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DBConstants.InvoiceTable.COL_CUSTOMER + " TEXT, " +
                DBConstants.InvoiceTable.COL_BILL + " TEXT, " +
                DBConstants.InvoiceTable.COL_DATE + " TEXT, " +
                ProductTable.COL_NET + " INTEGER, " +
                ProductTable.COL_GST + " INTEGER, " +
                ProductTable.COL_GROSS + " INTEGER " +
                ")";

        try {
            db.execSQL(SQL_CREATE_PRODUCTS_TABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public boolean isTableExists(String tableName) {

        boolean existence = false;

        if (db == null || !db.isOpen()) {
            db = getReadableDatabase();
        }

        if (!db.isReadOnly()) {
            db.close();
            db = getReadableDatabase();
        }

        String query = "SELECT * FROM sqlite_master WHERE name ='" + tableName + "' and type = 'table'";
        try (Cursor cursor = db.rawQuery(query, null)) {
            if (cursor != null) {
                if (cursor.getCount() > 0) {
                    cursor.close();
                    existence = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return existence;
    }

    public void DelDataBase(String DBName) {
        context.deleteDatabase(DBName);
    }


    public void addproduct(ModelProducts prod) {

        db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ProductTable.COL_NAME, prod.getName());
        values.put(ProductTable.COL_DESC, prod.getDesc());
        values.put(ProductTable.COL_NET, prod.getNet());
        values.put(ProductTable.COL_GST, prod.getGst());
        values.put(ProductTable.COL_GROSS, prod.getGross());

        db.insert(TABLE_NAME, null, values);

    }

    public void addInvoice(ModelInvoice inv) {

        db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DBConstants.InvoiceTable.COL_CUSTOMER, inv.getCustomer());
        values.put(DBConstants.InvoiceTable.COL_BILL, inv.getInvNum());
        values.put(DBConstants.InvoiceTable.COL_DATE, inv.getDate());
        values.put(DBConstants.InvoiceTable.COL_GROSS, inv.getGross());
        values.put(DBConstants.InvoiceTable.COL_GST, inv.getGst());
        values.put(DBConstants.InvoiceTable.COL_NET, inv.getNet());

        db.insert(TABLE_NAME, null, values);

    }

    public void addAnswers(List<Integer> list) {

        db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        for (int i = 0; i < list.size(); i++) {

            String[] selectionArgs = {"" + (i + 1)};
            //values.put(QuesTable.COLUMN_MY_ANSWER, list.get(i));
            //db.update(TABLE_NAME, values, QuesTable._ID + "= ?", selectionArgs);
        }
    }

    @SuppressLint("Range")
    public List<ModelProducts> getAllProducts() {

        List<ModelProducts> questionList = new ArrayList<>();
        db = this.getReadableDatabase();
        try (Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME, null)) {
            if (c.moveToFirst()) {
                do {
                    ModelProducts question = new ModelProducts();
                    question.setName(c.getString(c.getColumnIndex(ProductTable.COL_NAME)));
                    question.setDesc(c.getString(c.getColumnIndex(ProductTable.COL_DESC)));
                    question.setNet(c.getInt(c.getColumnIndex(ProductTable.COL_NET)));
                    question.setGst(c.getInt(c.getColumnIndex(ProductTable.COL_GST)));
                    question.setGross(c.getInt(c.getColumnIndex(ProductTable.COL_GROSS)));
                    questionList.add(question);
                } while (c.moveToNext());
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return questionList;
    }

    @SuppressLint("Range")
    public List<ModelInvoice> getAllInv() {

        List<ModelInvoice> invoiceList = new ArrayList<>();
        db = this.getReadableDatabase();
        try (Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME, null)) {
            if (c.moveToFirst()) {
                do {
                    ModelInvoice invoice = new ModelInvoice();
                    invoice.setCustomer(c.getString(c.getColumnIndex(DBConstants.InvoiceTable.COL_CUSTOMER)));
                    invoice.setDate(c.getString(c.getColumnIndex(DBConstants.InvoiceTable.COL_DATE)));
                    invoice.setInvNum(c.getString(c.getColumnIndex(DBConstants.InvoiceTable.COL_BILL)));
                    invoice.setNet(c.getInt(c.getColumnIndex(ProductTable.COL_NET)));
                    invoice.setGst(c.getInt(c.getColumnIndex(ProductTable.COL_GST)));
                    invoice.setGross(c.getInt(c.getColumnIndex(ProductTable.COL_GROSS)));
                    invoiceList.add(invoice);
                } while (c.moveToNext());
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return invoiceList;
    }

    public List<Integer> getAnswers() {
        List<Integer> answers = new ArrayList<>();
        db = this.getReadableDatabase();
        try (Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME, null)) {
            if (c.moveToFirst()) {
                do {
                    //answers.add(c.getInt(c.getColumnIndex(ProductTable.COL_GROSS));
                } while (c.moveToNext());
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return answers;
    }
}
