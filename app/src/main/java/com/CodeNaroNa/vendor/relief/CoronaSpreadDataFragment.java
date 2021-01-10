package com.CodeNaroNa.vendor.relief;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.CodeNaroNa.vendor.relief.Helper.NetworkCheckHelper;


public class CoronaSpreadDataFragment extends Fragment {

    private WebView web;
    private TextView noInternetView;
    private NetworkCheckHelper networkCheckHelper;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crona_spread_data, container,false);
        web=view.findViewById(R.id.web);
        noInternetView = view.findViewById(R.id.no_internet);
        networkCheckHelper = new NetworkCheckHelper(getActivity());
        if(networkCheckHelper.checkDeviceConnectedToInternet()) {
            web.loadUrl("https://vendor-relief.web.app/data.html");
            WebSettings webSettings = web.getSettings();
            webSettings.setJavaScriptEnabled(true);
            noInternetView.setVisibility(View.GONE);
        } else {
            noInternetView.setVisibility(View.VISIBLE);
        }

        return view;
    }
}
