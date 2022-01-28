package com.android.support;

import android.animation.*;
import android.app.*;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.os.*;
import android.view.inputmethod.InputMethodManager;
import com.android.support.*;
import android.text.*;
import android.view.Window.*; 
import android.text.method.*;
import android.graphics.Typeface;
import android.util.*;
import android.view.*;
import android.widget.*;
import android.view.animation.AlphaAnimation; 
import android.view.animation.Animation;
import java.io.*;
import java.util.Objects;

public class Menu
{
	protected int WIDTH,HEIGHT;   
	protected Context context;
	protected boolean isIconVisible;
	protected boolean isMenuVisible;
	protected ImageView iconView,closeView;
	protected FrameLayout parentBox;
	protected LinearLayout menulayout;  
	protected ScrollView scrollItems;    
	
	protected TextView title;

	protected WindowManager wmManager;
	protected WindowManager.LayoutParams wmParams;
	protected LinearLayout headerLayout;

	protected LinearLayout childOfScroll;
	protected LinearLayout infos;
	
	//For Animarions
    static int dur; 
	public static int one; 
	public static int zero;
	static final { 
		dur = 400; 
		zero = 0; 
		one = 1; 
	} 

	public static Animation fadein() { //Closening
		AlphaAnimation alphaAnimation = new AlphaAnimation((float)one, (float)zero); 
		alphaAnimation.setDuration((long)dur); 
		return alphaAnimation; 
	} 

	public static Animation fadeout() { //Opening
		AlphaAnimation alphaAnimation = new AlphaAnimation((float)zero, (float)one); 
		alphaAnimation.setDuration((long)dur); 
		return alphaAnimation; 
	}
	
	public TextView addText(String str) {
        TextView textView = new TextView(context);
        textView.setText(Html.fromHtml("<u><b>" + str + "</b></u>"));
        textView.setTextColor(Color.WHITE);
        textView.setTextSize(12.5f);
        textView.setGravity(3);
        textView.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        textView.setPadding(20, 0, 0, 0);
        getChildOfScroll().addView(textView);
        return textView;
    }
	
	public void SeekBar(final int featNum, final String featName, final int prog, int max, final iit interInt) {
			LinearLayout linearLayout = new LinearLayout(context);
			LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
			linearLayout.setPadding(10, 5, 0, 5);
			linearLayout.setOrientation(LinearLayout.VERTICAL);
			linearLayout.setGravity(17);
			linearLayout.setLayoutParams(layoutParams);

			//Textview
			final TextView textView = new TextView(context);				
		    textView.setText(featName + " : " + prog);
			textView.setTextSize(13.0f);
			textView.setGravity(3);
			textView.setTextColor(Color.WHITE);
			textView.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
			textView.setPadding(5, 0, 0, 0);

	    //Seekbar
        SeekBar SeekBar = new SeekBar(context);
        SeekBar.setMax(max);
		SeekBar.getProgressDrawable().setColorFilter(Color.parseColor("#ffffffff"), PorterDuff.Mode.MULTIPLY);
        SeekBar.getThumb().setColorFilter(Color.parseColor("#ffffffff"), PorterDuff.Mode.MULTIPLY);
			SeekBar.setPadding(25, 10, 35, 10);
			SeekBar.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
			LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
			layoutParams2.bottomMargin = 10;
			SeekBar.setLayoutParams(layoutParams2);
			SeekBar.setProgress(prog);

			final TextView textView2 = textView;
			SeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
					public void onStartTrackingTouch(SeekBar seekBar) {
					}

					public void onStopTrackingTouch(SeekBar seekBar) {
					}
					
					int l;
					public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
						if (l < i) {            
						}
						l = i;					
						interInt.OnWrite(i);
						TextView textView = textView2;			
						textView.setText(featName + " : " + i);
					}
				});
			linearLayout.addView(textView);
			linearLayout.addView(SeekBar);
			getChildOfScroll().addView(linearLayout);
         }
    
	public void ButtonOnOff(final int featNum, String feature, final ibt interfaceBtn) {
		final GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(GradientDrawable.RECTANGLE);
        String str2 = "#ffffffff";
        gradientDrawable.setColor(Color.parseColor(str2));
        gradientDrawable.setStroke(3, Color.parseColor(str2));
        gradientDrawable.setCornerRadius(8.0f);
        final GradientDrawable gradientDrawable2 = new GradientDrawable();
        gradientDrawable2.setShape(GradientDrawable.RECTANGLE);
        gradientDrawable2.setColor(0);
        gradientDrawable2.setStroke(3, Color.parseColor(str2));
        gradientDrawable2.setCornerRadius(8.0f);

        final Button ButtonOnOff = new Button(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.setMargins(7, 5, 7, 5);
        ButtonOnOff.setText(feature);
        ButtonOnOff.setTextColor(Color.WHITE);
        ButtonOnOff.setTextSize(14.5f);
        ButtonOnOff.setAllCaps(false);
        ButtonOnOff.setBackgroundColor(Color.TRANSPARENT);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, dpi(40));
        ButtonOnOff.setPadding(3, 3, 3, 3);
        layoutParams2.bottomMargin = 0;
        ButtonOnOff.setLayoutParams(layoutParams2);
        final String featName = feature;
        ButtonOnOff.setOnClickListener(new View.OnClickListener() {
                private boolean isActive = true;              
                public void onClick(View v) {
                    switch (featNum) {
                         case -1:                                        
                         break;
                    }
                    interfaceBtn.OnWrite();
                    if (isActive) {
                        ButtonOnOff.setText(Html.fromHtml("<b>" + featName + "</b>"));           
                        ButtonOnOff.setTextSize(15.5f);
                        ButtonOnOff.setBackgroundColor(Color.parseColor("#30FFFFFF"));
                        isActive = false;
                        return;
                    }
                    ButtonOnOff.setText(featName);                 
                    ButtonOnOff.setTextSize(15.0f);
                    ButtonOnOff.setBackgroundColor(Color.TRANSPARENT);
                    isActive = true;
                }
            });
		getChildOfScroll().addView(ButtonOnOff);	
    }

	public void Button(final int featNum, String feature, final ibt interfaceBtn) {
		final GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(GradientDrawable.RECTANGLE);
        String str2 = "#ffffffff";
        gradientDrawable.setColor(Color.parseColor(str2));
        gradientDrawable.setStroke(3, Color.parseColor(str2));
        gradientDrawable.setCornerRadius(10.0f);
        final GradientDrawable gradientDrawable2 = new GradientDrawable();
        gradientDrawable2.setShape(GradientDrawable.RECTANGLE);
        gradientDrawable2.setColor(0);
        gradientDrawable2.setStroke(3, Color.parseColor(str2));
        gradientDrawable2.setCornerRadius(10.0f);

        final Button Button = new Button(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.setMargins(7, 5, 7, 5);
        Button.setText(feature);
        Button.setTextColor(Color.WHITE);
        Button.setTextSize(14.5f);
        Button.setAllCaps(false);
        Button.setBackgroundColor(Color.TRANSPARENT);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, dpi(40));
        Button.setPadding(3, 3, 3, 3);
        layoutParams2.bottomMargin = 0;
        Button.setLayoutParams(layoutParams2);
        final String featName = feature;
        Button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    switch (featNum) {
                        case -2:                         
                        break;
                    }
					Button.setBackgroundColor(Color.parseColor("#30FFFFFF"));
					Button.setTextSize(15.5f);
					Button.setText(Html.fromHtml("<b>" + featName + "</b>"));
					final  Handler handler = new Handler();
					handler.postDelayed(new Runnable() {
							@Override
							public void run() {
								Button.setBackgroundColor(Color.TRANSPARENT);
								Button.setText(featName);
								Button.setTextSize(14.5f);
							}
						},75);				
                    interfaceBtn.OnWrite();
                }  
            });
		getChildOfScroll().addView(Button);
    }	

	public LinearLayout getInfosLayout()
	{
		return infos;
	}

	public LinearLayout getChildOfScroll()
	{
		return childOfScroll;
	}

	public LinearLayout getHeaderLayout()
	{
		return headerLayout;
	}

	public LinearLayout getMenuLayout()
	{
		return menulayout;
	}

	public ScrollView getScrollView()
	{
		return scrollItems;
	}

	protected void init(Context context)
	{
		this.context = context;
		isIconVisible = false;
		isMenuVisible = false;
		iconView = new ImageView(context);
		closeView = new ImageView(context);
		title = new TextView(context);

		parentBox = new FrameLayout(context);

		parentBox.setOnTouchListener(handleMotionTouch);
		wmManager = ((Activity)context).getWindowManager();
		int aditionalFlags=0;
		if (Build.VERSION.SDK_INT >= 11)
			aditionalFlags = WindowManager.LayoutParams.FLAG_SPLIT_TOUCH;
		if (Build.VERSION.SDK_INT >=  3)
			aditionalFlags = aditionalFlags | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM;
		    wmParams = new WindowManager.LayoutParams(
			WindowManager.LayoutParams.WRAP_CONTENT,
			WindowManager.LayoutParams.WRAP_CONTENT,
			10,//initialX
			80,//initialY
			WindowManager.LayoutParams.TYPE_APPLICATION,
			WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
			WindowManager.LayoutParams.FLAG_LAYOUT_IN_OVERSCAN |
			WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN |
			aditionalFlags,
			PixelFormat.TRANSPARENT
		);
		wmParams.gravity = Gravity.TOP | Gravity.LEFT;
	}

	public void setIconImage(String Icon)
	{
        byte[] decode = Base64.decode(Icon, 0);
        iconView.setImageBitmap(BitmapFactory.decodeByteArray(decode, 0, decode.length));
	    iconView.setPadding(dpi(7), dpi(7), 0, 0);
        iconView.setImageAlpha(200);
	}
	
	public void setWidth(int px)
	{
		FrameLayout.LayoutParams lp=(FrameLayout.LayoutParams)menulayout.getLayoutParams();
		lp.width = px;
		menulayout.setLayoutParams(lp);
		WIDTH = px;
	}
	
	public void setHeight(int px)
	{
		FrameLayout.LayoutParams lp=(FrameLayout.LayoutParams)menulayout.getLayoutParams();
		lp.height = px;
		menulayout.setLayoutParams(lp);
		HEIGHT = px;
	}
	public int getWidth(int px)
	{
		return WIDTH;
	}

	public int getHeight(int px)
	{
		return HEIGHT;
	}
    
	public void setTitle(String text)
	{
		title.setText(text);
		title.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View p1)
                {
                    showIcon();                
                }
            }); 
	     }
          
	public TextView getTitleTextView()
	{
		return title;
	}

	public void showIcon() {
        iconView.setAnimation(fadeout());//The appearance of the icon
		if (Loader.hide) {
			iconView.setVisibility(View.INVISIBLE);
		} else if (!Loader.hide) {            
			iconView.setVisibility(View.VISIBLE);
		}
		if (!isIconVisible)
		{
			isMenuVisible = false;
			parentBox.removeAllViews();
			parentBox.addView(iconView, dpi(70),dpi(70));
			parentBox.addView(closeView,dpi(20),dpi(20));
			isIconVisible = true;
		}
	}

	public void showMenu() {
		iconView.setAnimation(fadein()); //Remove the icon
		menulayout.setAnimation(fadeout()); //Opening the menu
		if (!isMenuVisible)
		{
			isIconVisible = false;
			parentBox.removeAllViews();
			parentBox.addView(menulayout, WIDTH, HEIGHT);
			isMenuVisible = true;          	
	     }
      }
      
	public int dpi(float dp)
	{
		float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dp * scale + 0.5f);
	}
	
	public Menu(Context context)
	{
		init(context);

		WIDTH = dpi(100);
		HEIGHT = dpi(50);

		final GradientDrawable menuGD = new GradientDrawable();
		menuGD.setColor(Color.parseColor("#9A2D3133"));//#9A2D3133
		menuGD.setCornerRadius(50.0f);
		
		ValueAnimator colorAnim = ObjectAnimator.ofInt(title,"textColor", Color.rgb(0,255,255), Color.rgb(0,128,255), Color.rgb(0,0,255), Color.rgb(255,0,255));
		colorAnim.setDuration(4000);
		colorAnim.setEvaluator(new ArgbEvaluator());
		colorAnim.setRepeatCount(ValueAnimator.INFINITE);
		colorAnim.setRepeatMode(ValueAnimator.RESTART);
        colorAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
				@Override
				public void onAnimationUpdate(ValueAnimator animator) {
					menuGD.setStroke(3,(int) animator.getAnimatedValue());
				}
			});
        colorAnim.start();

		menulayout = new LinearLayout(context);
		menulayout.setOrientation(LinearLayout.VERTICAL);
		{//header
			headerLayout = new LinearLayout(context);

			menulayout.addView(headerLayout, -1, -2);
			//MENU BG COLOR
			menulayout.setBackground(menuGD);
			{
				ImageView minimize=new ImageView(context);
				InputStream istr = null;
				Bitmap bitmap = null;
				AssetManager assetManager = context.getAssets();
				try
				{
					istr = assetManager.open("null");
					bitmap = BitmapFactory.decodeStream(istr);
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
				finally
				{
					minimize.setImageBitmap(bitmap);
				}
				title.setTextColor(Color.RED);
				title.setTextSize(19);
				title.setGravity(Gravity.CENTER_HORIZONTAL);
				{
					infos = new LinearLayout(context);
					infos.setOrientation(LinearLayout.VERTICAL);
					infos.addView(title,-1,-1);
					headerLayout.addView(infos, -1, -1);
					LinearLayout.LayoutParams mnp=(LinearLayout.LayoutParams)infos.getLayoutParams();
					mnp.weight = 10;
					mnp.gravity = Gravity.CENTER;
					infos.setLayoutParams(mnp);
				}
				headerLayout.addView(minimize, dpi(25),dpi(25));
				{
					LinearLayout.LayoutParams mnp = (LinearLayout.LayoutParams)minimize.getLayoutParams();
					mnp.weight = 0;
					mnp.gravity = Gravity.RIGHT;
					minimize.setLayoutParams(mnp);
					minimize.setPadding(0, dpi(10), dpi(10), dpi(10));
					minimize.setOnClickListener(new View.OnClickListener(){

							@Override
							public void onClick(View p1)
							{
								showMenu();
							}
						});
				      }               								               
				    }					 
				  }		       
		               
		scrollItems = new ScrollView(context);

		childOfScroll = new LinearLayout(context);

        scrollItems.setVerticalScrollBarEnabled(false);
        scrollItems.setOverScrollMode(View.OVER_SCROLL_NEVER);
		scrollItems.addView(childOfScroll, -1, -1);
		childOfScroll.setOrientation(LinearLayout.VERTICAL);
		childOfScroll.setBackgroundColor(Color.TRANSPARENT);
		menulayout.addView(scrollItems, -1, -1);
		wmManager.addView(parentBox, wmParams);
		showMenu();
        showIcon();
	}

	View.OnTouchListener handleMotionTouch = new View.OnTouchListener()
	{
		private float initX;          
		private float initY;
		
		private float touchX;
		private float touchY;

		double clock = 0;
		@Override
		public boolean onTouch(View vw, MotionEvent ev)
		{
			switch (ev.getAction())
			{
				case MotionEvent.ACTION_DOWN:

					initX = wmParams.x;
					initY = wmParams.y;
					
					touchX = ev.getRawX();
					touchY = ev.getRawY();
					
					clock = System.currentTimeMillis();
					break;

				case MotionEvent.ACTION_MOVE:
					
					wmParams.x = (int)initX + (int)(ev.getRawX() - touchX);
					wmParams.y = (int)initY + (int)(ev.getRawY() - touchY);

					wmManager.updateViewLayout(vw, wmParams);
					break;

				case MotionEvent.ACTION_UP:
					if (isIconVisible && (System.currentTimeMillis() < (clock + 200)))
					{
						showMenu();
					}
					break;
			}
			return true;
		}
	};
	public static interface ibt {
        void OnWrite();
    }
	
	public static interface iit {
        void OnWrite(int i);
    }
}
