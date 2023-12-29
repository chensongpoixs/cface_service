package com.exadel.frs.core.trainservice.util;

import java.util.ArrayList;
import java.util.List;

public class StringUtilSub
{
    public static List<Integer> SplitArraySum(String arraySum)
    {
        List<Integer>  arrSum = new ArrayList<>();
        String str = "";
        if (   arraySum.length() >0)
        {
            if (arraySum.charAt(0) != '-')
            {
                for (int i = 0; i < arraySum.length(); ++i)
                {
                    if (arraySum.charAt(i) < ('9' +1) && arraySum.charAt(i) > ('0' -1))
                    {
                        str +=arraySum.charAt(i);
                    }
                    else if (arraySum.charAt(i) == ',' /*|| device_id.length() ==  (i) */)
                    {
                        // TODO@chensong Java的接口定义需要这样玩的哈
                        if (str != "")
                        {
                            arrSum.add(Integer.parseInt(str));
//                            devicdids.add(Integer.parseInt(str));

                            str = "";
                        }
                    }
                    if (arraySum.length() ==  (i+1) )
                    {
                        if (str != "")
                        {
                            arrSum.add(Integer.parseInt(str));
//                            devicdids.add(Integer.parseInt(str));

                            str = "";
                        }
                    }
                }
            }
        }
        return arrSum;
    }
}
