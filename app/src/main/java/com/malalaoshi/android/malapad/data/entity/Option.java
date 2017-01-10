package com.malalaoshi.android.malapad.data.entity;

import com.google.gson.annotations.SerializedName;
import com.malalaoshi.android.core.entity.BaseEntity;

/**
 * Created by kang on 16/12/28.
 */

public class Option extends BaseEntity{
    @SerializedName("text")
    private String answer;
    private boolean isSelected;

    public Option() {
    }

    public Option(Long id, String answer) {
        super(id, null);
        this.answer = answer;
    }

    public Option(Long id, String answer, boolean isSelected) {
        super(id, null);
        this.answer = answer;
        this.isSelected = isSelected;
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
