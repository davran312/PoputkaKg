package poputka.neobis.poputkakg.utils

import android.content.Context
import android.content.SharedPreferences

class Preference {
    private val USER_ID  = "user_id"
    private val TOKEN = "token"
    private val PREFERENCE_NAME  = "neobis.poputka"

    private var mContext:Context? = null
    open val BASE_URL = "asd"
     val REQUEST_TIME_MINUTE: Long = 1

     constructor(context: Context){
         mContext = context
     }
    private fun clearAll(){
        mContext?.getSharedPreferences(PREFERENCE_NAME,Context.MODE_PRIVATE)!!.edit().clear().apply()
    }
  private  fun getPrefs():SharedPreferences{
        return mContext!!.getSharedPreferences(PREFERENCE_NAME,Context.MODE_PRIVATE)
    }
    fun getPrefsEditor():SharedPreferences.Editor{
        return mContext!!.getSharedPreferences(PREFERENCE_NAME,Context.MODE_PRIVATE).edit()
    }
    fun setToken(token:String){
      getPrefsEditor().putString(TOKEN,token).apply()
    }
    fun getToken():String{
        return getPrefs().getString(TOKEN,null)!!
        }
    fun setUserId(userId:Int){
        getPrefsEditor().putInt(USER_ID,userId).apply()
    }
    fun getUserId(){
        getPrefs().getString(USER_ID,null)
    }


 }