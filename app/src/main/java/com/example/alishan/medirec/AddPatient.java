package com.example.alishan.medirec;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddPatient extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);
        final TextInputLayout p_name = (TextInputLayout) findViewById(R.id.input_layout_name);
        final TextInputLayout p_phone = (TextInputLayout)findViewById(R.id.input_layout_Phone);
        final TextInputLayout p_Email = (TextInputLayout)findViewById(R.id.input_layout_Email);
        final  TextInputLayout p_Address = (TextInputLayout)findViewById(R.id.input_layout_Address);
        final TextInputLayout p_Emergency =(TextInputLayout) findViewById(R.id.input_layout_Emergeny);

       Button btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Add_patient_model P= new Add_patient_model();
                P.setP_Name(p_name.getEditText().getText().toString());
               P.setP_Contact(p_phone.getEditText().getText().toString());
                P.setP_Gender("male");
                P.setP_Email(p_Email.getEditText().getText().toString());
                P.setP_Address(p_Address.getEditText().getText().toString());
               P.setP_EmergencyContact(p_Emergency.getEditText().getText().toString());
                DBHelper db=new DBHelper(getApplicationContext());
                db.insertPatient(P);
                Toast.makeText(getApplicationContext()," Patient Added  Sucessfully ",Toast.LENGTH_LONG).show();
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });
    }
}
