package Model;

public class Question {
    private String mQuestionText;
    private boolean mIsAnswer;
    private boolean mIsCheat;
    private Colors mColors;

    public Question(String question, boolean answer, boolean cheat, Colors colors) {
        mQuestionText = question;
        mIsAnswer = answer;
        mIsCheat = cheat;
        mColors = colors;
    }

    public String getQuestionText() {
        return mQuestionText;
    }

    public boolean isAnswer() {
        return mIsAnswer;
    }

    public boolean isCheat() {
        return mIsCheat;
    }

    public Colors getColors() {
        return mColors;
    }
}
