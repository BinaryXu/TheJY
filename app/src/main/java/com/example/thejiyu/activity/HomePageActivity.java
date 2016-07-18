package com.example.thejiyu.activity;

import android.app.ActionBar;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.thejiyu.R;
import com.example.thejiyu.fragment.FirstFragment;
import com.example.thejiyu.fragment.SecondFragment;
import com.example.thejiyu.fragment.ThirdFragment;
import com.example.thejiyu.view.ChangeIconView;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;
import java.util.List;

public class HomePageActivity extends FragmentActivity implements View.OnClickListener,ViewPager.OnPageChangeListener{

    private ViewPager viewPager;
    private List<Fragment> mFrag = new ArrayList<>();
    private String[] mTitles = new String[]{
            "First Fragment","Second Fragment","Third Fragment"
    };
    private ChangeIconView one,two,three;
    private List<ChangeIconView> mTabIndicators = new ArrayList<>();
    private SlidingMenu menu;
    private FragmentPagerAdapter mAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        //初始化view
        initView();
        //定义fragment
        initDatas();
        //注册监听
        initEvent();
        viewPager.setAdapter(mAdapter);
        //左侧菜单
        initMenu();
    }


    private void initMenu() {
        menu = new SlidingMenu(this);
        menu.setMode(SlidingMenu.LEFT);
        //设置触屏模式
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setShadowWidthRes(R.dimen.shadow_width);
        //设置滑动菜单视图宽度
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        //设置渐入渐出效果值
        menu.setFadeDegree(0.35f);

        menu.attachToActivity(this,SlidingMenu.SLIDING_WINDOW);

        //为侧滑菜单设置布局
        menu.setMenu(R.layout.left_menu);
    }

    private void initEvent() {
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        viewPager.setOnPageChangeListener(this);
    }

    private void initDatas() {
//        for(String title : mTitles){
//            TabFragment tabFragment = new TabFragment();
//            Bundle bundle = new Bundle();
//            bundle.putString(TabFragment.ARG_PARAM1,title);
//            tabFragment.setArguments(bundle);
//            mFrag.add(tabFragment);
//        }
        //添加fragment到activity中
        FirstFragment firstFragment = new FirstFragment();
        SecondFragment secondFragment = new SecondFragment();
        ThirdFragment thirdFragment = new ThirdFragment();

        mFrag.add(firstFragment);
        mFrag.add(secondFragment);
        mFrag.add(thirdFragment);

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFrag.get(position);
            }

            @Override
            public int getCount() {
                return  mFrag.size();
            }
        };
    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.id_viewPager);

        one = (ChangeIconView) findViewById(R.id.id_indicator_one);
        mTabIndicators.add(one);
        two = (ChangeIconView) findViewById(R.id.id_indicator_two);
        mTabIndicators.add(two);
        three = (ChangeIconView) findViewById(R.id.id_indicator_three);
        mTabIndicators.add(three);

        one.setIconAlpha(1.0f);


    }

    @Override
    public void onClick(View v) {
        tabClick(v);

    }

    private void tabClick(View v) {
        resetOtherTabs();
        switch (v.getId()){
            case R.id.id_indicator_one:
                mTabIndicators.get(0).setIconAlpha(1.0f);
                viewPager.setCurrentItem(0, false);
                break;
            case R.id.id_indicator_two:
                mTabIndicators.get(1).setIconAlpha(1.0f);
                viewPager.setCurrentItem(1, false);
                break;
            case R.id.id_indicator_three:
                mTabIndicators.get(2).setIconAlpha(1.0f);
                viewPager.setCurrentItem(2,false);
                break;
        }
    }

    private void resetOtherTabs() {
        for(int i = 0; i< mTabIndicators.size();i++){
            mTabIndicators.get(i).setIconAlpha(0);
        }

    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if(positionOffset > 0){
            ChangeIconView left = mTabIndicators.get(position);
            ChangeIconView right = mTabIndicators.get(position+1);

            left.setIconAlpha(1 - positionOffset);
            right.setIconAlpha(positionOffset);
        }
    }
    @Override
    public void onPageSelected(int position) {
        switch (position){
            case 0:
                menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
                break;
            default:
                menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
                break;
        }
    }
    @Override
    public void onPageScrollStateChanged(int state) {
    }
    public class ImageViewPager  extends ViewPager {

        public ImageViewPager(Context context) {
            super(context);
        }

        public ImageViewPager(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        @Override
        public boolean onInterceptTouchEvent(MotionEvent ev) {
            return super.onInterceptTouchEvent(ev);
        }

        @Override
        public boolean dispatchTouchEvent(MotionEvent ev) {
            return super.dispatchTouchEvent(ev);
        }

        @Override
        public boolean onTouchEvent(MotionEvent ev) {
            return super.onTouchEvent(ev);
        }
    }
}
