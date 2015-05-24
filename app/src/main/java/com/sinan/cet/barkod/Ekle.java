package com.sinan.cet.barkod;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Ekle extends ActionBarActivity {
    DatabaseHelper myDb;
    EditText editName, editSurname, editTC; //EditText'ler tanımlanır.
    Button btnAddData;                      //Butonlar tanımlanır
    Button btnviewAll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ekle);
        myDb = new DatabaseHelper(this);
        editName = (EditText) findViewById(R.id.editTextname);
        editSurname = (EditText) findViewById(R.id.editTextsurname);
        editTC = (EditText) findViewById(R.id.editTextmarks);
        btnAddData = (Button) findViewById(R.id.button);
        btnviewAll = (Button) findViewById(R.id.buttonGoster);

        veriEkle();
        tumunuGor();


    }


    public void veriEkle() { // Verilerin eklendiği metot
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertData(
                                editName.getText().toString(),
                                editSurname.getText().toString(),
                                editTC.getText().toString());
                        if (isInserted = true)
                            Toast.makeText(Ekle.this, "Veri Eklendi.", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Ekle.this, "Veri Eklenmedi.", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void tumunuGor() { //verilerin listelendiği metot

        btnviewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllData();
                        if (res.getCount() == 0) {
                            ogrenciListele("Hata", "Veri Bulunamadı");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("ID :" + res.getString(0) + "\n");
                            buffer.append("AD :" + res.getString(1) + "\n");
                            buffer.append("SOYAD :" + res.getString(2) + "\n");
                            buffer.append("TC :" + res.getString(3) + "\n\n");
                        }

                        ogrenciListele("Öğrenciler", buffer.toString());
                    }

                }
        );
    }


    public void ogrenciListele(String title, String Message) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();

    }

    public void onClick(View view) {

        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.initiateScan();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




}