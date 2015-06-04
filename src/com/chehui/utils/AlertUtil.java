package com.chehui.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

/*
 * 文件名：AlertUtil.java
 * 版权：Copyright 2014-2014 AllCam Tech. Co. Ltd. All Rights Reserved. 
 * 描述�? AlertUtil.java
 * 修改人：xiwen
 * 修改时间�?2014�?10�?16�?
 * 修改内容：新com.myproject.utilse com.bbcam.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

/**
 * TODO 添加类的�?句话�?单描述�??
 * <p>
 * TODO 详细描述
 * <p>
 * </pre>
 * 
 * @author     xiwen
 * @version    V1.0 2014�?10�?16�?
 */
public class AlertUtil
{
    private static AlertDialog.Builder builder;
    
    public static void createDialog(Activity activity,String title,String msg,final AlertDialogCallback callback){
     
        if(builder==null){
            builder=new AlertDialog.Builder(activity);
        }
        
        builder.setTitle(title).setMessage(msg)
            .setNegativeButton("取消", new OnClickListener()
            {
                
                @Override
                public void onClick(DialogInterface arg0, int arg1)
                {
                    if(callback!=null){
                        callback.onCancelCallback();
                    }
                }
            }).setPositiveButton("确定", new OnClickListener()
            {
                
                @Override
                public void onClick(DialogInterface arg0, int arg1)
                {
                    if(callback!=null){
                        callback.onConfirmCallback();
                    }
                    
                }
            });
            builder.create().show();
    }
        
    public interface   AlertDialogCallback{
        
        public void onCancelCallback();
        public void onConfirmCallback();
    }
}

