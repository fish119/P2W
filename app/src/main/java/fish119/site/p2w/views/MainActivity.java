package fish119.site.p2w.views;

import android.Manifest;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.tbruyelle.rxpermissions.RxPermissions;

import java.util.List;

import fish119.site.p2w.R;
import fish119.site.p2w.po.local.TranslatePo;
import fish119.site.p2w.utils.ToastUtils;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import rx.functions.Action1;

/**
 * Created by fish119 on 2017/6/30.
 */
public class MainActivity extends BaseActivity {
    private LinearLayout no_data_ll;
    private ImageView animation_down_arrow;
    private List<TranslatePo> queryHistoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        no_data_ll = (LinearLayout) findViewById(R.id.no_data_ll);
        animation_down_arrow = (ImageView) findViewById(R.id.animation_down_arrow);
        getPermission();
    }

    private void getHistoryList() {
        Realm mRealm = Realm.getDefaultInstance();
        RealmResults<TranslatePo> results = mRealm.where(TranslatePo.class).findAll();
        queryHistoryList = mRealm.copyFromRealm(results);
    }

    /**
     * 双击返回键退出程序
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return doubleClickToQuit(keyCode, event);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        AnimationDrawable animationDrawable = (AnimationDrawable) animation_down_arrow.getBackground();
        if (queryHistoryList != null && queryHistoryList.size() > 0) {
            no_data_ll.setVisibility(View.GONE);
            if (animationDrawable.isRunning()) {
                animationDrawable.stop();
            }
        } else {
            no_data_ll.setVisibility(View.VISIBLE);
            if (!animationDrawable.isRunning()) {
                animationDrawable.start();
            }
        }
    }

    private void getPermission() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(
                Manifest.permission.WRITE_EXTERNAL_STORAGE)//申请存储权限
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean granted) {
                        if (granted) {
                            RealmConfiguration config = new RealmConfiguration.Builder()
                                    .name("p2w.realm")
                                    .deleteRealmIfMigrationNeeded()
                                    .build();
                            Realm.setDefaultConfiguration(config);
                            getHistoryList();
                        } else {
                            ToastUtils.show(getString(R.string.request_permission_write_external_storage));
                        }
                    }
                });
    }
}
