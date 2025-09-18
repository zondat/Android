package vn.fpt.coursesupport.prm.project.kingchess.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

import java.util.HashMap;
import java.util.Map;

public class Util {
    private static final Map<String, Drawable> cache = new HashMap<>();

    public static Drawable getDrawable(Context context, String drawableName) {
        if (cache.containsKey(drawableName)) {
            return cache.get(drawableName);
        }

        int resId = context.getResources().getIdentifier(drawableName, "drawable", context.getPackageName());
        if (resId != 0) {
            Drawable drawable = ContextCompat.getDrawable(context, resId);
            cache.put(drawableName, drawable);
            return drawable;
        }

        return null;
    }
    public static void clearCache() {
        cache.clear();
    }

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }
}
