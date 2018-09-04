package com.hao.m.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle2.components.support.RxFragment;

/**
 * Created by HaoBoy
 */

public abstract class BaseFragment extends RxFragment {

    private View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        base();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(layoutResId(), container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        logic();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public void base() {
//        Density.setDefault(this.getActivity());
    }
    protected <T extends View> T getViewById(int resId) {
        return this.view.findViewById(resId);
    }
    public abstract int layoutResId();

    public abstract void init();

    public abstract void logic();


}
