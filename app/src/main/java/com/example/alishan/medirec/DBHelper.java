package com.example.alishan.medirec;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by AliShan on 4/3/2018.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MediRec.db";
    public static final String TABLE_PATIENTS = "Patients";
    public static final String PATIENTS_COLUMN_ID = "p_id";
    public static final String PATIENTS_COLUMN_NAME = "p_Name";
    public static final String PATIENTS_COLUMN_PHONE = "p_Contact";
    public static final String PATIENTS_COLUMN_Gender = "p_Gender";
    public static final String PATIENTS_COLUMN_Email = "p_Email";
    public static final String PATIENTS_COLUMN_ADDRESS = "p_Address";
    public static final String PATIENTS_COLUMN_EMERGENCY = "p_EmergencyContact";

    //Report Table start
    public static final String TABLE_Checkup = "checkupRecord";
    public static final String Checkup_COLUMN_Time = "Time";
    public static final String Checkup_COLUMN_Date = "Date";
    public static final String Checkup_COLUMN_ID = "c_id";
    public static final String Checkup_COLUMN_PID = "P_id";
    public static final String Checkup_COLUMN_BP = "BloodPressure";
    public static final String Checkup_COLUMN_Temprature = "Temprature";
    public static final String Checkup_COLUMN_Respiration = "Respiration";
    public static final String Checkup_COLUMN_Sugar = "Sugar";
    public static final String Checkup_COLUMN_SleepHours= "SleepHours";
    public static final String Checkup_COLUMN_pluse= "pluse";
    private HashMap hp;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table checkupRecord " +
                        "(c_id integer primary key AUTOINCREMENT , P_id integer ,Time text,Date text,BloodPressure text,Temprature text ,Respiration text ,Sugar text,SleepHours text,pluse text)"
        );
        db.execSQL(
                "create table Patients " +
                        "(p_id integer primary key AUTOINCREMENT ,p_Name text,p_Contact text,p_Gender text,p_Email text ,p_Address text ,p_EmergencyContact text)"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS Patients");
        db.execSQL("DROP TABLE IF EXISTS checkup");
        onCreate(db);
    }

    public boolean insertPatient (Add_patient_model P) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(PATIENTS_COLUMN_NAME, P.getP_Name());
        contentValues.put(PATIENTS_COLUMN_PHONE, P.getP_Contact());
        contentValues.put(PATIENTS_COLUMN_Gender, P.getP_Gender());
        contentValues.put(PATIENTS_COLUMN_Email, P.getP_Email());
        contentValues.put(PATIENTS_COLUMN_ADDRESS, P.getP_Address());
        contentValues.put(PATIENTS_COLUMN_EMERGENCY,P.getP_EmergencyContact());
       long i =db.insert(TABLE_PATIENTS, null, contentValues);
        return true;
    }
//Check up insert

    /*******************************************************/
    public boolean insertcheckup (Report_Model RM) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Checkup_COLUMN_PID,RM.getP_id());
        contentValues.put(Checkup_COLUMN_Time, RM.getTime());
        contentValues.put(Checkup_COLUMN_Date, RM.getDate());
        contentValues.put(Checkup_COLUMN_BP, RM.getBloodPressure());
        contentValues.put(Checkup_COLUMN_Temprature, RM.getTemperature());
        contentValues.put(Checkup_COLUMN_Respiration,RM.getRespiration());
        contentValues.put(Checkup_COLUMN_Sugar, RM.getSugar());
        contentValues.put(Checkup_COLUMN_SleepHours,RM.getSleepHours());
        contentValues.put(Checkup_COLUMN_pluse,RM.getPluse());
        long i =db.insert(TABLE_Checkup, null, contentValues);
        return true;
    }
    /******************************************************/
  /*  public Add_patient_model getPatientById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from patients where id="+id+"", null );
        //Add_patient_model patient=new Add_patient_model(res.getInt(res.getColumnIndex(PATIENTS_COLUMN_ID)), res.getString(res.getColumnIndex(PATIENTS_COLUMN_NAME)),res.getString(res.getColumnIndex(PATIENTS_COLUMN_EMERGENCY)),res.getString(res.getColumnIndex(PATIENTS_COLUMN_NATIONALITY)) , res.getString(res.getColumnIndex(PATIENTS_COLUMN_PHONE)),res.getString(res.getColumnIndex(PATIENTS_COLUMN_DESP)));
        return patient;
    }
*/
    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE_PATIENTS);
        return numRows;
    }

    public boolean updateContact (Integer id, String name, String phone, String email, String street,String place) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("phone", phone);
        contentValues.put("email", email);
        contentValues.put("street", street);
        contentValues.put("place", place);
        db.update("contacts", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deleteContact (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("contacts",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public ArrayList<Add_patient_model> getAllPatients() {
        ArrayList<Add_patient_model> array_list = new ArrayList<Add_patient_model>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from Patients", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            Add_patient_model patient=new Add_patient_model(res.getInt(res.getColumnIndex(PATIENTS_COLUMN_ID)), res.getString(res.getColumnIndex(PATIENTS_COLUMN_NAME)),res.getString(res.getColumnIndex(PATIENTS_COLUMN_PHONE)),res.getString(res.getColumnIndex(PATIENTS_COLUMN_Gender)) , res.getString(res.getColumnIndex(PATIENTS_COLUMN_Email)),res.getString(res.getColumnIndex(PATIENTS_COLUMN_ADDRESS)),res.getString(res.getColumnIndex(PATIENTS_COLUMN_EMERGENCY)));
            array_list.add(patient);
            res.moveToNext();
        }
        return array_list;
    }
    public ArrayList<Report_Model> getRecordByPatientId(int p_id)
    {
        ArrayList<Report_Model> Rm = new ArrayList<Report_Model>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from checkupRecord where P_id = '"+p_id+"'", null );
        res.moveToFirst();
/*res.getInt(res.getColumnIndex(Checkup_COLUMN_ID))*/
       // res.getInt(res.getColumnIndex(Checkup_COLUMN_PID))
        while(res.isAfterLast() == false){

           Rm.add( new Report_Model(res.getInt(res.getColumnIndex(Checkup_COLUMN_ID)),res.getInt(res.getColumnIndex(Checkup_COLUMN_PID)),res.getString(res.getColumnIndex(Checkup_COLUMN_Time)),res.getString(res.getColumnIndex(Checkup_COLUMN_Date)),res.getString(res.getColumnIndex(Checkup_COLUMN_BP)),res.getString(res.getColumnIndex(Checkup_COLUMN_Temprature)),res.getString(res.getColumnIndex(Checkup_COLUMN_Respiration)),res.getString(res.getColumnIndex(Checkup_COLUMN_Sugar)),res.getString(res.getColumnIndex(Checkup_COLUMN_SleepHours)),res.getString(res.getColumnIndex(Checkup_COLUMN_pluse))));
            res.moveToNext();
        }
        return Rm;
    }
}