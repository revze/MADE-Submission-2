package io.revze.searchmovie.view.main;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import io.revze.searchmovie.R;
import io.revze.searchmovie.view.adapter.CustomViewPagerAdapter;
import io.revze.searchmovie.view.movie.nowplaying.NowPlayingMovieMovieFragment;
import io.revze.searchmovie.view.movie.search.SearchMovieFragment;
import io.revze.searchmovie.view.movie.upcoming.UpcomingMovieMovieFragment;

public class MainActivity extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setOffscreenPageLimit(3);
        CustomViewPagerAdapter adapter = new CustomViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new NowPlayingMovieMovieFragment(), getString(R.string.movie_now_playing_title));
        adapter.addFragment(new UpcomingMovieMovieFragment(), getString(R.string.movie_up_playing_title));
        adapter.addFragment(new SearchMovieFragment(), getString(R.string.movie_search_movie_title));
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.language_setting:
                Intent langSettingIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
                startActivity(langSettingIntent);
        }
        return super.onOptionsItemSelected(item);
    }
}
