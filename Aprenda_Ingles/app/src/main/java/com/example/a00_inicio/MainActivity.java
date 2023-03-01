package com.example.a00_inicio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import com.example.a00_inicio.fragment.BichosFragment;
import com.example.a00_inicio.fragment.NumerosFragment;
import com.example.a00_inicio.fragment.VogaisFragment;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

public class MainActivity extends AppCompatActivity {

    private SmartTabLayout smartTabLayout;
    private ViewPager viewPager;

    private MediaPlayer mediaDog, mediaCat, mediaLion, mediaMonkey, mediaSheep, mediaCow;
    private MediaPlayer mediaOne, mediaTwo, mediaThree, mediaFour, mediaFive, mediaSix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        smartTabLayout = findViewById(R.id.viewPagerTab);
        viewPager      = findViewById(R.id.viewPager);

        // para fazer uma barra entre ActionBar e SmartTabLayout
        getSupportActionBar().setElevation(10);

        // Configurar adapter para as abas
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add("Bichos", BichosFragment.class)
                .add("Números", NumerosFragment.class)
                .add("Vogais", VogaisFragment.class)
                .create());

        viewPager.setAdapter(adapter);
        smartTabLayout.setViewPager(viewPager);

        mediaDog = MediaPlayer.create(getApplicationContext(), R.raw.dog);
        mediaCat = MediaPlayer.create(getApplicationContext(), R.raw.cat);
        mediaLion = MediaPlayer.create(getApplicationContext(), R.raw.lion);
        mediaMonkey = MediaPlayer.create(getApplicationContext(), R.raw.monkey);
        mediaSheep = MediaPlayer.create(getApplicationContext(), R.raw.sheep);
        mediaCow = MediaPlayer.create(getApplicationContext(), R.raw.cow);

        mediaOne = MediaPlayer.create(getApplicationContext(), R.raw.one);
        mediaTwo = MediaPlayer.create(getApplicationContext(), R.raw.two);
        mediaThree = MediaPlayer.create(getApplicationContext(), R.raw.three);
        mediaFour = MediaPlayer.create(getApplicationContext(), R.raw.four);
        mediaFive = MediaPlayer.create(getApplicationContext(), R.raw.five);
        mediaSix = MediaPlayer.create(getApplicationContext(), R.raw.six);
    }

    public void cao (View view) {
        if (mediaDog != null)
            mediaDog.start();
    }

    public void gato (View view) {
        if (mediaCat != null)
            mediaCat.start();
    }

    public void leao (View view) {
        if (mediaLion != null)
            mediaLion.start();
    }

    public void macaco (View view) {
        if (mediaMonkey != null)
            mediaMonkey.start();
    }

    public void ovelha (View view) {
        if (mediaSheep != null)
            mediaSheep.start();
    }

    public void vaca (View view) {
        if (mediaCow != null)
            mediaCow.start();
    }

    public void um (View view) {
        if (mediaOne != null)
            mediaOne.start();
    }

    public void dois (View view) {
        if (mediaTwo != null)
            mediaTwo.start();
    }

    public void tres (View view) {
        if (mediaThree != null)
            mediaThree.start();
    }

    public void quatro (View view) {
        if (mediaFour != null)
            mediaFour.start();
    }

    public void cinco (View view) {
        if (mediaFive != null)
            mediaFive.start();
    }

    public void seis (View view) {
        if (mediaSix != null)
            mediaSix.start();
    }
}