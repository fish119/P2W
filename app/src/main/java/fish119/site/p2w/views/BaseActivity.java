package fish119.site.p2w.views;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.Toast;

import fish119.site.p2w.R;

/**
 * Created by fish119 on 2017/7/1.
 */

public class BaseActivity extends AppCompatActivity {
    public Toolbar toolbar;
    public static final int CLOSE_ENTRY = 0;//参数：是否关闭EntryActivity
    private long touchTime = 0;//点击次数（双击退出时使用）

    /**
     * 设置自定义Toolbar的title
     *
     * @param title     标题
     */
    protected void initToolBar(String title) {
        toolbar = (Toolbar) findViewById(R.id.toolbar_activity);
        setSupportActionBar(toolbar);//继承自ActionBarActivity
        //隐藏Toolbar的标题
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView titleTv = (TextView) toolbar.findViewById(R.id.tv_title_toolbar);
        titleTv.setText(title);
    }

    /**
     * 跳转到另一个Activity且不结束本身
     *
     * @param context context
     * @param target  目标Activity的class
     */
    public void goActivity(Context context, final Class<?> target) {
        Intent intent = new Intent(context, target);
        startActivity(intent);
    }

    /**
     * 跳转到另一个Activity，结束本身
     *
     * @param context context
     * @param target  目标Activity的class
     */
    public void goActivityWithFinish(Context context, final Class<?> target) {
        Intent intent = new Intent(context, target);
        startActivity(intent);
        finish();
    }

    /**
     * 双击返回按钮退出程序
     *
     * @param keyCode keyCode
     * @param event   event
     * @return 双击返回true, 否则返回super.onKeyDown(keyCode, event)
     */
    protected boolean doubleClickToQuit(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN && KeyEvent.KEYCODE_BACK == keyCode) {
            long currentTime = System.currentTimeMillis();
            if ((currentTime - touchTime) >= 2000) {
                Toast.makeText(this, getString(R.string.press_again_to_quit), Toast.LENGTH_SHORT).show();
                touchTime = currentTime;
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
