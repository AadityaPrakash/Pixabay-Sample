package com.aadi.pixabaysample.toolkit

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast

class Utils {

    companion object {
        fun hideKeyboard(context: Context, view: View){
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0)
        }

        fun isValidQuery(mContext: Context, query:String): Boolean {
            if (query.isBlank()) {
                Toast.makeText(mContext, "Enter input keyword for search!", Toast.LENGTH_SHORT).show()
                return false
            }
            return true
        }
    }
}