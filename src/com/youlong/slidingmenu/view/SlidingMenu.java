package com.youlong.slidingmenu.view;

import com.youlong.preferencenews.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

public class SlidingMenu extends HorizontalScrollView {
	
	
	private LinearLayout mWapper;
	private ViewGroup mMenu;
	private ViewGroup mContent;
	private int mScreenWidth;
	private int mMenuRightPadding = 50;
	private int mMenuWidth;
	private boolean once;
	private boolean isOpen;
	/**
	 * δʹ���Զ�������ʱ����
	 */
	public SlidingMenu(Context context, AttributeSet attrs) {
		
		this(context,attrs,0);

	}
	/**
	 *��ʹ���Զ�������ʱʹ�� 
	 */
	public SlidingMenu(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		
		//��ȡ�����Զ�������	
		TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
				R.styleable.SlidingMenu, defStyle, 0);
		
		int n = a.getIndexCount();
		for(int i=0;i<n;i++)
		{
			int attr = a.getIndex(i);
			switch(attr)
			{
			case R.styleable.SlidingMenu_rightPadding:
				mMenuRightPadding = a.getDimensionPixelSize(attr,
						(int) TypedValue.applyDimension(TypedValue.
								COMPLEX_UNIT_DIP, 50, context
								.getResources().getDisplayMetrics()));
				break;
			default:
				break;
			}
		}
		a.recycle();
		WindowManager wm = (WindowManager) context.getSystemService(context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);
		
		mScreenWidth = outMetrics.widthPixels;
		
	}

	public SlidingMenu(Context context) {
		this(context,null);
		// TODO Auto-generated constructor stub
	}

	/**
	 *������view��͸ߣ������Լ��Ŀ�� 
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		if(!once)
		{
			mWapper = (LinearLayout) getChildAt(0);
			mMenu = (ViewGroup) mWapper.getChildAt(0);
			mContent = (ViewGroup) mWapper.getChildAt(1);			
			mMenuWidth=mMenu.getLayoutParams().width=mScreenWidth-mMenuRightPadding;
			mContent.getLayoutParams().width=mScreenWidth;	
			once = true;
		}
			
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	/**
	 *�������ƫ��������menu���� 
	 */
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
	
		super.onLayout(changed, l, t, r, b);
		if(changed)
		{
			this.scrollTo(mMenuWidth, 0);
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		int action = ev.getAction();
		switch(action)
		{
		case MotionEvent.ACTION_UP:
			int scrollX = getScrollX();
			if(scrollX > mScreenWidth/2)
			{
				this.smoothScrollTo(mMenuWidth, 0);
				isOpen = false;
			}else{
				this.smoothScrollTo(0, 0);
				isOpen = true;
			}
			return true;
			
		}
		return super.onTouchEvent(ev);
	}
	
	public void onOpenMenu()
	{
		if(isOpen)return;
		this.smoothScrollTo(0, 0);
		isOpen = true;
	}
	
	public void onCloseMenu()
	{
		if(!isOpen)return;
		this.smoothScrollTo(mMenuWidth, 0);
		isOpen = false;
	}
	
	public void toggle()
	{
		if(isOpen)
			onCloseMenu();
		else
			onOpenMenu();
	}
	
	
	
	
}
