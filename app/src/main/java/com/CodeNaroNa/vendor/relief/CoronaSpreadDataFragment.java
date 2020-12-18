package com.CodeNaroNa.vendor.relief;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.CodeNaroNa.vendor.relief.R;


public class CoronaSpreadDataFragment extends Fragment {

    private WebView web;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crona_spread_data, container,false);
        web=view.findViewById(R.id.web);
        web.loadUrl("https://vendor-relief.web.app/data.html");
        WebSettings webSettings = web.getSettings();
        webSettings.setJavaScriptEnabled(true);

        return view;
    }
}
