package br.com.htech.firula8.util;

/**
 * Created by michel.souza on 28/12/2017.
 */

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import br.com.htech.firula8.Modelo.Token;

public class SessionManager {
    // Shared Preferences
    private static SharedPreferences pref;
    public static final String TOKEN = "token";
    private static final String PREF_NAME_TOKEN = "firula.token";

    public static void saveToken(Context c , Token token){
        pref = c.getSharedPreferences(PREF_NAME_TOKEN, c.MODE_PRIVATE);
        final SharedPreferences.Editor editor = pref.edit();
        editor.putString(TOKEN, new Gson().toJson(token));
        editor.commit();
    }

    public static Token getToken(Context c){
        pref = c.getSharedPreferences(PREF_NAME_TOKEN, c.MODE_PRIVATE);
        String json = pref.getString(TOKEN,"");
        if (json.equals("")){
            return new Token();
        }else {
            return new Gson().fromJson(json,Token.class);
        }
    }

}
