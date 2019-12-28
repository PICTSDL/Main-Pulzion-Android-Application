package com.pasc.pulzion19;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class EventAdapter extends FragmentStatePagerAdapter {
    int counter ;
    public EventAdapter(FragmentManager fm,int counter) {
        super(fm);
        this.counter=counter;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                TechEvents techEvents=new TechEvents();
                return techEvents;
            case 1:
                NonTechEvents nonTechEvents=new NonTechEvents();
                return nonTechEvents;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {

        return counter;
    }
}
