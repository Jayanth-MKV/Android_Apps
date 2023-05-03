package vugido.foodhub.ap.sklm.hungrybirds.app_config;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class ConfigVariables {

    public  static  final double mLatitude = 18.316617;
    public  static  final double mLongitude = 83.894910;











    //---------------------------------------------------------------------------
    //Product attributes global...



    public static String getCurrentDate(){
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        return df.format(c);
    }
    public static String getCurrentTime(){

        Date d=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("hh:mm a", Locale.getDefault());
        return sdf.format(d);
    }

    public static long getMilliSeconds(){

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("MM/dd/yyyy HH:mm:ss",Locale.getDefault());
        String Today= simpleDateFormat.format(c);
        String Launch = "10/09/2019 08:00:00";
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss",Locale.getDefault());
        try {
            Date Start=format.parse(Today);
            Date Stop=format.parse(Launch);

            assert Stop != null;
            assert Start != null;
            long diff = Stop.getTime() - Start.getTime();

            long diffSeconds = diff / 1000 % 60;
            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;
            long diffDays = diff / (24 * 60 * 60 * 1000);


            
           /* Log.e("diffDays",  String.valueOf(diffDays));
            Log.e("diffHours",  String.valueOf(diffHours));
            Log.e("diffMin", String.valueOf(diffMinutes));
            Log.e("diffSec", String.valueOf(diffSeconds));        */

            return getMilliSeconds(diffDays,diffHours,diffMinutes,diffSeconds);

        } catch (ParseException e) {
            e.printStackTrace();
            Log.e("error", Objects.requireNonNull(e.getLocalizedMessage()))   ;
        }

        return 0;

    }


    private static long getMilliSeconds(long days,long hours,long min,long sec){


        long DayMs=days*24*3600000;
        long HrMs=hours*3600000;
        long MinMs=min*60000;
        long SecMs=sec * 1000;

        return DayMs+HrMs+MinMs+SecMs;


    }



    public static void setupBadge(int Count, TextView CartCount) {

        if (CartCount != null) {
            if (Count == 0) {
                if (CartCount.getVisibility() != View.GONE) {
                    CartCount.setVisibility(View.GONE);
                }
            } else {
                CartCount.setText(String.valueOf(Count));
                if (CartCount.getVisibility() != View.VISIBLE) {
                    CartCount.setVisibility(View.VISIBLE);

                }
            }
        }
    }



}
