package com.chehui.manage.comm;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Config;

import com.chehui.utils.LogN;
import com.chehui.utils.StringUtils;



public class SharedPreManager extends BaseManager
{
    private static String CONFIG_FILE_NAME = "chehui.config";

    private volatile static SharedPreManager instance = null;

    public static SharedPreManager getInstance()
    {
        if (instance == null)
        {
            instance = new SharedPreManager();
        }

        return instance;
    }

    private SharedPreferences sp = null;

    // 远程服务器IP
    public static final String HOST_IP = "host";

    // 远程服务器端口
    public static final String HOST_PORT = "port";

    // 用户姓名
    public static final String USER_NAME = "name";

    // 用户密码
    public static final String USER_PWD = "pwd";

    private SharedPreManager()
    {
        
    }

    @Override
    public void init(Context context)
    {
        super.init(context);
        sp = context.getSharedPreferences(CONFIG_FILE_NAME, Context.MODE_PRIVATE);
    }
/**
    public SharedPreferences getUserPreference()
    {
        AccountInfo account = RuntimeData.getAccountInfo();
        String userPrefName = account.getUserId();

        if (StringUtils.isEmpty(userPrefName))
        {
            LogN.w(this, "getUserPreference warning userid is empty");
            userPrefName = "user";
        }

        userPrefName = StringUtils.appendStr(userPrefName, "_",
                getString(HOST_IP, Config.DEFAULT_SERVER_IP));

        return appContext.getSharedPreferences(userPrefName, Context.MODE_PRIVATE);
    }
*/
    public String getString(String key, String defaultValue)
    {
        String ret = defaultValue;

        if (isInit && sp != null)
        {
            ret = sp.getString(key, defaultValue);
        }
        else
        {
            LogN.e(this, "init() method should call first or sp is null");
        }

        return ret;
    }

    public int getInt(String key, int defaultValue)
    {
        int ret = defaultValue;

        if (isInit && sp != null)
        {
            ret = sp.getInt(key, defaultValue);
        }
        else
        {
            LogN.e(this, "init() method should call first or sp is null");
        }

        return ret;
    }

    public void setString(String key, String value)
    {
        if (isInit && sp != null)
        {
            Editor ed = sp.edit();
            ed.putString(key, value);
            ed.commit();
        }
        else
        {
            LogN.e(this, "init() method should call first or sp is null");
        }
    }

    public void setInt(String key, int value)
    {
        if (isInit && sp != null)
        {
            Editor ed = sp.edit();
            ed.putInt(key, value);
            ed.commit();
        }
        else
        {
            LogN.e(this, "init() method should call first or sp is null");
        }
    }
}
