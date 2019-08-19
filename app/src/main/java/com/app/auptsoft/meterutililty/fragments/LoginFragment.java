package com.app.auptsoft.meterutililty.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.auptsoft.meterutililty.R;
import com.app.auptsoft.meterutililty.databinding.FragmentLoginBinding;
import com.app.auptsoft.meterutililty.model.User;
import com.app.auptsoft.meterutililty.view_models.UserViewModel;


/**
 * Created by Andrew on 18.3.19.
 */

public class LoginFragment extends Fragment {

    FragmentLoginBinding fragmentLoginBinding;
    UserViewModel userViewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentLoginBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        View view = fragmentLoginBinding.getRoot();

        User user = new User();
        userViewModel = new UserViewModel(user, "", getActivity());
        userViewModel.setLoading(false);

        fragmentLoginBinding.setUserViewModel(userViewModel);

        return view;
    }
}
