package com.android.support;

import android.animation.*;
import android.content.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.util.*;
import android.os.*;
import android.view.*;
import android.text.*;
import android.widget.*;
import com.android.support.Menu;
import android.app.AlertDialog;
import android.R;
import android.net.Uri;
import javax.security.auth.Destroyable;
import java.util.regex.MatchResult;
import android.transition.AutoTransition;
import java.io.UnsupportedEncodingException;

public class Loader 
{
	protected static Context context;
	protected LinearLayout childOfScroll;
	
	public static native void Changes(int feature, int value);

    private static native String[] getFeatures();
	
	public static boolean hide;
    private static native String Icon();
    public static native String setTitleText();
    private static native String setHeadingText();
	public static void Start(final Context context)
	{
        System.loadLibrary("DarkTeam");
        Handler handler = new Handler();
	   	handler.postDelayed(new Runnable() {
                @Override
                public void run() {
					new Loader().initFloating(context);
                }
            }, 3000);
	    } 
      
	public final void initFloating(final Context context)
	{      
		Loader.context = context;
		Menu menu = new Menu(context);
		menu.setWidth(menu.dpi(300));
		menu.setHeight(menu.dpi(240));
		menu.setIconImage(Icon());
		menu.setTitle(setTitleText());
        
        TextView BB = new TextView(context);
        BB.setText(Html.fromHtml(setHeadingText()));
        BB.setTextColor(Color.WHITE);
        BB.setTextSize(13.5f);
        BB.setGravity(Gravity.CENTER);
		menu.getChildOfScroll().addView(BB);
		String[] listFT = getFeatures();
        for (int i = 0; i < listFT.length; i++) {
            final int feature = i;
            String str = listFT[i];
            String[] split = str.split("_");
            if (str.contains("ButtonOnOff_")) {
                menu.ButtonOnOff(feature, split[1], new Menu.ibt() {
						public void OnWrite() {
							Changes(feature, 0);
						}
					});
			} else if (str.contains("Button_")) {
                menu.Button(feature, split[1], new Menu.ibt() {
						public void OnWrite() {
							Changes(feature, 0);
						}
					}); 
			} else if (str.contains("Hide_")) {
                menu.ButtonOnOff(feature, split[1], new Menu.ibt() {
						public void OnWrite() {
							hide = !hide;
						}
					});                        
			} else if (str.contains("Text_")) {
                menu.addText(str.replace("Text_", ""));                                            
			} else if (str.contains("SeekBar_")) {

				menu.SeekBar(feature, split[1], Integer.parseInt(split[2]), Integer.parseInt(split[3]), new Menu.iit() {
						public void OnWrite(int i) {
							Changes(feature, i);
						}
					});
			     }
		      }
	        }
          }
