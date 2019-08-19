package com.app.auptsoft.meterutililty.view_models;

import android.app.Activity;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.app.auptsoft.meterutililty.AppState;
import com.app.auptsoft.meterutililty.BR;
import com.app.auptsoft.meterutililty.MainActivity;
import com.app.auptsoft.meterutililty.model.User;

/**
 * Created by Andrew on 18.3.19.
 */

public class UserViewModel extends BaseObservable {


    private User user;
    private String confirmPassword;

    private String emailErrorMsg;
    private String usernameErrorMsg;
    private String passwordErrorMsg;
    private String confirmPasswordErrorMsg;


    private Activity activity;
    private boolean loading;

    public UserViewModel(User user, String confirmPassword, Activity activity) {
        this.user = user;
        this.confirmPassword = confirmPassword;
        this.activity = activity;
    }

    public void gotoRegister() {
        if (((AppCompatActivity) activity).getSupportFragmentManager().getBackStackEntryCount() > 0) {
            ((AppCompatActivity) activity).getSupportFragmentManager().popBackStack();
        } else {
            ((AppCompatActivity) activity).getSupportFragmentManager().beginTransaction()
                    //.replace(R.id.autheticate_main_frame, new RegisterFragment())
                    .addToBackStack("")
                    .commit();
        }
    }

    public void gotoLogin() {
        if (((AppCompatActivity) activity).getSupportFragmentManager().getBackStackEntryCount() > 0) {
            ((AppCompatActivity) activity).getSupportFragmentManager().popBackStack();
        } else {
            ((AppCompatActivity) activity).getSupportFragmentManager().beginTransaction()
                    //.replace(R.id.autheticate_main_frame, new LoginFragment())
                    .addToBackStack("")
                    .commit();
        }
    }

    public void login(User user) {
        //setLoading(true);


        if(user.getUsername().equals(AppState.username)
                && user.getPassword().equals(AppState.password)) {
            //Toasty.success(activity, "Authenticated").show();
            Toast.makeText(activity, "Authencticated", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(activity, MainActivity.class);
            AppState.authenticated = true;
            activity.startActivity(intent);
            activity.finish();
        } else {
            //Toasty.info(activity, "Wrong username or password").show();
            Toast.makeText(activity, "Wrong username or password", Toast.LENGTH_LONG).show();
        }

    }


    public boolean validateUsername(String username) {
        return true;
    }


    @Bindable
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        notifyPropertyChanged(BR.user);
    }

    @Bindable
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        notifyPropertyChanged(BR.confirmPassword);
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    @Bindable
    public boolean isLoading() {
        return loading;
    }

    /*@BindingAdapter("android:visibility")
    public static void setLoading(AVLoadingIndicatorView avLoadingIndicatorView, boolean loading) {
        //this.loading = loading;
        avLoadingIndicatorView.setVisibility(loading ? View.VISIBLE : View.GONE);
        //notifyPropertyChanged(BR.loading);
    } */

    @BindingAdapter("android:visibility")
    public static void setLoading(Button button, boolean loading) {
        button.setVisibility(!loading ? View.VISIBLE : View.GONE);
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
        notifyPropertyChanged(BR.loading);
    }


    /*@Bindable
    public String getEmailErrorMsg() {
        return emailErrorMsg;
    }

    public void setEmailErrorMsg(String emailErrorMsg) {
        this.emailErrorMsg = emailErrorMsg;
        notifyPropertyChanged(BR.emailErrorMsg);
    } */

    /*@Bindable
    public String getUsernameErrorMsg() {
        return usernameErrorMsg;
    }

    public void setUsernameErrorMsg(String usernameErrorMsg) {
        this.usernameErrorMsg = usernameErrorMsg;
        notifyPropertyChanged(BR.usernameErrorMsg);
    } */

    /*@Bindable
    public String getPasswordErrorMsg() {
        return passwordErrorMsg;
    }

    public void setPasswordErrorMsg(String passwordErrorMsg) {
        this.passwordErrorMsg = passwordErrorMsg;
        notifyPropertyChanged(BR.passwordErrorMsg);
    } */

    /*@Bindable
    public String getConfirmPasswordErrorMsg() {
        return confirmPasswordErrorMsg;
    }

    public void setConfirmPasswordErrorMsg(String confirmPasswordErrorMsg) {
        this.confirmPasswordErrorMsg = confirmPasswordErrorMsg;
        notifyPropertyChanged(BR.confirmPasswordErrorMsg);
    } */

    @BindingAdapter("android:tag")
    public static void setEmailErrorMsg(TextInputLayout textInputLayout, String emailErrorMsg) {
        //textInputLayout.setErrorEnabled(true);
        //textInputLayout.setError(emailErrorMsg);
    }

}