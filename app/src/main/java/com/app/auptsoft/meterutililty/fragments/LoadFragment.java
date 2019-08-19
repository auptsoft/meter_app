package com.app.auptsoft.meterutililty.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.auptsoft.meterutililty.AppState;
import com.app.auptsoft.meterutililty.R;
import com.app.auptsoft.meterutililty.databinding.FragmentLoadBinding;
import com.app.auptsoft.meterutililty.model.Load;
import com.app.auptsoft.meterutililty.presenters.LoadFragmentPresenter;

/**
 * Created by Administrator on 12/8/2018.
 */

public class LoadFragment extends Fragment{
    int index;
    Load load;

    TextView loadName;

    //FloatingActionButton currentValuesButton;

    FragmentLoadBinding binding;
    LoadFragmentPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        index = getArguments().getInt("loadIndex");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        load = AppState.allLoads.get(index);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_load, container,  false);
        View view = binding.getRoot();

        presenter = new LoadFragmentPresenter(AppState.allLoads.get(index), getActivity(), index);
        binding.setPresenter(presenter);

        loadName = binding.loadNameId;
        loadName.setText(AppState.allLoads.get(index).getName());

        return view;
    }

    public static LoadFragment newInstance(int index){
        LoadFragment loadFragment = new LoadFragment();

        Bundle arg = new Bundle();
        arg.putInt("loadIndex", index);

        loadFragment.setArguments(arg);

        return loadFragment;
    }
}
