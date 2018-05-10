package com.mohit.program.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Author @ Mohit Soni on 10-05-2018 05:57 PM.
 */

public class SharedPrefUtility {


    public static final String DATE = "date";
    public static final String MEMBER_ID = "member_id";
    public static final String MEMBER_NAME = "member_name";
    public static final String MEMBER_EMAIL = "member_email";
    public static final String MEMBER_PASSWORD = "member_password";
    public static final String ROLE_ID = "role_id";
    public static final String MEMBER_STATUS = "member_status";

    public static final String TEAM_MEMBER = "TEAM_MEMBER";

    public static final String REMEMBER_ME = "remember_me";

    private final String PREF_LOGIN="login_pref";

    private Context context;

    public SharedPrefUtility(Context context) {
        this.context = context;
    }

    /**
     * to save user data
     */
    /*public void setPrefData(User responseModel) {
        if (responseModel!=null) {
            SharedPreferences preferences;
            preferences=context.getSharedPreferences(PREF_LOGIN, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(MEMBER_ID, responseModel.getMemberId());
            editor.apply();
        }
    }*/


    /**
     * to save member data
     */
    public void setMemberData(Object memberData) {

        if (memberData!=null) {
            SharedPreferences preferences;
            preferences=context.getSharedPreferences(PREF_LOGIN, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(TEAM_MEMBER,(String) memberData);
            editor.apply();
        }
    }

    /**
     * to get preference data
     */
    public String getMemberData()
    {
        SharedPreferences preferences=context.getSharedPreferences(PREF_LOGIN, Context.MODE_PRIVATE);
        return preferences.getString(TEAM_MEMBER, null);
    }


    /**
     * to get preference data
     */
    /*public UserData getPrefData()
    {
        UserData prefModel = new UserData();
        Boolean rememberMe = false;
        SharedPreferences preferences=context.getSharedPreferences(PREF_LOGIN, Context.MODE_PRIVATE);
        prefModel.setMemberId(preferences.getString(MEMBER_ID, null));
        return prefModel;
    }*/

    /**
     * clear all {@link SharedPreferences} data
     */
    public void clearPrefData() {

        SharedPreferences preferences;
        preferences=context.getSharedPreferences(PREF_LOGIN, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString(MEMBER_ID,"");
        editor.putString(MEMBER_NAME,"");
        editor.putString(MEMBER_EMAIL,"");
        editor.putString(MEMBER_PASSWORD,"");
        editor.putString(ROLE_ID,"");
        editor.putString(MEMBER_STATUS,"");

        editor.putBoolean(REMEMBER_ME, false);
        editor.putString(DATE,"");

        editor.apply();
    }

    /**
     * set remember at login
     * @param remember
     */
    public void setRemember(Boolean remember) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_LOGIN, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putBoolean(REMEMBER_ME,remember);
        editor.apply();
    }

    /**
     * reset remember at login
     * @return
     */
    public Boolean getRemember() {
        SharedPreferences preferences=context.getSharedPreferences(PREF_LOGIN, Context.MODE_PRIVATE);
        return preferences.getBoolean(REMEMBER_ME, false);
    }
}
