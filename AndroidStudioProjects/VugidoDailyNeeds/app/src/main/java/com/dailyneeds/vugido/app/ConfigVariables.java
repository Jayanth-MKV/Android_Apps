package com.dailyneeds.vugido.app;

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

    public static final String ADD_TO_CART_URL = "http://www.vugido.com/VDBACKEND/ADD_TO_CART.php";

    public static final String INSERT_CART_URL = "http://www.vugido.com/VDBACKEND/INSERT_CART_SYSTEM.php";



    public static final String GET_CART_PRODUCTS_URL = "http://www.vugido.com/VDBACKEND/GET_CART_PRODUCTS.php";
    public static final String UPDATE_URL = "http://www.vugido.com/VDBACKEND/APP_UPDATE_INFO.php";
    public static final String PLACE_ORDER = "http://www.vugido.com/VDBACKEND/PLACE_ORDER.php";
    public static final String GET_ORDER_INFO = "http://www.vugido.com/VDBACKEND/GET_ORDER_INFO.php";
    public static final String GET_ORDER_ITEMS = "http://www.vugido.com/VDBACKEND/GET_ORDERED_ITEMS.php";
    public static final String GET_FIREBASE_TOKEN = "http://www.vugido.com/VDBACKEND/GET_FIREBASE_TOKEN.php";
    public static final String SEND_FIREBASE_TOKEN = "http://www.vugido.com/VDBACKEND/SEND_FIREBASE_TOKEN.php";
    public static final String GET_USER_PURCHASES = "http://www.vugido.com/VDBACKEND/GET_USER_PURCHASES.php";
    public static final String FETCH_CART_COUNT = "http://www.vugido.com/VDBACKEND/GET_CART_COUNT.php";
    public static String SEND_OTP_URL="http://www.vugido.com/VDBACKEND/REQUEST_SMS.php";
    public static String VERIFY_OTP_URL="http://www.vugido.com/VDBACKEND/VERIFY_OTP.php";
    public static String VEGETABLES_URL="http://www.vugido.com/";
    public static String FRUITS_URL="http://www.vugido.com/V_INVENTORY_END/FRUITS.php";
    public static String VEGETABLES_IMG_URL="http://www.vugido.com/V_INVENTORY_END/VEG_IMAGES/";


    public static final String MY_APP_URL="http://play.google.com/store/apps/details?id=com.dailyneeds.vugido";


    // voice search URL...

    public  static final String VOICE_SEARCH_URL="http://www.vugido.com/V_INVENTORY_END/VOICE_SEARCH.php";


    public static final double DestinationLatitude=18.300283;
    public static final double DestinationLongitude= 83.883357;

    public static final int ORDER_PLACED_CODE=1000;
    public  static final int ORDER_PLACED_RESPONSE_CODE=10000;



    //---------------------------------------------------------------------------
    //Product attributes global...

    public static String quantityUnit(int unit){
        if(unit==0){
            return "mg";
        }else if(unit==1){
            return "g";
        }else {
            return "kg";
        }

    }

    public static final int QUANTITY=0;
    public static final int UNITS=1;
    public static final int SIZE=2;

// default size values...

    public static final int SMALL=1;
    public static final int MEDIUM=2;
    public static final int LARGE=3;

    // quantity values..
    public  static final int BASE_QUANTITY=250;

    public static final int OFFER=1;

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


    // Order status variables....

    public  static final int PACKING=0;
   // public static final int PACKED=1;
    public static final int DISPATCHING=1;
  //  public static final int DISPATCHED=3;
    public static final int PROCESSED=2;


    public static int Userslot(){

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("HH:mm:ss",Locale.getDefault());
        String Current= simpleDateFormat.format(c);
        String SlotStart = "05:00:00";
        String SlotEnd   = "21:00:00";
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss",Locale.getDefault());

        try {
            Date d1=format.parse(Current);
            Date d2=format.parse(SlotEnd);
            Date d3=format.parse(SlotStart);


            assert d2 != null;
            assert d1 != null;
            long CurrentDiff=d2.getTime()-d1.getTime();
            assert d3 != null;
          //  long UsualDiff= d2.getTime()-d3.getTime();



            // cal diff for current slots..

            long CurrentdiffSeconds = CurrentDiff / 1000 % 60;
            long CurrentdiffMinutes = CurrentDiff / (60 * 1000) % 60;
            long CurrentdiffHours = CurrentDiff / (60 * 60 * 1000) % 24;
            long CurrentdiffDays = CurrentDiff / (24 * 60 * 60 * 1000);


            long CurrentMilli=getMilliSeconds(CurrentdiffDays,CurrentdiffHours,CurrentdiffMinutes,CurrentdiffSeconds);

            long CurrentMin=milliToMin(CurrentMilli);

            long UsualMin=  16 * 60;

            long FinishedMin= UsualMin-CurrentMin;


            Log.e("Usual",String.valueOf(UsualMin));
            Log.e("Current", String.valueOf(CurrentMin));
            Log.e("FinishedMin",String.valueOf(FinishedMin));

            return slotComparator(FinishedMin,UsualMin);


        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;

    }

    public static final int ALL_SLOTS=1;
    public  static final int CUSTOM_SLOTS=2;
    public static final int NO_SLOTS=3;
    public static final int TODAY=1;
    public static final int TOMORROW=2;




    public  static boolean appWeeklyServiceTimings(){


        Calendar myDate = Calendar.getInstance(); // set this up however you need it.
        int dow = myDate.get (Calendar.DAY_OF_WEEK);
        boolean isSunToThur = ((dow >= Calendar.SUNDAY) && (dow <= Calendar.THURSDAY));

        if(isSunToThur){


            return  true;


        }else {

            // the current day is friday or saturday...

            // when the current day is fri or sat compare the time of the service.... of order...


            Date c = Calendar.getInstance().getTime();
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("HH:mm:ss",Locale.getDefault());
            String Current= simpleDateFormat.format(c);
            String t1 = "16:59:59";
            String t2 = "17:59:59";
            SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss",Locale.getDefault());

            try {
                Date d1= format.parse(t1);
                Date d2= format.parse(t2);
                Date current=format.parse(Current);
                int CurrentDay = myDate.get (Calendar.DAY_OF_WEEK);

                if(CurrentDay== Calendar.FRIDAY){

                    // if it is friday then stop service at  17:00 ..

                    assert current != null;
                    boolean isAfter_5=current.after(d1);

                    // return service closed..
                    // ok service running
                    return !isAfter_5;


                }else if(CurrentDay== Calendar.SATURDAY){

                    // start the app after 18:00

                    assert current != null;
                    boolean isBefore_6=current.before(d2);

                    // service not exists...
                    // service exists....
                    return !isBefore_6;




                }




            } catch (ParseException e) {
                e.printStackTrace();
            }



        }



        return  false;

    }

    public static int xyz(){

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("HH:mm:ss",Locale.getDefault());
        String Current= simpleDateFormat.format(c);
        String t1 = "23:59:59";
        String t2 = "04:59:59";
        String t3 = "21:00:00";
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss",Locale.getDefault());

        try {
            Date d=format.parse(Current);

            Date d1=format.parse(t1);

            Date d2=format.parse(t2);

            Date d3=format.parse(t3);

            assert d != null;
            boolean d_before_12am=d.before(d1);
            boolean d_after_9pm=d.after(d3);
            boolean d_before_9pm=d.before(d3);
            boolean d_after_5am=d.after(d2);
           // boolean d_after_12am=d.after(d1);

            if(d_before_9pm && d_after_5am){


                // normal flow
                return CUSTOM_SLOTS;

            }else if(d_after_9pm && d_before_12am){

                return NO_SLOTS;
                // no slots for today... order tomorrow..

            }else if(!d_before_12am && !d_after_5am){

                // show all slots to user..

                return ALL_SLOTS;
            }






        } catch (ParseException e) {
            e.printStackTrace();
        }

return 0;
    }
    public static boolean expressDeliveryAvailability(){

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("HH:mm:ss",Locale.getDefault());
        String Current= simpleDateFormat.format(c);
        String t1 = "23:59:59";
        String t2 = "04:59:59";
        String t3 = "21:00:00";
        String t4 = "00:00:00";

        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss",Locale.getDefault());

        try {
            Date d=format.parse(Current);

            Date d1=format.parse(t1);

            Date d2=format.parse(t2);

            Date d3=format.parse(t3);

            Date d4=format.parse(t4);

            assert d != null;
            boolean d_before_12am=d.before(d1);
            boolean d_after_9pm=d.after(d3);
            boolean d_after_5am=d.after(d2);
            boolean d_after_12am=d.after(d4);
            boolean d_before_5am=d.before(d2);




            if((d_after_9pm && d_before_12am) || (d_after_12am && d_before_5am) ){
                Log.e("not available","express");
                return false;


            }else {

                Log.e(" available","express");

                return true;
            }






        } catch (ParseException e) {
            e.printStackTrace();
        }

        return false;
    }





    private static int  slotComparator(long finishedMin, long TotalMin) {

        int RunningSlot=60;
        int FlagSlot=0;
        int StartSlot=0;
      while (RunningSlot<=TotalMin){

          FlagSlot+=1;

          if(finishedMin>=StartSlot && finishedMin<=RunningSlot){
              // flag that slot...
              break;
          }


          RunningSlot += 60;
          StartSlot +=60;



      }

      return FlagSlot;

    }


    private static long milliToMin(long milli){


        long seconds=(milli/1000);

        return (seconds/60);

    }






}
