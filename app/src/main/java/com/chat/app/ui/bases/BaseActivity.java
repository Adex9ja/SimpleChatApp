package com.chat.app.ui.bases;

import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.google.android.material.snackbar.Snackbar;
import com.chat.app.R;
import com.chat.app.util.MyHandler;

import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

/**
 * Created by root on 9/24/17.
 */

@SuppressWarnings("ALL")
public abstract class BaseActivity extends AppCompatActivity {

    private MyHandler handler;


    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
        handler = new MyHandler(this, true);

    }

    public void setToolbar(Toolbar toolbar, String title) {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    protected void toastSuccess(String msg) {
        Toasty.success(this, msg, Toast.LENGTH_SHORT, true).show();
    }

    protected void toastError(String msg) {
        Toasty.error(this, msg, Toast.LENGTH_SHORT, true).show();
    }

    protected void toastInfo(String msg) {
        Toasty.info(this, msg, Toast.LENGTH_SHORT, true).show();
    }

    protected void toastWarning(String msg) {
        Toasty.warning(this, msg, Toast.LENGTH_SHORT, true).show();
    }

    protected void startLoading(){
        handler.sendEmptyMessage(0);
    }

    protected void loadingSuccessful(){
        handler.sendEmptyMessage(1);
    }

    protected void loadingFailed(String msg){
        handler.sendEmptyMessage(1);
        if(!TextUtils.isEmpty(msg)){
            toastError(msg);
        }

    }

    protected void loadingSuccessful(String msg) {
        handler.sendEmptyMessage(1);
        if(!TextUtils.isEmpty(msg)){
            toastSuccess(msg);
        }

    }
}
