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


public class Sorgula extends ActionBarActivity {
    DatabaseHelper myDb;
    EditText editName, editSurname, editTC, editText;

    Button btnviewAll;
    Button btnSorgula;
    String barcode;
    public static int av=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sorgula);
        myDb = new DatabaseHelper(this);


        btnSorgula = (Button) findViewById(R.id.buttonSorgula);
        btnviewAll = (Button) findViewById(R.id.buttonGoster);
        editText = (EditText) findViewById(R.id.editText);

        tumunuGor();
        varmiyokmu();

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


    public void  ogrenciListele(String title, String Message) {

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
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanResult != null) {

            barcode = scanResult.getContents();
            editText.setText(barcode);

        }


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


    public void varmiyokmu() { //öğrencinin listede olup olmadığını sorgular

        btnSorgula.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllData();
                        if (res.getCount() == 0) {
                            ogrenciListele("Hata", "Veri Bulunamadı");
                            return;
                        }

                        av=0;
                        while (res.moveToNext()) {

                            String dbTC = res.getString(3);
                            String qrTC = editText.getText().toString();
                            if(dbTC.equals(qrTC))
                            {

                                 av=1;
                            }


                        }
                        if(av==1){ Toast.makeText(Sorgula.this, "Burada.", Toast.LENGTH_LONG).show();}
                        if(av==0){ Toast.makeText(Sorgula.this, "Burada yok.", Toast.LENGTH_LONG).show();}


                    }


                }
        );
    }

}