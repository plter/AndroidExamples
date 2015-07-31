package com.plter.news;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Gallery;
import android.widget.ListView;

import com.plter.news.data.Data;
import com.plter.news.data.NewsData;
import com.plter.newsframework.R;

@SuppressWarnings("deprecation")
public class MainActivity extends FragmentActivity {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;
	
	private Gallery titleBar;
	private ArrayAdapter<NewsData> titleBarAdapter;
	
	private static MainActivity __mainAty = null;
	
	
	public MainActivity() {
		__mainAty = this;
	}
	
	public static MainActivity getMainActivity(){
		return __mainAty;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
		mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				selectTitle(arg0);
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
		
		titleBar = (Gallery) findViewById(R.id.hsvNewsTitleBar);
		titleBarAdapter = new ArrayAdapter<NewsData>(this, R.layout.news_title, Data.getAllNewsData()){
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				View v = super.getView(position, convertView, parent);
				
				if (getItem(position).isSelected()) {
					v.setBackgroundColor(0xFFCCCCCC);
				}else{
					v.setBackgroundColor(0x00FFFFFF);
				}
				
				return v;
			}
		};
		titleBar.setAdapter(titleBarAdapter);
		titleBar.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				selectTitle(arg2);
				selectContent(arg2);
			}
		});
	}
	
	
	public void selectTitle(int index){
		Data.setSelectedNews(index);
		titleBarAdapter.notifyDataSetChanged();
		titleBar.setSelection(index, true);
	}
	
	public void selectContent(int index){
		mViewPager.setCurrentItem(index,true);
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}



	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			Fragment fragment = new DummySectionFragment();
			Bundle args = new Bundle();
			args.putInt(DummySectionFragment.ARG_SECTION_INDEX, position);
			fragment.setArguments(args);
			return fragment;
		}

		@Override
		public int getCount() {
			return Data.getNewsCatCount();
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return Data.getNewsData(position).getLabel();
		}
	}

	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */
	public static class DummySectionFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_INDEX = "section_number";

		public DummySectionFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main_dummy, container, false);
			ListView lvNews = (ListView) rootView.findViewById(R.id.lvNews);
			lvNews.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, Data.getNewsData(getArguments().getInt(ARG_SECTION_INDEX, 0)).getContent()));
			return rootView;
		}
	}

}
