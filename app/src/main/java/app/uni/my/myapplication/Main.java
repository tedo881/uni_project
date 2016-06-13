package app.uni.my.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main extends AppCompatActivity {
    Button logIn;
   public EditText myusername;
    EditText mypassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
       getSupportActionBar().setDisplayShowHomeEnabled(true);
      getSupportActionBar().setLogo(R.drawable.logo);
      getSupportActionBar().setDisplayUseLogoEnabled(true);
       getSupportActionBar().setDisplayShowTitleEnabled(true);

        logIn = (Button) findViewById(R.id.button2);
        myusername= (EditText) findViewById(R.id.editText);
        mypassword= (EditText) findViewById(R.id.editText2);
        logIn.setTextColor(Color.WHITE);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        if(sp.getBoolean("IsLogIn",false))
            this.startActivity(new Intent(this, HeaderActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username= myusername.getText().toString();
                String password = mypassword.getText().toString();


            // LogIn logIn = new LogIn(getBaseContext());
              //  logIn.execute(username, password);
                if (isConnectingToInternet()) {

                    if (username.equals("")) {
                        myusername.setError("შეავსე სახელის ველი");
                    }
                    if (password.equals("")) {
                        mypassword.setError("შეავსე პაროლის ველი");
                    }
                    if (!password.equals("") || !username.equals("")) {

                        new LogIn(getBaseContext()).execute(username, password);


                    }


                }

                else
                    Toast.makeText(Main.this, "No Internet Connection", Toast.LENGTH_SHORT).show();


              //  startActivity(new Intent(Main.this, MainActivity.class));



            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }
    public boolean isConnectingToInternet(){
        ConnectivityManager connectivity = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null)
        {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED)
                    {
                        return true;
                    }

        }
        return false;
    }
}
