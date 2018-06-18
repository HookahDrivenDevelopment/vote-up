package hookahdrivendevelopment.vote_up;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.UnsupportedEncodingException;

import hookahdrivendevelopment.vote_up.types.Answer;
import hookahdrivendevelopment.vote_up.types.Vote;

public class VoteCreateActivity extends AppCompatActivity {
    RequestQueue requestQueue;

    private EditText etTitle;
    private EditText etContent;

    private EditText etAnswer1;
    private EditText etAnswer2;
    private EditText etAnswer3;
    private EditText etAnswer4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote_create);

        etTitle = (EditText) findViewById(R.id.etTitle);
        etContent = (EditText) findViewById(R.id.etContent);

        etAnswer1 = (EditText) findViewById(R.id.etAnswer1);
        etAnswer2 = (EditText) findViewById(R.id.etAnswer2);
        etAnswer3 = (EditText) findViewById(R.id.etAnswer3);
        etAnswer4 = (EditText) findViewById(R.id.etAnswer4);

        requestQueue = Volley.newRequestQueue(this);
    }

    public void publishVote(View view) {
        Vote vote = new Vote();
        vote.title = etTitle.getText().toString();
        vote.content = etContent.getText().toString();

        vote.answers.add(new Answer(etAnswer1.getText().toString()));
        vote.answers.add(new Answer(etAnswer2.getText().toString()));
        vote.answers.add(new Answer(etAnswer3.getText().toString()));
        vote.answers.add(new Answer(etAnswer4.getText().toString()));

        Gson gson = new GsonBuilder().create();
        final String jsonVote = gson.toJson(vote);
        final String url = "http://10.0.2.2:3000";

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", error.toString());
                    }
                }
        ) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return jsonVote == null ? null : jsonVote.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    Log.d("tag", "unsupported error");
                    return null;
                }
            }
        };
        postRequest.setRetryPolicy(new DefaultRetryPolicy(
                30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        requestQueue.add(postRequest);
        Snackbar.make(view, "publishing...", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
        onBackPressed();
    }
}
