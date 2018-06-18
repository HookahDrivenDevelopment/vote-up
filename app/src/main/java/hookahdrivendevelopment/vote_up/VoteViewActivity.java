package hookahdrivendevelopment.vote_up;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

import org.w3c.dom.Text;

import java.io.UnsupportedEncodingException;

import hookahdrivendevelopment.vote_up.types.Answer;
import hookahdrivendevelopment.vote_up.types.Vote;
import hookahdrivendevelopment.vote_up.types.VoteUpdateRequest;

public class VoteViewActivity extends AppCompatActivity {

    private Vote mVote;

    private TextView tvTitle;
    private TextView tvAuthor;
    private TextView tvContent;

    private Button bAnswer1;
    private Button bAnswer2;
    private Button bAnswer3;
    private Button bAnswer4;

    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote_view);

        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvAuthor = (TextView) findViewById(R.id.tvAuthor);
        tvContent = (TextView) findViewById(R.id.tvContent);

        bAnswer1 = (Button) findViewById(R.id.bAnswer1);
        bAnswer2 = (Button) findViewById(R.id.bAnswer2);
        bAnswer3 = (Button) findViewById(R.id.bAnswer3);
        bAnswer4 = (Button) findViewById(R.id.bAnswer4);

        Gson gson = new GsonBuilder().create();
        String jsonVote = getIntent().getStringExtra("vote");
        mVote = gson.fromJson(jsonVote, Vote.class);

        tvTitle.setText(mVote.title);
        tvContent.setText(mVote.content);

        bAnswer1.setText(mVote.answers.get(0).text);
        bAnswer2.setText(mVote.answers.get(1).text);
        bAnswer3.setText(mVote.answers.get(2).text);
        bAnswer4.setText(mVote.answers.get(3).text);

        requestQueue = Volley.newRequestQueue(this);
    }

    public void updateVoteByAnswerId(int id) {
        final VoteUpdateRequest voteUpdateRequest = new VoteUpdateRequest();
        voteUpdateRequest.id = mVote.id;
        voteUpdateRequest.answerId = id;

        Gson gson = new GsonBuilder().create();
        final String jsonVote = gson.toJson(voteUpdateRequest);
        final String url = "http://10.0.2.2:3000";

        StringRequest putRequest = new StringRequest(Request.Method.PUT, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                        mVote.answers.get(voteUpdateRequest.answerId).voted++;
                        mVote.voted++;
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

        putRequest.setRetryPolicy(new DefaultRetryPolicy(
            30000,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        requestQueue.add(putRequest);
    }

    public void onAnswer1Clicked(View view) {
        updateVoteByAnswerId(0);
    }

    public void onAnswer2Clicked(View view) {
        updateVoteByAnswerId(1);
    }

    public void onAnswer3Clicked(View view) {
        updateVoteByAnswerId(2);
    }

    public void onAnswer4Clicked(View view) {
        updateVoteByAnswerId(3);
    }
}
