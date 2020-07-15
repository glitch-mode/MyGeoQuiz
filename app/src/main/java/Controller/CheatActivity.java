package Controller;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.quiz2.R;

public class CheatActivity extends AppCompatActivity {

    private Button mButtonCheat;
    private boolean[] mIsCheatUsed;
    public static final String BUNDLE_KEY_CURRENT_INDEX = "mCurrentIndex";
    public static final String BUNDLE_KEY_ANSWER = "mAnswer";
    public static final String BUNDLE_KEY_IS_CHEAT_USED = "mISCheatUsed";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat2);
        mButtonCheat = findViewById(R.id.cheat_yes_button);
        final Intent intent = getIntent();
        mIsCheatUsed = intent.getBooleanArrayExtra(BUNDLE_KEY_IS_CHEAT_USED);
        mButtonCheat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIsCheatUsed[intent.getIntExtra(BUNDLE_KEY_CURRENT_INDEX, 0)] = true;
                Toast toast = Toast.makeText(CheatActivity.this,
                        String.valueOf(intent.getBooleanExtra(BUNDLE_KEY_ANSWER, true))
                        , Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        saveActivity();

    }

    public void saveActivity() {
        Intent intent1 = new Intent(CheatActivity.this, QuizActivity.class);
        setResult(RESULT_OK, intent1);
        intent1.putExtra(BUNDLE_KEY_IS_CHEAT_USED, mIsCheatUsed);
    }
}