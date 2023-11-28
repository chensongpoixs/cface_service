import java.text.SimpleDateFormat;
import java.util.Date;

public class Test
{
    @org.junit.jupiter.api.Test
    public void test()
    {
        //1970-01-01 00:00:00
        Integer timestamp = 60 * 60 * 24;
        //1970-01-01 00:00:45
        Date date = new Date(timestamp * 1000 );
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(dateFormat.format(date));
    }
}
