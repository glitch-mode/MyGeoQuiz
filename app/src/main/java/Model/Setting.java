package Model;

import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;

import java.io.Serializable;

public class Setting implements Serializable {
    private boolean mSwitchTrue, mSwitchFalse, mSwitchNext, mSwitchPrevious, mSwitchFirst, mSwitchLast, mSwitchCheat, mSwitchTimeOut;
    private int mRadioGroupQuestionSize, mRadioGroupColor;
    private int mEditTextPositive, mEditTextNegative;

    public boolean isSwitchTrue() {
        return mSwitchTrue;
    }

    public void setSwitchTrue(boolean switchTrue) {
        mSwitchTrue = switchTrue;
    }

    public boolean isSwitchFalse() {
        return mSwitchFalse;
    }

    public void setSwitchFalse(boolean switchFalse) {
        mSwitchFalse = switchFalse;
    }

    public boolean isSwitchNext() {
        return mSwitchNext;
    }

    public void setSwitchNext(boolean switchNext) {
        mSwitchNext = switchNext;
    }

    public boolean isSwitchPrevious() {
        return mSwitchPrevious;
    }

    public void setSwitchPrevious(boolean switchPrevious) {
        mSwitchPrevious = switchPrevious;
    }

    public boolean isSwitchFirst() {
        return mSwitchFirst;
    }

    public void setSwitchFirst(boolean switchFirst) {
        mSwitchFirst = switchFirst;
    }

    public boolean isSwitchLast() {
        return mSwitchLast;
    }

    public void setSwitchLast(boolean switchLast) {
        mSwitchLast = switchLast;
    }

    public boolean isSwitchCheat() {
        return mSwitchCheat;
    }

    public void setSwitchCheat(boolean switchCheat) {
        mSwitchCheat = switchCheat;
    }

    public boolean isSwitchTimeOut() {
        return mSwitchTimeOut;
    }

    public void setSwitchTimeOut(boolean switchTimeOut) {
        mSwitchTimeOut = switchTimeOut;
    }

    public int getRadioGroupQuestionSize() {
        return mRadioGroupQuestionSize;
    }

    public void setRadioGroupQuestionSize(int radioGroupQuestionSize) {
        mRadioGroupQuestionSize = radioGroupQuestionSize;
    }

    public int getRadioGroupColor() {
        return mRadioGroupColor;
    }

    public void setRadioGroupColor(int radioGroupColor) {
        mRadioGroupColor = radioGroupColor;
    }

    public int getEditTextPositive() {
        return mEditTextPositive;
    }

    public void setEditTextPositive(int editTextPositive) {
        mEditTextPositive = editTextPositive;
    }

    public int getEditTextNegative() {
        return mEditTextNegative;
    }

    public void setEditTextNegative(int editTextNegative) {
        mEditTextNegative = editTextNegative;
    }
}
