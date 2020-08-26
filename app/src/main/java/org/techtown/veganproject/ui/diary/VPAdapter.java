package org.techtown.veganproject.ui.diary;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class VPAdapter extends FragmentPagerAdapter {
    public ArrayList<Fragment> items;
    public  ArrayList<String> itext = new ArrayList<String>();



    public VPAdapter(@NonNull FragmentManager fm) {
        super(fm);
        items= new ArrayList<Fragment>();
        items.add(new blackfastFragment());
        items.add(new lunchFragment());
        items.add(new dinnerFragment());
        items.add(new dessertFragment());

        itext.add("아침");
        itext.add("점심");
        itext.add("저녁");
        itext.add("간식");
    }
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return itext.get(position);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return items.get(position);
    }

    @Override
    public int getCount() {
        return items.size();
    }
}