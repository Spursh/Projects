package com.example.anushatamanampudi.application;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class ShoppingCartActivity extends Activity {

    private List<Product> mCartList;
    private ProductAdapter mProductAdapter;
    private TextView priceTxt;
    public static String line;
    double price=0;
    public static String res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shoppingcart);
        priceTxt = (TextView)findViewById(R.id.totalprice);

        mCartList = ShoppingCartHelper.getCartList();

        // Make sure to clear the selections
        for(int i=0; i<mCartList.size(); i++) {
            mCartList.get(i).selected = false;
        }
        price=ShoppingCartHelper.getTotalprice();
        priceTxt.setText("Total Price: " + price);
        // Create the list
        final ListView listViewCatalog = (ListView) findViewById(R.id.ListViewCatalog);
        mProductAdapter = new ProductAdapter(mCartList, getLayoutInflater(), true);
        listViewCatalog.setAdapter(mProductAdapter);

        listViewCatalog.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent productDetailsIntent = new Intent(getBaseContext(),ProductDetailsActivity.class);
                productDetailsIntent.putExtra(ShoppingCartHelper.PRODUCT_INDEX, position);
                startActivity(productDetailsIntent);
            }
        });

    }
    public void showpayment(View v1) {

            Log.i("clicks", "You Clicked B1");
            Intent i=new Intent(ShoppingCartActivity.this, paymentActivity.class);
            startActivity(i);

    }

    public void showdiscounts(View v1) {

        Log.i("clicks", "You Clicked B1");
        class discountsAsync extends AsyncTask<String, Void, String> {


            String username = LoginActivity.USER_NAME;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

            }

            @Override
            protected String doInBackground(String... params) {
                try {
                    Log.v("list details",ShoppingCartHelper.getProductname());
                    String link = "http://smartbasketapp.mybluemix.net/Current_purchase.php";
                    Log.v("hjdjjdks",username);
                    String data = ("username") + "=" + (username);
                    data += "&" + ("list") + "=" + (ShoppingCartHelper.getProductname());
                    data += "&" + ("totalprice") + "=" + (ShoppingCartHelper.getTotalprice());
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
                }
                catch (Exception e) {
                    return new String("Exception: " + e.getMessage());
                }



            }

            @Override
            protected void onPostExecute(String result){
                 result = result.trim();
                 res=result;
                Intent i=new Intent(ShoppingCartActivity.this, SuggestedProducts.class);
                i.putExtra("setdata",result);
                startActivity(i);
                Log.v("ajhdadjh",result);

            }
        }

        discountsAsync d = new discountsAsync();
        d.execute();


    }
    @Override
    protected void onResume() {
        super.onResume();

        // Refresh the data
        if(mProductAdapter != null) {
            mProductAdapter.notifyDataSetChanged();
        }
    }

    public static String getsuggestedproducts(){
        return res;
    }

}

