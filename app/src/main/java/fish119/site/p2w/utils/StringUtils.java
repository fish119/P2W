package fish119.site.p2w.utils;

/**
 * Created by fish119 on 2017/6/30.
 */

public class StringUtils {
    public static String getSpeakUrl(String result){
        return Constant.SPEAK_URL + result.replaceAll("The ", "").replaceAll("a ", "").replaceAll("A ", "") + Constant.SPEAK_URL_SUFFIX;
    }
}
