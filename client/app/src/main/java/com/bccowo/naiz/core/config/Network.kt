package com.bccowo.naiz.core.config

import com.bccowo.naiz.BuildConfig

object Network {
    const val BASE_URL = BuildConfig.API_BASE_URL
    const val BASE_ML_URL = BuildConfig.ML_ENGINE_BASE_URL

    const val ENDPOINT_SIGN_UP = "/sign_up"
    const val ENDPOINT_LOG_IN = "/sign_in?column=email, password, name, id, phone"
    const val ENDPOINT_ALL_CANDI =
        "/u/fetch/temples?select=id, name, description, address, image, longitude, latitude, rating:temple_reviews(rate), total_reliefs, reliefs: temple_relief_connections(id, detail: reliefs(name, description, image, type: relief_types(name)))"
    const val ENDPOINT_SCAN_COUNT = "/u/fetch/user_scans?select=count()"
    const val ENDPOINT_SIMILAR_RELIEF =
        "/u/fetch/relief_types?select=name,similar: reliefs(name,image,id, description, type: relief_types(name))"
    const val ENDPOINT_OTHER_RELIEF =
        "/u/fetch/temples?select=id,name,other:temple_relief_connections(id,relief: reliefs(id,name,image, description))"
    const val ENDPOINT_CHECK_CREDENTIAL = "/u/fetch/users?select=id"
    const val ENDPOINT_GET_QUIZZES = "/u/fetch/quiz_packs"
    const val ENDPOINT_GET_QUIZ_QUESTION =
        "/u/fetch/quiz_questions?select=question, image, options: quiz_options(option, is_true)"
    const val ENDPOINT_SUBMIT_QUIZ = "/u/quiz"
    const val ENDPOINT_SUBMIT_REVIEW = "/u/temple/review"
    const val ENDPOINT_SUBMIT_SCAN = "/u/temple/scan"
    const val ENDPOINT_GET_RELIEF =
        "/u/fetch/temples?select=reliefs: reliefs(id, name, description, image, type: relief_types(name))"
}