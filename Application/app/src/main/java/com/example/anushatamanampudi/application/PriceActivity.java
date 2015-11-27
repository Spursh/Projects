package com.example.anushatamanampudi.application;

/**
 * Created by anushatamanampudi on 11/25/15.
 */
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * Created by anushatamanampudi on 11/25/15.
 */
public class PriceActivity extends AsyncTask<String,Void,String>  {


    public static String line;
    public TextView PriceTxt;
    public Context context;

    private ArrayAdapter<String> lvArrayAdapter;
    ArrayList<String> itemList;

    public PriceActivity(Context context, TextView PriceTxt,ArrayList<String> itemList,ArrayAdapter<String> lvArrayAdapter) {
        this.context = context;
        this.PriceTxt = PriceTxt;
        this.lvArrayAdapter=lvArrayAdapter;
        this.itemList=itemList;
        Toast.makeText(context, "hello", Toast.LENGTH_LONG).show();
    }

    public void onPreExecute() {

    }

    @Override
    public String doInBackground(String... arg0) {

        try {

            String id = (String) arg0[0];


            String link = "http://smartbasketapp.mybluemix.net/postdata.php";
            String data = ("id") + "=" + (id);
            //  data += "&" + ("password") + "=" + (password);

            URL url = new URL(link);

            URLConnection conn = url.openConnection();

            conn.setDoOutput(true);

            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

            wr.write(data);
            wr.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();


            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");

            }
            return sb.toString();


        } catch (Exception e) {
            return new String("Exception: " + e.getMessage());
        }

    }
    @Override
    public void  onPostExecute(String result) {

        Toast.makeText(context, "hello", Toast.LENGTH_LONG).show();
        Toast.makeText(context, result, Toast.LENGTH_LONG).show();
        // Log.d("ttttttt", result);
        this.PriceTxt.setText("Price: "+result);
        //line = result;
        if(PriceTxt.getText().toString().isEmpty()){

        }else{
            itemList.add(PriceTxt.getText().toString());
            lvArrayAdapter.notifyDataSetChanged();
        }








    }

}

