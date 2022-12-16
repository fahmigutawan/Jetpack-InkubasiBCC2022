package com.bcc.brain.data.ktor

object HttpRoutes {
    private const val BASE_URL = "https://backend-silomba-production.up.railway.app"

    const val LOGIN = "$BASE_URL/user/login"
    const val REGISTER = "$BASE_URL/user/register"
    const val GET_ALL_LOMBA = "$BASE_URL/all-lomba"
    const val GET_LOMBA_BY_ID = "$BASE_URL/lomba"
}