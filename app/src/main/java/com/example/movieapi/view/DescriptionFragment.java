package com.example.movieapi.view;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.movieapi.R;

public class DescriptionFragment extends Fragment {

    TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_description, container, false);
        Bundle bundle = getArguments();
        String string = bundle.getString("ID");
        textView = view.findViewById(R.id.wqw);
        textView.setText(string);
        return view;
    }
}
