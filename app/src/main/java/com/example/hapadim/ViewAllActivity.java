package com.example.hapadim;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.hapadim.adapters.ViewAllAdapter;
import com.example.hapadim.models.JsonEndPoint;
import com.example.hapadim.models.Place;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.Types.BoomType;
import com.nightonke.boommenu.Types.ButtonType;
import com.nightonke.boommenu.Types.PlaceType;
import com.nightonke.boommenu.Util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by NesadaKoca on 2/28/2017.
 */

public class ViewAllActivity extends AppCompatActivity
        implements
        BoomMenuButton.OnSubButtonClickListener,
        BoomMenuButton.AnimatorListener,
        View.OnClickListener {

    private BoomMenuButton boomSort;
    private LinearLayoutManager linearLayoutManager;
    private View customView;
    private boolean isInit = false;

    private RecyclerView viewAllRV;
    private ViewAllAdapter viewAllAdapter;
    private Bundle bundle;
    String category;
    private List<Place> allPlaces;

    private List<Place> selectedList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);

        setupToolbar();

        List<Place> ld = JsonEndPoint.getInstance().getLongDistance();
        List<Place> monuments = JsonEndPoint.getInstance().getMonuments();
        List<Place> mountains = JsonEndPoint.getInstance().getMountains();


        allPlaces = new ArrayList<>();
        allPlaces.addAll(ld);
        allPlaces.addAll(monuments);
        allPlaces.addAll(mountains);

        viewAllRV = (RecyclerView) findViewById(R.id.rv_view_all);
        bundle = getIntent().getExtras();
        category = bundle.getString("category_key");

        if (category.equals("Monument")) {
            selectedList = monuments;
            viewAllAdapter = new ViewAllAdapter(selectedList);

        } else if (category.equals("LongDistance")) {

            selectedList = ld;
            viewAllAdapter = new ViewAllAdapter(selectedList);

        } else if (category.equals("Mountain")) {

            selectedList = mountains;
            viewAllAdapter = new ViewAllAdapter(selectedList);

        } else {

            selectedList = allPlaces;
            viewAllAdapter = new ViewAllAdapter(selectedList);
        }

        setupViewAllAdapter();

        toolbarTransparent();

    }
    private void toolbarTransparent() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    public void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(true);
        LayoutInflater mInflater = LayoutInflater.from(this);
        customView = mInflater.inflate(R.layout.custom_actionbar, null);
        mActionBar.setCustomView(customView);
        mActionBar.setDisplayShowCustomEnabled(true);
        ((Toolbar) customView.getParent()).setContentInsetsAbsolute(0, 0);
        boomSort = (BoomMenuButton) customView.findViewById(R.id.boom_sort);
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if (!isInit) {
            initInfoBoom();
        }
        isInit = true;
    }

    private void initInfoBoom() {

        Drawable[] drawables = new Drawable[4];
        int[] drawablesResource = new int[]{
                R.drawable.sort09,
                R.drawable.sort90,
                R.drawable.sortaz,
                R.drawable.sortza
        };
        for (int i = 0; i < drawables.length; i++)
            drawables[i] = ContextCompat.getDrawable(this, drawablesResource[i]);

        int[][] colors = new int[4][3];
        for (int i = 0; i < drawables.length; i++) {
            colors[i][2] = ContextCompat.getColor(this, R.color.black);
            colors[i][1] = ContextCompat.getColor(this, R.color.bg_screen6);
            colors[i][0] = Util.getInstance().getPressedColor(colors[i][2]);
        }

        new BoomMenuButton.Builder()
                .subButtons(drawables, colors, new String[]{"Sort Feet Ascending", "Sort Feet Descending", "Sort Name Ascending", "Sort Name Descending"})
                .button(ButtonType.HAM)
                .boom(BoomType.PARABOLA_2)
                .place(PlaceType.HAM_4_1)
                .subButtonsShadow(Util.getInstance().dp2px(2), Util.getInstance().dp2px(2))
                .onSubButtonClick(new BoomMenuButton.OnSubButtonClickListener() {
                    @Override
                    public void onClick(int buttonIndex) {
                        List<Place> arrEl = getSelectedList();

                        if (buttonIndex == 0) {
                            Collections.sort(arrEl, new Comparator<Place>() {
                                @Override
                                public int compare(Place element1, Place element2) {
                                    return element1.getStepNumber() - element2.getStepNumber();
                                }
                            });
                        } else if (buttonIndex == 1) {
                            Collections.sort(arrEl, new Comparator<Place>() {
                                @Override
                                public int compare(Place element1, Place element2) {
                                    return element2.getStepNumber() - element1.getStepNumber();
                                }
                            });
                        } else if (buttonIndex == 2) {
                            Collections.sort(arrEl, new Comparator<Place>() {
                                @Override
                                public int compare(Place element1, Place element2) {
                                    return element1.getPlaceName().compareTo(element2.getPlaceName());
                                }
                            });
                        } else if (buttonIndex == 3) {
                            Collections.sort(arrEl, new Comparator<Place>() {
                                @Override
                                public int compare(Place element1, Place element2) {
                                    return element2.getPlaceName().compareTo(element1.getPlaceName());
                                }
                            });
                        }

                        viewAllAdapter.notifyDataSetChanged();
                    }
                })
                .init(boomSort);
    }

    private void setupViewAllAdapter() {
        viewAllRV.setAdapter(viewAllAdapter);
        viewAllRV.setHasFixedSize(true);
        viewAllRV.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    public List<Place> getSelectedList() {
        return selectedList;
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void toShow() {

    }

    @Override
    public void showing(float v) {

    }

    @Override
    public void showed() {

    }

    @Override
    public void toHide() {

    }

    @Override
    public void hiding(float v) {

    }

    @Override
    public void hided() {

    }

    @Override
    public void onClick(int i) {

    }

    @Override
    public void onBackPressed() {
        if (boomSort.isClosed()) {
            super.onBackPressed();
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

        } else {
            boomSort.dismiss();
        }
    }

    public void onArrowClick(View view) {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}