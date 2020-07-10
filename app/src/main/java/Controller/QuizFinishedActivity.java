package Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.quiz2.R;

import org.w3c.dom.Text;

public class QuizFinishedActivity extends AppCompatActivity {
    private TextView mTextViewScore;
    public static final String BUNDLE_KEY_SCORE = "mScore";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_finished);
        mTextViewScore = findViewById(R.id.score_text_view);
        Intent intent = getIntent();
        mTextViewScore.setText(String.valueOf(intent.getIntExtra(BUNDLE_KEY_SCORE, 0)));

    }
}