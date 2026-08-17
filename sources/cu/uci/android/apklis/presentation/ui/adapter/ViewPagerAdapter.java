package cu.uci.android.apklis.presentation.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import cu.uci.android.apklis.presentation.model.AppDetails;
import io.reactivex.subjects.BehaviorSubject;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class ViewPagerAdapter extends FragmentPagerAdapter {
    BehaviorSubject<ArrayList<AppDetails>> behaviorSubject;
    private ArrayList<Fragment> fragments;
    private ArrayList<String> titles;

    public ViewPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        this.behaviorSubject = BehaviorSubject.createDefault(new ArrayList());
        this.fragments = new ArrayList<>();
        this.titles = new ArrayList<>();
    }

    public ViewPagerAdapter(FragmentManager fragmentManager, BehaviorSubject<ArrayList<AppDetails>> behaviorSubject) {
        super(fragmentManager);
        this.behaviorSubject = BehaviorSubject.createDefault(new ArrayList());
        this.fragments = new ArrayList<>();
        this.titles = new ArrayList<>();
        this.behaviorSubject = behaviorSubject;
    }

    public void addFragment(Fragment fragment, String str) {
        this.fragments.add(fragment);
        this.titles.add(str);
    }

    @Override // android.support.v4.view.PagerAdapter
    public int getCount() {
        return this.fragments.size();
    }

    @Override // android.support.v4.app.FragmentPagerAdapter
    public Fragment getItem(int i) {
        return this.fragments.get(i);
    }

    @Override // android.support.v4.view.PagerAdapter
    public CharSequence getPageTitle(int i) {
        return this.titles.get(i);
    }
}
