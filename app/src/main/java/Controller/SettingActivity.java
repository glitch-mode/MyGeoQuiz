package Controller;

import Model.Setting;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;

import com.example.quiz2.R;

import java.io.Serializable;

public class SettingActivity extends AppCompatActivity {
    private Switch mSwitchTrue, mSwitchFalse, mSwitchNext, mSwitchPrevious, mSwitchFirst, mSwitchLast, mSwitchCheat, mSwitchTimeOut;
    private RadioGroup mRadioGroupQuestionSize, mRadioGroupColor;
    private EditText mEditTextPositive, mEditTextNegative;
    private Button mButtonSave;
    private Setting setting = new Setting();
    public static final String EXTRA_SETTING_CLASS = "setting";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        findAllViews();
        setAllViews();
        mButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, QuizActivity.class);
                intent.putExtra(EXTRA_SETTING_CLASS, (Serializable) setting);
                startActivity(intent);
            }
        });

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