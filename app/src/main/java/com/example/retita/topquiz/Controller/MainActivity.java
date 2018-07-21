package com.example.retita.topquiz.Controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.retita.topquiz.Model.User;
import com.example.retita.topquiz.R;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private EditText editText;
    private Button button;
    private User user;
    private static final int GAME_ACTIVITY_REQUEST_CODE = 42;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.text_view_id);
        editText = (EditText)findViewById(R.id.edit_tewt_id);
        button = (Button)findViewById(R.id.button_id);

        String firstname = getPreferences(MODE_PRIVATE).getString("firstname", null);

        if(firstname != null){
            textView.setText("Welcome " + firstname);
            editText.setText(firstname);
            button.setEnabled(true);
        }else {
            textView.setText("Welcome");
            button.setEnabled(false);
        }


        user = new User();

        //désactiver le boutton

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                button.setEnabled(charSequence.toString().length() != 0);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user.setFirstName(editText.getText().toString());

                SharedPreferences preferences = getPreferences(MODE_PRIVATE);
                preferences.edit().putString("firstname", user.getFirstName()).apply();

                Intent gameActivity = new Intent(MainActivity.this, GameActivity.class);
                startActivityForResult(gameActivity,GAME_ACTIVITY_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (GAME_ACTIVITY_REQUEST_CODE == requestCode && RESULT_OK == resultCode) {

            // Fetch the score from the Intent

            int score = data.getIntExtra(GameActivity.BUNDLE_EXTRA_SCORE, 0);
            SharedPreferences preferences = getPreferences(MODE_PRIVATE);
            preferences.edit().putInt("score", score).apply();
        }
    }
}
