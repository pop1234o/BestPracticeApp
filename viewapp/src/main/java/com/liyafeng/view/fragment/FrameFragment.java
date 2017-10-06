package com.liyafeng.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by liyafeng on 2017/10/1.
 */

public class FrameFragment extends Fragment {

    private static int  n=1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());
        textView.setText("this is  frame fragment"+n++);
        return textView;
    }
}
