package com.malalaoshi.android.malapad.classexercises.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.malalaoshi.android.core.utils.MiscUtil;
import com.malalaoshi.android.malapad.R;
import com.malalaoshi.android.malapad.data.entity.Option;
import com.malalaoshi.android.malapad.data.entity.ChoiceQuestion;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

/**
 * Created by kang on 16/12/28.
 */

public class QuestionAdapter extends BaseAdapter implements ViewHolder.OnOptionSelectedListener {
    private List<ChoiceQuestion> choiceQuestionList;
    private Map<String, Option> selectedOptionMap;
    private LayoutInflater layoutInflater;

    public QuestionAdapter(Context context, List<ChoiceQuestion> list){
        layoutInflater = LayoutInflater.from(context);
        this.choiceQuestionList = list;
        selectedOptionMap = new HashMap<>();
    }

    @Override
    public int getCount() {
        return choiceQuestionList.size();
    }

    @Override
    public Object getItem(int position) {
        return choiceQuestionList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null){
            convertView = layoutInflater.inflate(R.layout.question_list_item,parent,false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ChoiceQuestion choiceQuestion = choiceQuestionList.get(position);
        viewHolder.updateData(choiceQuestion, position, this);
        return convertView;
    }

    public Map<String, Option> getSelectedOptions()
    {
        return selectedOptionMap;
    }

    @Override
    public void OnOptionSelected(int position, int optionId) {
        ChoiceQuestion choiceQuestion = choiceQuestionList.get(position);
        List<Option> optionList = choiceQuestion.getOptions();
        for (Option option : optionList){
            option.setSelected(false);
        }
        Option option = optionList.get(optionId);
        option.setSelected(true);
        selectedOptionMap.put(choiceQuestion.getQuestion(), option);
        MiscUtil.toast(choiceQuestion.getQuestion()+"  "+ option.getAnswer());
        notifyDataSetChanged();
    }
}

class ViewHolder implements View.OnClickListener {
    @BindView(R.id.tv_question_number)
    TextView tvQuestionNumber;
    @BindView(R.id.tv_question)
    TextView tvQuestion;
    @BindViews({R.id.tv_option_a,R.id.tv_option_b,R.id.tv_option_c,R.id.tv_option_d})
    List<TextView> tvOptionList;
    private OnOptionSelectedListener listener;

    interface OnOptionSelectedListener {
        void OnOptionSelected(int position, int optionId);
    }

    ViewHolder(View view){
        ButterKnife.bind(this,view);
    }

    void updateData(ChoiceQuestion choiceQuestion, int postion, OnOptionSelectedListener listener){
        this.listener = listener;
        tvQuestionNumber.setText(String.valueOf(postion+1));
        tvQuestion.setText(choiceQuestion.getQuestion());
        for (TextView tvOption: tvOptionList){
            tvOption.setSelected(false);
            tvOption.setText("");
            tvOption.setTag(null);
            tvOption.setOnClickListener(null);
        }
        //赋值
        TextView tvOption;
        Option option;
        List<Option> options = choiceQuestion.getOptions();
        for (int i = 0; options !=null&&i< options.size()&&i< tvOptionList.size(); i++){
            tvOption = tvOptionList.get(i);
            option = options.get(i);
            tvOption.setSelected(option.isSelected());
            tvOption.setText(option.getAnswer());
            tvOption.setTag(postion);
            tvOption.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        int position = (int) v.getTag();
        switch (v.getId()){
            case R.id.tv_option_a:
                setOptionSelected(position,0);
                break;
            case R.id.tv_option_b:
                setOptionSelected(position,1);
                break;
            case R.id.tv_option_c:
                setOptionSelected(position,2);
                break;
            case R.id.tv_option_d:
                setOptionSelected(position,3);
                break;
        }
    }

    private void setOptionSelected(int position, int optionId){
        if (listener!=null){
            listener.OnOptionSelected(position, optionId);
        }
    }

}
