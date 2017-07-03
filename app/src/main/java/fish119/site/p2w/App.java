package fish119.site.p2w;

import android.app.Application;
import com.youdao.sdk.app.YouDaoApplication;
import fish119.site.p2w.utils.Constant;
import io.realm.Realm;

/**
 * Created by fish119 on 2017/6/30.
 */

public class App extends Application {
    private static App app;
    @Override
    public void onCreate() {
        super.onCreate();
        YouDaoApplication.init(this, Constant.APP_KEY);//创建应用，每个应用都会有一个Appkey，绑定对应的翻译服务实例，即可使用
        app = this;
        Realm.init(this);
    }

    public static App getInstance() {
        return app;
    }
}
