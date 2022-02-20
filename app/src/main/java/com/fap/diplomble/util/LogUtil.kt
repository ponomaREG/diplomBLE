package com.fap.diplomble.util

import android.util.Log

fun <T> Collection<T>.log(prefix: String, postfix: (T) -> String) {
    forEach {
        Log.e(prefix, postfix.invoke(it))
    }
}

fun <K, V> Map<K, V>.log(prefix: String, postfix: (K, V) -> String) {
    forEach { (k, v) ->
        Log.e("prefix", postfix.invoke(k, v))
    }
}