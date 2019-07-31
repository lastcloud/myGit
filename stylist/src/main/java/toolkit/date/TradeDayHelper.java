package toolkit.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TradeDayHelper {

    private final static SimpleDateFormat _tradeDaySDF = new SimpleDateFormat("yyyyMMdd");

    private Date now = new Date();

    private Calendar calendar = Calendar.getInstance();

    private ArrayList<String> tradeDayList = new ArrayList<>();

    /**
     * 主要考量DateFormat非线程安全，也许有并发场景，还是要防范下
     */
    private static ThreadLocal<DateFormat> threadLocal = new ThreadLocal<DateFormat>(){
        @Override
        protected DateFormat initialValue(){
            return _tradeDaySDF;
        }
    };


    public static String formatDate(Date date){

        /*
            synchronized (_tradeDaySDF){
                return _tradeDaySDF.format(date);
            }
        */
        return threadLocal.get().format(date);
    }

    public static Date parseDate(String dateStr) throws ParseException{
        return threadLocal.get().parse(dateStr);
    }

    public void init (List<String> tradeDayList){


        /**
         * 获取一年的所有日期
         */
        calendar.setTime(now);
        calendar.set(Calendar.DAY_OF_YEAR, 1);
        String startDate = formatDate(calendar.getTime());

        //System.out.println(startDate);

        calendar.add(Calendar.YEAR, 1);
        calendar.set(Calendar.DAY_OF_YEAR, 0);

        String endDate = formatDate(calendar.getTime());

        //System.out.println(endDate);


        try{

            long startTime = parseDate(startDate).getTime();
            long endTime = parseDate(endDate).getTime();
            long unit = 1000*60*60*24;
            for(long i = startTime; i <= endTime; i+=unit){

                Date date = new Date(i);

                if(isTradeDay(date)){

                    tradeDayList.add(formatDate(date));
                }
            }
        }catch (ParseException e){
            e.printStackTrace();
        }

        System.out.println(tradeDayList);

        /**
         * 获取节假日
         */
    }

    public  boolean isTradeDay(Date date){



        calendar.setTime(date);
        if(calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
            return false;
        }
        /**
         * 节日的话可能还是拿到年度硬编码
         * 国庆节
         */
        if(calendar.get(Calendar.MONTH) == 9 && calendar.get(Calendar.DAY_OF_MONTH) == 1){
            return false;
        }

        return true;
    }

    public String getTradeDay(Date time, int offsetDays){


        String tradeDay = "";
        String tradeDayStr = getEffectiveDate(time);

        try{

            init(tradeDayList);
            if(offsetDays <= tradeDayList.size()){
                if(tradeDayList.contains(tradeDayStr)){
                    int index = tradeDayList.indexOf(tradeDayStr);

                    tradeDay = tradeDayList.get(index + offsetDays);
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println(tradeDay);
            return tradeDay;

        }

    }

    public String getEffectiveDate(Date time){

        Date _AMTradeTime_start;
        Date _AMTradeTime_end;

        Date _PMTradeTime_start;
        Date _PMTradeTime_end;


        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");

        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


        String dateStr = sdf1.format(time);

        //System.out.println(dateStr);

        calendar.setTime(time);

        try {
            synchronized (sdf2) {


                _AMTradeTime_start = sdf2.parse(dateStr + " 09:30:00");
                _AMTradeTime_end = sdf2.parse(dateStr + " 11:30:00");

                _PMTradeTime_start = sdf2.parse(dateStr + " 13:00:00");
                _PMTradeTime_end = sdf2.parse(dateStr + " 15:00:00");

                Calendar _AM_begin = Calendar.getInstance();
                _AM_begin.setTime(_AMTradeTime_start);

                Calendar _AM_end = Calendar.getInstance();
                _AM_end.setTime(_AMTradeTime_end);

                Calendar _PM_begin = Calendar.getInstance();
                _PM_begin.setTime(_PMTradeTime_start);

                Calendar _PM_end = Calendar.getInstance();
                _PM_end.setTime(_PMTradeTime_end);

                if ((calendar.after(_AM_begin) && calendar.before(_AM_end)) || (calendar.after(_PM_begin) && calendar.before(_PM_end))){
                    /**
                     * 有效交易日期内
                     */
                    System.out.println("Effective trade time");
                }else{
                    calendar.add(Calendar.DAY_OF_YEAR, 1);
                    time = (calendar.getTime());

                }

            }
        }catch (ParseException e){
            e.printStackTrace();
        }

        while(! isTradeDay(time)){
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            time = calendar.getTime();
        }

        //System.out.println(formatDate(time));

        return formatDate(time);
    }

    public static void main(String[] args){


        //new TradeDayHelper().init(new ArrayList<String>());

        new TradeDayHelper().getEffectiveDate(new Date());

        new TradeDayHelper().getTradeDay(new Date(), 5);
    }
}
