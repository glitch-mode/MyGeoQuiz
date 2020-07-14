package Controller;

import Model.Setting;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;

import com.example.quiz2.R;

import java.io.Serializable;

public class SettingActivity extends AppCompatActivity {
    private Switch mSwitchTrue, mSwitchFalse, mSwitchNext, mSwitchPrevious, mSwitchFirst, mSwitchLast, mSwitchCheat, mSwitchTimeOut;
    private RadioGroup mRadioGroupQuestionSize, mRadioGroupColor;
    private RadioButton mRadioButtonSmall, mRadioButtonMedium, mRadioButtonLarge, mRadioButtonColorful, mRadioButtonYellow;
    private EditText mEditTextPositive, mEditTextNegative;
    private Button mButtonSave;
    private Setting setting = new Setting();
    public static final String EXTRA_SETTING_CLASS = "setting";
    private static final int SMALL_ID = 14, MEDIUM_ID = 18, LARGE_ID = 26, COLORFUL_ID = 1000, YELLOW_ID = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        findAllViews();

        mButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAllViews();
                Intent intent = new Intent();
                intent.putExtra(EXTRA_SETTING_CLASS, setting);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        Intent intent = getIntent();
        if (intent.getSerializableExtra(QuizActivity.EXTRA_SETTING_OBJECT) != null) {
            setting = (Setting) intent.getSerializableExtra(QuizActivity.EXTRA_SETTING_OBJECT);
        }

        if (savedInstanceState != null) {
            setting = (Setting) savedInstanceState.getSerializable(EXTRA_SETTING_CLASS);
        }

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(EXTRA_SETTING_CLASS, setting);
    }

    public void findAllViews() {
        mSwitchTrue = findViewById(R.id.true_switch);
        mSwitchFalse = findViewById(R.id.false_switch);
        mSwitchNext = findViewById(R.id.next_switch);
        mSwitchPrevious = findViewById(R.id.previous_switch);
        mSwitchFirst = findViewById(R.id.first_switch);
        mSwitchLast = findViewById(R.id.last_switch);
        mSwitchCheat = findViewById(R.id.cheat_switch);
        mSwitchTimeOut = findViewById(R.id.disable_enable_time_out_switch);
        mRadioGroupQuestionSize = findViewById(R.id.question_size_radio_group);
        mRadioGroupColor = findViewById(R.id.color_radio_group);
        mEditTextPositive = findViewById(R.id.positive_edit_text);
        mEditTextNegative = findViewById(R.id.negative_edit_text);
        mButtonSave = findViewById(R.id.save_button);
        mRadioButtonColorful = findViewById(R.id.colorful_radio_button);
        mRadioButtonColorful.setId(COLORFUL_ID);
        mRadioButtonLarge = findViewById(R.id.large_radio_button);
        mRadioButtonLarge.setId(LARGE_ID);
        mRadioButtonMedium = findViewById(R.id.medium_radio_button);
        mRadioButtonMedium.setId(MEDIUM_ID);
        mRadioButtonSmall = findViewById(R.id.small_radio_button);
        mRadioButtonSmall.setId(SMALL_ID);
        mRadioButtonYellow = findViewById(R.id.yellow_radio_button);
        mRadioButtonYellow.setId(YELLOW_ID);
    }

    public void setAllViews() {
        setting.setSwitchTrue(mSwitchTrue.isChecked());
        setting.setSwitchFalse(mSwitchFalse.isChecked());
        setting.setSwitchNext(mSwitchNext.isChecked());
        setting.setSwitchPrevious(mSwitchPrevious.isChecked());
        setting.setSwitchFirst(mSwitchFirst.isChecked());
        setting.setSwitchLast(mSwitchLast.isChecked());
        setting.setSwitchCheat(mSwitchCheat.isChecked());
        setting.setSwitchTimeOut(mSwitchTimeOut.isChecked());
        setting.setEditTextNegative(Integer.parseInt(String.valueOf(mEditTextNegative.getText())));
        setting.setEditTextPositive(Integer.parseInt(String.valueOf(mEditTextPositive.getText())));
        setting.setRadioGroupColor(mRadioGroupColor.getCheckedRadioButtonId());
        setting.setRadioGroupQuestionSize(mRadioGroupQuestionSize.getCheckedRadioButtonId());
    }
}