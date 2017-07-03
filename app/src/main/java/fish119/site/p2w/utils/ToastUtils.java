package fish119.site.p2w.utils;

import android.content.Context;
import android.widget.Toast;

import fish119.site.p2w.App;

/**
 * Created by fish119 on 2017/6/30.
 */

public class ToastUtils {

    private static Toast toast = null;

    public static void show(String msg) {
        showText(App.getInstance(), msg, Toast.LENGTH_SHORT);
    }

    public static void show(Integer stringId) {
        showText(App.getInstance(), stringId, Toast.LENGTH_SHORT);
    }

    public static void show(Context context, String msg) {
        showText(context, msg, Toast.LENGTH_SHORT);
    }

    public static void show(Context context, Integer stringId) {
        showText(context, stringId, Toast.LENGTH_SHORT);
    }

    private static void showText(Context context, String msg, int length) {
        if (toast == null) {
            toast = Toast.makeText(context, msg, length);
        } else {
            toast.setText(msg);
        }
        toast.show();
    }

    private static void showText(Context context, Integer stringId, int length) {
        if (toast == null) {
            toast = Toast.makeText(context, stringId, length);
        } else {
            toast.setText(stringId);
        }
        toast.show();
    }
}
