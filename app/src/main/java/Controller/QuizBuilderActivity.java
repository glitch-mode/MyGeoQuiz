package Controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.quiz2.R;

public class QuizBuilderActivity extends AppCompatActivity {
    private EditText mEditTextQuestions;
    private Button mButtonStart;
    public static final String BUNDLE_KEY_QUESTION = "mEditTextQuestions";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_builder);
        findAllViews();
        setOnClickListeners();

        if (savedInstanceState != null) {
            mEditTextQuestions.setText(savedInstanceState.getString(BUNDLE_KEY_QUESTION));
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(BUNDLE_KEY_QUESTION, mEditTextQuestions.getText().toString());
    }

    public void findAllViews() {
        mEditTextQuestions = findViewById(R.id.questions_edit_text);
        mButtonStart = findViewById(R.id.start_button);
    }

    public void setOnClickListeners() {
        mButtonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizBuilderActivity.this, QuizActivity.class);
                intent.putExtra(BUNDLE_KEY_QUESTION, mEditTextQuestions.getText().toString());
                startActivity(intent);
            }
        });
    }
}