package com.leander.momo_practice.global.utilities

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.leander.momo_practice.global.network.response.AnimalInfoResponse
import com.leander.momo_practice.global.network.response.HouseInfoResponse
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

/* Created on 2022/9/24 */
class GlobalFunctionTest{
    private lateinit var appContext : Context

    @Before
    fun setUp(){
        appContext = InstrumentationRegistry.getInstrumentation().targetContext
    }

    @Test
    fun onGetResponseSuccess(){
        val result = GlobalFunction.getResponseFromJsonStringAsset<HouseInfoResponse>(appContext,"house_info.json")
        assertEquals(HouseInfoResponse::class.java, result::class.java)
    }

    @Test
    fun onGetResponseFail(){
        val result = GlobalFunction.getResponseFromJsonStringAsset<AnimalInfoResponse>(appContext,"animal_info.json")
        assertNotSame(HouseInfoResponse::class.java, result::class.java)
//        assertNull(result)
    }
}