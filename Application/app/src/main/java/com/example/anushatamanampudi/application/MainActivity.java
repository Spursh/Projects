package com.example.anushatamanampudi.application;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnClickListener{
    ArrayList<String> itemList =new ArrayList<String>();
    private Button scanBtn,showcartBtn;
    private TextView formatTxt, contentTxt,PriceTxt,SumTxt;
    private ListView itemsLV;
    private ArrayAdapter<String> lvArrayAdapter;
    ArrayList<Integer> priceList=new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        scanBtn = (Button)findViewById(R.id.scan_button);
        showcartBtn=(Button)findViewById(R.id.Cart_button);
        formatTxt = (TextView)findViewById(R.id.scan_format);
        contentTxt = (TextView)findViewById(R.id.scan_content);
        PriceTxt = (TextView)findViewById(R.id.price_content);
        SumTxt = (TextView)findViewById(R.id.SumTxt);
        scanBtn.setOnClickListener(this);
        //itemsLV = (ListView)findViewById(R.id.listView);
       // ListView itemsLV = (ListView)findViewById(R.id.listView);
       lvArrayAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,itemList);
      //  itemsLV.setAdapter(lvArrayAdapter);

    }

    // assign the list adapter


    public void showcart(View view) {
        Intent intent = new Intent(this, CatalogActivity.class);
        startActivity(intent);
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

    public void onClick(View v){
        if(v.getId()==R.id.scan_button){
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();
//scan
        }




    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
//retrieve scan result
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        //Log.v(String.valueOf(scanningResult),"reslut");
        if (scanningResult != null) {

            if(scanningResult.getContents()!=null && scanningResult.getFormatName()!=null) {
                String scanContent = scanningResult.getContents();
               // String scanFormat = scanningResult.getFormatName();

               // formatTxt.setText("FORMAT: " + scanFormat);
                contentTxt.setText("CONTENT: " + scanContent);
                new PriceActivity(this, PriceTxt, SumTxt, itemList,priceList,lvArrayAdapter).execute(scanContent);

            }
            else{
                Toast toast = Toast.makeText(getApplicationContext(),
                        "No scan data received!", Toast.LENGTH_SHORT);
                toast.show();
            }
            //String id = contentTxt.getText().toString();

        }
        else{
            Log.v("usyguyu","bjndjnvfjd");
            Toast toast = Toast.makeText(getApplicationContext(),
                    "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }




}
