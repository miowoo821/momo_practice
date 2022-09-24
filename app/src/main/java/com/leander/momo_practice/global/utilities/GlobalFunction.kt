package com.leander.momo_practice.global.utilities

import android.content.Context
import com.google.gson.Gson
import java.io.BufferedInputStream


/* Created on 2022/9/24 */

object GlobalFunction {

    /**從Asset裡取得Json字串轉成物件*/
    inline fun <reified R> getResponseFromJsonStringAsset(context: Context?, fileName: String): R {
        context?.assets?.open(fileName).use { infoInputStream ->
            BufferedInputStream(infoInputStream).use { bis ->
                return Gson().fromJson(bis.reader().readText(), R::class.java)
            }
        }
    }
}