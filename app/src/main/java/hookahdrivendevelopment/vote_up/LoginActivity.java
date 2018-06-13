package hookahdrivendevelopment.vote_up;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class LoginActivity extends AppCompatActivity {

    private EditText userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userName = findViewById(R.id.userName);
    }

    public void next(View view) {
        Intent myIntent = new Intent(LoginActivity.this, MainActivity.class);
        myIntent.putExtra("user", userName.getText());
        LoginActivity.this.startActivity(myIntent);
    }
}

