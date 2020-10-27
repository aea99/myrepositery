package a.e.trygit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
private TextView mTextViewResult;
private RequestQueue mQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextViewResult = findViewById(R.id.textViewResult);
        Button buttonParse = findViewById(R.id.buttonParse);

        mQueue = Volley.newRequestQueue(this);
        buttonParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonParse();
            }
        });

    }
    private void jsonParse(){

        String url = "https://jsonplaceholder.typicode.com/users";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
              try {
                  JSONArray jsonArray = response.getJSONArray("");

                  for (int i=0; i< jsonArray.length(); i++){
                      JSONObject person = jsonArray.getJSONObject(i);

                      int id = person.getInt("id");
                      String name = person.getString("name");
                      String username = person.getString("username");
                      String email = person.getString("email");

                  }

              } catch (JSONException e){
               e.printStackTrace();
              }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }

}