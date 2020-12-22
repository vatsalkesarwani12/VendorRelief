package com.CodeNaroNa.vendor.relief.Deprecated.Model;

@Deprecated
public class PrecautionData {
    String ques,answer;

    public PrecautionData(String ques, String answer) {
        this.ques = ques;
        this.answer = answer;
    }

    public String getQues() {
        return ques;
    }

    public void setQues(String ques) {
        this.ques = ques;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
