package com.malalaoshi.android.malapad.data.api;

import com.malalaoshi.android.core.network.NetworkClient;
import com.malalaoshi.android.malapad.data.api.param.ExercisesParam;
import com.malalaoshi.android.malapad.data.entity.Question;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by kang on 16/12/28.
 */

public class ExercisesApi {
    private interface ExercisesService {
        @POST("/api/v1/sms")
        public Observable<List<Question>> submitAnswers(@Body ExercisesParam param);
    }

    protected static final ExercisesService service  = NetworkClient.retrofit().create(ExercisesService.class);

    public static Observable<List<Question>> login(ExercisesParam param){
        return service.submitAnswers(param);
    }

}
