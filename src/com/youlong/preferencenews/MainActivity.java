package com.youlong.preferencenews;

import java.util.ArrayList;
import java.util.List;

import com.youlong.slidingmenu.view.SlidingMenu;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class MainActivity extends Activity implements OnClickListener{
	
	private ViewPager mViewPager;
	private PagerAdapter mAdapter;
	private List<View> mViews=new ArrayList<View>();
	//tab
	private LinearLayout mTabNews;
	private LinearLayout mTabMsg;
	private LinearLayout mTabMe;
	private LinearLayout mTabMore;
	//img
	private ImageButton mNewsImg;
	private ImageButton mMsgImg;
	private ImageButton mMeImg;
	private ImageButton mMoreImg;
	
	private SlidingMenu mSlingMenu;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
	
		mSlingMenu=(SlidingMenu) findViewById(R.id.slidingmenu);
		initView();
		initEvent();
	}

	public void toggleMenu(View v)
	{
		mSlingMenu.toggle();
	}
	private void initEvent() {
		// TODO Auto-generated method stub
		mTabNews.setOnClickListener(this);
		mTabMsg.setOnClickListener(this);
		mTabMe.setOnClickListener(this);
		mTabMore.setOnClickListener(this);
		
		mViewPager.setOnPageChangeListener(new OnPageChangeListener(){

			@Override
			public void onPageScrollStateChanged(int arg0) {
				
				
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			
				
			}

			@Override
			public void onPageSelected(int arg0) {
				int currentItem = mViewPager.getCurrentItem();
				resetImg();
				switch(currentItem)
				{
				case 0:
					mNewsImg.setImageResource(R.drawable.tab_find_news_pressed);
					break;
				case 1:
					mMsgImg.setImageResource(R.drawable.tab_msg_pressed);
					break;
				case 2:
					mMeImg.setImageResource(R.drawable.tab_address_pressed);
					break;
				case 3:
					mMoreImg.setImageResource(R.drawable.tab_settings_pressed);
					break;
				default:
					break;
				}
				
			}
			
		});
	}

	private void initView() {
		// TODO Auto-generated method stub
		mViewPager=(ViewPager)findViewById(R.id.id_viewpager);
		//tab
		mTabNews=(LinearLayout)findViewById(R.id.id_tab_news);
		mTabMsg=(LinearLayout)findViewById(R.id.id_tab_msg);
		mTabMe=(LinearLayout)findViewById(R.id.id_tab_me);
		mTabMore=(LinearLayout)findViewById(R.id.id_tab_more);
		//img
		mNewsImg=(ImageButton)findViewById(R.id.id_news_img);
		mMsgImg=(ImageButton)findViewById(R.id.id_msg_img);
		mMeImg=(ImageButton)findViewById(R.id.id_me_img);
		mMoreImg=(ImageButton)findViewById(R.id.id_more_img);
		
		LayoutInflater mInflater= LayoutInflater.from(this);
		View tab_01 = mInflater.inflate(R.layout.tab_news, null);
		View tab_02 = mInflater.inflate(R.layout.tab_msg, null);
		View tab_03 = mInflater.inflate(R.layout.tab_me, null);
		View tab_04 = mInflater.inflate(R.layout.tab_more, null);
		
		mViews.add(tab_01);
		mViews.add(tab_02);
		mViews.add(tab_03);
		mViews.add(tab_04);
		
		mAdapter=new PagerAdapter()

		{
			

			@Override
			public void destroyItem(ViewGroup container, int position,
					Object object) {
				// TODO Auto-generated method stub
				container.removeView(mViews.get(position));
			}

			@Override
			public Object instantiateItem(ViewGroup container, int position) {
				// TODO Auto-generated method stub
				View view = mViews.get(position);
				container.addView(view);
				return view;
			}

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return mViews.size();
			}

			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				// TODO Auto-generated method stub
				return arg0==arg1;
			}
			
		};
		
		mViewPager.setAdapter(mAdapter);
		mNewsImg.setImageResource(R.drawable.tab_find_news_pressed);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		resetImg();
		switch(v.getId())
		{
		case R.id.id_tab_news:
			mViewPager.setCurrentItem(0);
			mNewsImg.setImageResource(R.drawable.tab_find_news_pressed);
			break;
		case R.id.id_tab_msg:
			mViewPager.setCurrentItem(1);
			mMsgImg.setImageResource(R.drawable.tab_msg_pressed);
			break;
		case R.id.id_tab_me:
			mViewPager.setCurrentItem(2);
			mMeImg.setImageResource(R.drawable.tab_address_pressed);
			break;
		case R.id.id_tab_more:
			mViewPager.setCurrentItem(3);
			mMoreImg.setImageResource(R.drawable.tab_settings_pressed);
			break;
	    default:
	    	break;
		}
		
	}

/**
 *初始化所有图片normal
 */
	private void resetImg() {
		// TODO Auto-generated method stub		
		mNewsImg.setImageResource(R.drawable.tab_find_news_normal);
		mMsgImg.setImageResource(R.drawable.tab_msg_normal);
		mMeImg.setImageResource(R.drawable.tab_address_normal);
		mMoreImg.setImageResource(R.drawable.tab_settings_normal);
	}
	
}
