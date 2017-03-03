package com.etiya.etiyabadgetab;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.etiya.etiyabadgetablib.EtiyaBadgeTab;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Context context;
    private EtiyaBadgeTab etiyaBadgeTab;
    private int[] tabIcons = {
            R.drawable.tab_1_image,
            R.drawable.tab_2_image,
            R.drawable.tab_3_image,
            R.drawable.tab_4_image
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        etiyaBadgeTab = (EtiyaBadgeTab) findViewById(R.id.etiyaBadgeTabs);
        etiyaBadgeTab.setupWithViewPager(viewPager);
        setupTabIcons();
    }

    private void setupTabIcons() {
        etiyaBadgeTab.setSelectedTabIndicatorColor(getResources().getColor(R.color.selectTabColor));
        etiyaBadgeTab.setSelectedTabIndicatorHeight(5);
        // TabLayout.MODE_SCROLLABLE - TabLayout.MODE_FIXED
        etiyaBadgeTab.setTabMode(TabLayout.MODE_SCROLLABLE);
        // TabLayout.GRAVITY_FILL - TabLayout.GRAVITY_CENTER
        etiyaBadgeTab.setTabGravity(TabLayout.GRAVITY_CENTER);
        etiyaBadgeTab.setBackgroundColor(getResources().getColor(R.color.tabBgColor));

        etiyaBadgeTab.selectEtiyaBadgeTab(0)
                .tabTitle("TAB 1")
                .tabTitleColor(R.color.tabTitleColor)
                .tabIcon(tabIcons[0])
                .tabIconToTitle(1)
                .tabIconColor(R.color.tabTitleColor)
                .tabIconDirection("LEFT")
                .tabBadge(true)
                .tabBadgeCount(18)
                .tabBadgeCountMore(true)
                .tabBadgeBgColor(R.color.orange)
                .tabBadgeTextColor(R.color.smoothwhite)
                .tabBadgeStroke(2, R.color.greyblue)
                .tabBadgeCornerRadius(18)
                .createEtiyaBadgeTab();

        etiyaBadgeTab.selectEtiyaBadgeTab(1)
                .tabTitle("TAB 2")
                .tabIconToTitle(3)
                .tabIcon(tabIcons[1])
                .tabIconColor(R.color.tabTitleColor)
                .tabIconDirection("RIGHT")
                .tabBadge(true)
                .tabBadgeBgColor(R.color.tabTitleColor)
                .tabBadgeCornerRadius(10)
                .tabBadgeStroke(3, R.color.white)
                .tabBadgeCount(7)
                .createEtiyaBadgeTab();

        etiyaBadgeTab.selectEtiyaBadgeTab(2)
                .tabIcon(tabIcons[2])
                .tabIconColor(R.color.orange)
                .tabBadge(true)
                .tabBadgeBgColor(R.color.tabTitleColor)
                .tabBadgeTextColor(R.color.greyblue)
                .tabBadgeCornerRadius(2)
                .tabBadgeStroke(3, R.color.greyblue)
                .tabBadgeCount(7)
                .createEtiyaBadgeTab();

        etiyaBadgeTab.selectEtiyaBadgeTab(3)
                .tabTitle("TAB 4")
                .createEtiyaBadgeTab();
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFrag(new SampleFragment(), "TAB 1");
        adapter.addFrag(new SampleFragment(), "TAB 2");
        adapter.addFrag(new SampleFragment(), "TAB 3");
        adapter.addFrag(new SampleFragment(), "TAB 4");

        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
