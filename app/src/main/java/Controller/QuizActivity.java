package Controller;

import Model.Colors;
import Model.Question;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quiz2.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class QuizActivity extends AppCompatActivity {
    private ImageButton mButtonFirst, mButtonLast;
    private Button mButtonPrevious, mButtonNext, mButtonTrue, mButtonFalse, mButtonReset, mButtonCheat, mButtonSetting;
    private TextView mTextViewQuestion, mTextViewScore, mTimerTextView;
    private List<Question> mQuestionBank = new ArrayList<>();
    private int mIntTimer, mCurrentIndex = 0, mScore = 0;
    private boolean[] mIsCheatUsed;
    private boolean[] mIsQuestionAnswered;
    private CountDownTimer mCountDownTimer;
    private long mCountDownRemaining = 0;
    private boolean mAnswer;
    public static final String BUNDLE_KEY_CURRENT_INDEX = "mCurrentIndex";
    public static final String BUNDLE_KEY_IS_ANSWERED_ARRAY = "mISQuestionAnswered";
    public static final String BUNDLE_KEY_IS_CHEAT_USED = "mISCheatUsed";
    public static final String BUNDLE_KEY_SCORE = "mScore";
    public static final String BUNDLE_KEY_QUESTION = "mEditTextQuestions";
    public static final String BUNDLE_KEY_COUNT_DOWN_REMAINING_LONG = "mCountDownRemaining";
    public static final String BUNDLE_KEY_ANSWER = "mAnswer";
    public static final int REQUEST_CODE_CHEAT_ACTIVITY = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        parseQuestions(intent.getStringExtra(QuizActivity.BUNDLE_KEY_QUESTION));
        mIsQuestionAnswered = new boolean[mQuestionBank.size()];
        mIsCheatUsed = new boolean[mQuestionBank.size()];
        timer(mIntTimer * 1000);
        findAllViews();
        setOnClickListeners();
        updateQuestions();
        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(BUNDLE_KEY_CURRENT_INDEX);
            mIsQuestionAnswered = savedInstanceState.getBooleanArray(BUNDLE_KEY_IS_ANSWERED_ARRAY);
            mScore = savedInstanceState.getInt(BUNDLE_KEY_SCORE);
            mCountDownRemaining = savedInstanceState.getLong(BUNDLE_KEY_COUNT_DOWN_REMAINING_LONG);
            timer(mCountDownRemaining);
            updateQuestions();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        timer(mCountDownRemaining);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mCountDownTimer.cancel();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(BUNDLE_KEY_CURRENT_INDEX, mCurrentIndex);
        outState.putBooleanArray(BUNDLE_KEY_IS_ANSWERED_ARRAY, mIsQuestionAnswered);
        outState.putInt(BUNDLE_KEY_SCORE, mScore);
        outState.putLong(BUNDLE_KEY_COUNT_DOWN_REMAINING_LONG, mCountDownRemaining);
        mCountDownTimer.cancel();
    }

    public void timer(long millisInFuture) {
        mCountDownTimer = new CountDownTimer(millisInFuture, 1000) {

            public void onTick(long millisUntilFinished) {
                mTimerTextView.setText(String.valueOf(millisUntilFinished / 1000));
                mCountDownRemaining = millisUntilFinished;
            }

            public void onFinish() {
                if (mCountDownRemaining == 1) finished();
            }
        }.start();
    }


    public void findAllViews() {
        mButtonCheat = findViewById(R.id.cheat_button);
        mButtonFalse = findViewById(R.id.false_button);
        mButtonFirst = findViewById(R.id.left_arrow_image_button);
        mButtonLast = findViewById(R.id.right_arrow_image_button);
        mButtonNext = findViewById(R.id.next_button);
        mButtonPrevious = findViewById(R.id.previous_button);
        mButtonTrue = findViewById(R.id.true_button);
        mButtonReset = findViewById(R.id.reset_button);
        mTextViewQuestion = findViewById(R.id.question_text_view);
        mTextViewScore = findViewById(R.id.score_int_text_view);
        mTimerTextView = findViewById(R.id.timer_text_view);
    }

    public void updateQuestions() {
        mAnswer = mQuestionBank.get(mCurrentIndex).isAnswer();
        HashMap<Colors, Integer> colorCodesHashMap = new HashMap<>();
        colorCodesHashMap.put(Colors.BLACK, Color.BLACK);
        colorCodesHashMap.put(Colors.BLUE, Color.BLUE);
        colorCodesHashMap.put(Colors.GREEN, Color.GREEN);
        colorCodesHashMap.put(Colors.RED, Color.RED);
        mTextViewScore.setText(String.valueOf(mScore));
        mTextViewQuestion.setTextColor(colorCodesHashMap.get(mQuestionBank.get(mCurrentIndex).getColors()));
        checkAlreadyAnswered();
        mTextViewQuestion.setText(mQuestionBank.get(mCurrentIndex).getQuestionText());
        checkFinish();
    }

    public void checkAlreadyAnswered() {
        if (mIsQuestionAnswered[mCurrentIndex]) {
            mButtonTrue.setEnabled(false);
            mButtonFalse.setEnabled(false);
        } else {
            mButtonTrue.setEnabled(true);
            mButtonFalse.setEnabled(true);
        }
    }

    public void checkFinish() {
        for (boolean b : mIsQuestionAnswered) {
            if (!b) return;
        }
        finished();
    }

    public void finished() {
        Intent intent = new Intent(QuizActivity.this, QuizFinishedActivity.class);
        intent.putExtra(BUNDLE_KEY_SCORE, mScore);
        startActivity(intent);
    }

    public void checkAnswer(boolean b) {
        Toast toast = Toast.makeText(QuizActivity.this, String.valueOf(b == mQuestionBank.get(mCurrentIndex).isAnswer()), Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM, 0, 0);
        toast.show();
        if (!mIsCheatUsed[mCurrentIndex]) {
            if (b == mQuestionBank.get(mCurrentIndex).isAnswer())
                mScore++;
            else mScore--;
        }
    }

    public void setOnClickListeners() {
        mButtonTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIsQuestionAnswered[mCurrentIndex] = true;
                checkAnswer(true);
                updateQuestions();
            }
        });

        mButtonFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIsQuestionAnswered[mCurrentIndex] = true;
                checkAnswer(false);
                updateQuestions();
            }
        });

        mButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.size();
                updateQuestions();
            }
        });

        mButtonPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + mQuestionBank.size() - 1) % mQuestionBank.size();
                updateQuestions();
            }

        });
        mButtonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = 0;
                updateQuestions();
            }
        });

        mButtonLast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = mQuestionBank.size() - 1;
                updateQuestions();
            }
        });

        mButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = 0;
                mScore = 0;
                mIsCheatUsed = new boolean[mQuestionBank.size()];
                mIsQuestionAnswered = new boolean[mQuestionBank.size()];
                updateQuestions();
            }
        });

        mButtonCheat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizActivity.this, CheatActivity.class);
                intent.putExtra(BUNDLE_KEY_CURRENT_INDEX, mCurrentIndex);
                intent.putExtra(BUNDLE_KEY_ANSWER, mAnswer);
                intent.putExtra(BUNDLE_KEY_IS_CHEAT_USED, mIsCheatUsed);
                startActivityForResult(intent, REQUEST_CODE_CHEAT_ACTIVITY);
            }
        });

        mButtonSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizActivity.this, SettingActivity.class);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != RESULT_OK || data == null)
            return;

        //result backed from cheate
        if (requestCode == REQUEST_CODE_CHEAT_ACTIVITY) {
            mIsCheatUsed = data.getBooleanArrayExtra(CheatActivity.BUNDLE_KEY_IS_CHEAT_USED);
        }
    }


    private void parseQuestions(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        //find and remove timer
        mIntTimer = Integer.parseInt(stringBuilder.substring(
                stringBuilder.lastIndexOf(", ") + 3, stringBuilder.length() - 1));

        stringBuilder.delete(stringBuilder.lastIndexOf(",") - 2, stringBuilder.length() - 1);
        //splitting the string into questions
        String[] spliced = stringBuilder.toString().split("]");
        //splitting parts of questions and make object from them
        for (int i = 0; i < spliced.length - 1; i++) {
            String[] questionParts = spliced[i].split(", ");
            String q = spliced[i].substring(spliced[i].indexOf("“") + 1, spliced[i].indexOf("”"));
            boolean a, ch;
            a = questionParts[1].substring(1, questionParts[1].length() - 1).equals("true");
            ch = questionParts[2].substring(1, questionParts[2].length() - 1).equals("true");
            //using Enum.valueOf
            //checking if color is valid
            try {
                Colors co = Enum.valueOf(Colors.class, questionParts[3].substring(1, questionParts[3].indexOf("}")).toUpperCase());
                mQuestionBank.add(new Question(q, a, ch, co));
            } catch (IllegalArgumentException l) {
                Toast toast = Toast.makeText(QuizActivity.this, "No enum const class", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.BOTTOM, 0, 0);
                toast.show();
            }
        }
    }
}