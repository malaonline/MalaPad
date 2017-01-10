package com.malalaoshi.android.malapad.data.api;

import com.malalaoshi.android.core.network.NetworkClient;
import com.malalaoshi.android.malapad.data.api.param.QuestionsParam;
import com.malalaoshi.android.malapad.data.api.response.QuestionsResponse;
import com.malalaoshi.android.malapad.data.entity.ChoiceQuestion;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by kang on 16/12/28.
 */

public class ExercisesApi {
    private interface ExercisesService {
        @POST("/api/v1/pad/question")
        public Observable<QuestionsResponse> getQuestions(@Body QuestionsParam param);

        @POST("/api/v1/pad/answer")
        public Observable<List<ChoiceQuestion>> submitAnswers(@Body QuestionsParam param);
    }

    protected static final ExercisesService service  = NetworkClient.retrofit().create(ExercisesService.class);

    public static Observable<QuestionsResponse> loadQuestions(QuestionsParam param){
        return service.getQuestions(param);
    }

    public static Observable<List<ChoiceQuestion>> submitAnswers(QuestionsParam param){
        return service.submitAnswers(param);
    }

}
