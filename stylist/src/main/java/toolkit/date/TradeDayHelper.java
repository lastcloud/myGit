package toolkit.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TradeDay {

    private final static SimpleDateFormat _tradeDaySDF = new SimpleDateFormat("yyyyMMdd");
    public void init (List<String> tradeDayList){

        Calendar calendar = Calendar.getInstance();

        System.out.println(calendar);
    }

    public static void main(String[] args){

        Calendar calendar = Calendar.getInstance();

        Date date = new Date();
        

        System.out.println(date);
    }
}
