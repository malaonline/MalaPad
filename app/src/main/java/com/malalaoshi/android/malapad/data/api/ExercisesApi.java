package com.malalaoshi.android.malapad.data.api;

import com.malalaoshi.android.core.network.NetworkClient;
import com.malalaoshi.android.malapad.data.api.param.AnswersParam;
import com.malalaoshi.android.malapad.data.api.param.QuestionsParam;
import com.malalaoshi.android.malapad.data.api.response.AnswerResponse;
import com.malalaoshi.android.malapad.data.api.response.QuestionsResponse;

import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by kang on 16/12/28.
 */

public class ExercisesApi extends BaseApi {
    private interface ExercisesService {
        @POST("/api/v1/pad/question")
        public Observable<QuestionsResponse> getQuestions(@Header("Pad-Token") String token,@Body QuestionsParam param);

        @POST("/api/v1/pad/submit")
        public Observable<AnswerResponse> submitAnswers(@Header("Pad-Token") String token, @Body AnswersParam param);
    }

    protected static final ExercisesService service  = NetworkClient.retrofit().create(ExercisesService.class);

    public static Observable<QuestionsResponse> loadQuestions(QuestionsParam param){
        return service.getQuestions(getToken(),param);
    }

    public static Observable<AnswerResponse> submitAnswers(AnswersParam param){
        return service.submitAnswers(getToken(),param);
    }

}
