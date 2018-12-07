/*
 * This is the source code of Telegram for Android v. 3.x.x.
 * It is licensed under GNU GPL v. 2 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Nikolai Kudashov, 2013-2017.
 */

package poputka.neobis.poputkakg.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.format.DateFormat;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AndroidUtilities {
    public static float density = 1;
    public static Point displaySize = new Point();
    public static DisplayMetrics displayMetrics = new DisplayMetrics();
    private Context mContext;

    public AndroidUtilities(Context mContext) {
        this.mContext = mContext;
        checkDisplaySize(null);
    }

    public static Bitmap decodeBase64ToBitmap(String encodedImage) {
        try {
            byte[] decodedString = Base64.decode(encodedImage.getBytes(), Base64.DEFAULT);
            return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String replaceAllOccurences(String temp, String reg, String value) {
        return temp.replaceAll(reg, value);
    }

    public static void showKeyboard(View view) {
        if (view == null) {
            return;
        }
        try {
            InputMethodManager inputManager = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void hideKeyboard(View view) {
        if (view == null) {
            return;
        }
        try {
            InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (!imm.isActive()) {
                return;
            }
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int dp(float value) {
        if (value == 0.0) {
            return 0;
        }
        return (int) Math.ceil(density * value);
    }

    public static int dp2(float value) {
        if (value == 0) {
            return 0;
        }
        return (int) Math.floor(density * value);
    }

    public static int compare(int lhs, int rhs) {
        if (lhs == rhs) {
            return 0;
        } else if (lhs > rhs) {
            return 1;
        }
        return -1;
    }

    public static float dpf2(float value) {
        if (value == 0) {
            return 0;
        }
        return density * value;
    }

    public void checkDisplaySize(Configuration newConfiguration) {
        try {
            density = mContext.getResources().getDisplayMetrics().density;
            Configuration configuration = newConfiguration;
            if (configuration == null) {
                configuration = mContext.getResources().getConfiguration();
            }
            WindowManager manager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
            if (manager != null) {
                Display display = manager.getDefaultDisplay();
                if (display != null) {
                    display.getMetrics(displayMetrics);
                    display.getSize(displaySize);
                }
            }
            if (configuration.screenWidthDp != Configuration.SCREEN_WIDTH_DP_UNDEFINED) {
                int newSize = (int) Math.ceil(configuration.screenWidthDp * density);
                if (Math.abs(displaySize.x - newSize) > 3) {
                    displaySize.x = newSize;
                }
            }
            if (configuration.screenHeightDp != Configuration.SCREEN_HEIGHT_DP_UNDEFINED) {
                int newSize = (int) Math.ceil(configuration.screenHeightDp * density);
                if (Math.abs(displaySize.y - newSize) > 3) {
                    displaySize.y = newSize;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static float getPixelsInCM(float cm, boolean isX) {
        return (cm / 2.54f) * (isX ? displayMetrics.xdpi : displayMetrics.ydpi);
    }

    public static String parseDay(String dateStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
            Date date = sdf.parse(dateStr);
            return DateFormat.format("dd", date).toString() + "." +
                    DateFormat.format("MM", date).toString() + "." +
                    DateFormat.format("yyyy", date).toString();
        } catch (ParseException e) {
            return "unidefined";
        }
    }

    public static String takeHour(String dateStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.getDefault());
            Date date = sdf.parse(dateStr);
            return (DateFormat.format("HH", date).toString() + ":" + DateFormat.format("mm", date).toString());
        } catch (ParseException e) {
            return "unidefined";
        }
    }

    public static String formattedDate(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss", Locale.getDefault());
        try {
            Date myDate = dateFormat.parse(date);
            SimpleDateFormat timeFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()); //    kk:mm 22:46
            return timeFormat.format(myDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "-";
    }

    public static int formattedDate(String date, String type) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss", Locale.getDefault());
        try {
            Date myDate = dateFormat.parse(date);
            SimpleDateFormat timeFormat = new SimpleDateFormat(type, Locale.getDefault()); //    kk:mm 22:46
            return Integer.parseInt(timeFormat.format(myDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean isNetworkOnline() {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
            if (netInfo != null && (netInfo.isConnectedOrConnecting() || netInfo.isAvailable())){
                return true;
            }

            netInfo = connectivityManager.getActiveNetworkInfo();
            if (netInfo != null && netInfo.isConnectedOrConnecting()) {
                return true; // connected to the internet
            }
            return netInfo.getType() == ConnectivityManager.TYPE_MOBILE || netInfo.getType() == ConnectivityManager.TYPE_WIFI;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
