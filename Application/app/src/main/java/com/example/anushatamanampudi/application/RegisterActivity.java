package com.example.anushatamanampudi.application;

/**
 * Created by anushatamanampudi on 12/1/15.
 */
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextName;
    private EditText editTextUsername;
    private EditText editTextPassword;
    //private EditText editTextEmail;
    public static String line;

    private Button buttonRegister;

    //private static final String REGISTER_URL = "http://smartbasketapp.mybluemix.net/register.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v("hsedsdhsii","entered loop");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_main);

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextUsername = (EditText) findViewById(R.id.editTextUserName);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);


        buttonRegister = (Button) findViewById(R.id.buttonRegister);

        buttonRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == buttonRegister){
            registerUser();
        }
    }

    private void registerUser() {
        String name = editTextName.getText().toString().trim().toLowerCase();
        String username = editTextUsername.getText().toString().trim().toLowerCase();
        String password = editTextPassword.getText().toString().trim().toLowerCase();


        register(name, username, password);
    }

    private void register(String name, String username, String password) {


            class RegisterUser extends AsyncTask<String, Void, String>{




                ProgressDialog loading;


                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    loading = ProgressDialog.show(RegisterActivity.this, "Please Wait",null, true, true);
                }

                @Override
                protected String doInBackground(String... params) {
                    try {
                        String name = params[0];
                        String username = params[1];
                        String password = params[2];
                        Log.v("uabdbhj",username);
                        Log.v("pwd",password);
                        String link = "http://smartbasketapp.mybluemix.net/register.php";
                        String data= ("name") + "=" + (name);
                        data += "&"+("username") + "=" + (username);
                        data += "&" + ("password") + "=" + (password);

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
                    String s = result.trim();
                    loading.dismiss();
                    Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();

                }
            }

        RegisterUser ru = new RegisterUser();
        ru.execute(name,username,password);


        }








    public void login(View v) {
        Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(i);

    }
}
