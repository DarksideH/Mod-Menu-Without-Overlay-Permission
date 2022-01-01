package com.android.support;

import android.animation.*;
import android.app.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.os.*;
import android.util.*;
import android.view.*;
import android.widget.*;
import com.android.support.*;

import android.view.View.*;
import com.android.support.*;

public class ActivityMain extends Activity  
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
		Main.Start(this);
    }
}
