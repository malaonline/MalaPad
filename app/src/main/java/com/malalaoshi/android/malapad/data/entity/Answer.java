package com.malalaoshi.android.malapad.data.entity;

/**
 * Created by kang on 16/12/28.
 */

public class Answer {
    private String id;
    private String answer;
    private boolean isSelected;

    public Answer(String id, String answer, boolean isSelected) {
        this.id = id;
        this.answer = answer;
        this.isSelected = isSelected;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
