package com.malalaoshi.android.malapad.classexercises.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.malalaoshi.android.malapad.R;
import com.malalaoshi.android.malapad.data.entity.Answer;
import com.malalaoshi.android.malapad.data.entity.Question;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kang on 16/12/28.
 */

public class QuestionAdapter extends BaseAdapter {
    List<Question> questionList;
    private LayoutInflater layoutInflater;
    public QuestionAdapter(Context context, List<Question> list){
        layoutInflater = LayoutInflater.from(context);
        this.questionList = list;
    }

    @Override
    public int getCount() {
        return questionList.size();
    }

    @Override
    public Object getItem(int position) {
        return questionList.get(position);
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

        Question question = questionList.get(position);
        viewHolder.updateData(question,position);
        return convertView;
    }

    public List<Question> getSelectedItems()
    {
        return null;
    }

    static class ViewHolder{
        @BindView(R.id.tv_question_number)
        TextView tvQuestionNumber;
        @BindView(R.id.tv_question)
        TextView tvQuestion;
        @BindView(R.id.tv_answer_a)
        TextView tvAnswerA;
        @BindView(R.id.tv_answer_b)
        TextView tvAnswerB;
        @BindView(R.id.tv_answer_c)
        TextView tvAnswerC;
        @BindView(R.id.tv_answer_d)
        TextView tvAnswerD;
        public ViewHolder(View view){
            ButterKnife.bind(this,view);
        }

        public void updateData(Question question, int postion){
            tvQuestionNumber.setText(String.valueOf(postion+1));
            tvQuestion.setText(question.getQuestion());
            List<Answer> answers = question.getAnswers();
            tvAnswerA.setSelected(answers.get(0).isSelected());
            tvAnswerA.setText(answers.get(0).getAnswer());
            tvAnswerB.setSelected(answers.get(1).isSelected());
            tvAnswerB.setText(answers.get(1).getAnswer());
            tvAnswerC.setSelected(answers.get(2).isSelected());
            tvAnswerC.setText(answers.get(2).getAnswer());
            tvAnswerD.setSelected(answers.get(3).isSelected());
            tvAnswerD.setText(answers.get(3).getAnswer());
        }
    }
}

