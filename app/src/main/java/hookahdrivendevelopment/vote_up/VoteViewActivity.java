package hookahdrivendevelopment.vote_up;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.w3c.dom.Text;

import hookahdrivendevelopment.vote_up.types.Vote;

public class VoteViewActivity extends AppCompatActivity {

    private Vote mVote;

    private TextView tvTitle;
    private TextView tvAuthor;
    private TextView tvContent;

    private Button bAnswer1;
    private Button bAnswer2;
    private Button bAnswer3;
    private Button bAnswer4;

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
    }
}
