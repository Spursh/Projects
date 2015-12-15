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
    public TextView SumTxt;

    private ArrayAdapter<String> lvArrayAdapter;
    ArrayList<String> itemList;
    ArrayList<Integer> priceList;

    public PriceActivity(Context context, TextView PriceTxt,TextView SumTxt,ArrayList<String> itemList,ArrayList<Integer> priceList,ArrayAdapter<String> lvArrayAdapter) {
        this.context = context;
        this.PriceTxt = PriceTxt;
        this.lvArrayAdapter=lvArrayAdapter;
        this.itemList=itemList;
        this.priceList=priceList;
        this.SumTxt=SumTxt;

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


        Toast.makeText(context, result, Toast.LENGTH_LONG).show();
        // Log.d("ttttttt", result);
        this.PriceTxt.setText(result);
        //line = result;
        if(PriceTxt.getText().toString().isEmpty()){

        }else {
            //Log.d("ttttttt", String.valueOf(result.indexOf(":") + 1-result.indexOf(".")));
            //Log.v("gusdgjht", String.valueOf(result.indexOf(".")));

            if (((result.indexOf("."))-(result.indexOf(":")+1)) > 0) {
                int intresult = Integer.parseInt(result.substring(result.lastIndexOf(":") + 1, result.lastIndexOf(".")));


                priceList.add(intresult);
                int sum = 0;
                for (int i = 0; i < priceList.size(); i++) {
                    sum += priceList.get(i);
                }
                //this.SumTxt.setText("Total Price: " + String.valueOf(sum));
                Toast.makeText(context, String.valueOf(sum), Toast.LENGTH_LONG).show();
                itemList.add(PriceTxt.getText().toString());
               // new ShoppingCartHelper(result.substring(result.indexOf(":") + 1, result.indexOf(".")),"",intresult);
                ShoppingCartHelper.addcatalog(result.substring(result.indexOf(":") + 1, result.indexOf(".")),"",intresult);

                //lvArrayAdapter.notifyDataSetChanged();
            } else {
                Toast.makeText(context, "Sorry product scanned is not for sale!", Toast.LENGTH_LONG).show();
            }
        }







    }

}

