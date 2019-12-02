package com.cy.utils;

public class IMEI
{
    private static void checkIMEI ( String[] imei )
    {
        int even = 0, odd = 0;
        for ( int i = 0; i < imei.length; i++ )
        {
            int me = Integer.parseInt (imei[i].trim ());
            if (i % 2 == 0)
            {
                even += me;
            }
            else
            {
                String tmp = 2 * me + "";
                for ( int j = 0; j < tmp.length (); j++ )
                {
                    odd += Integer.parseInt (tmp.charAt (j) + "");
                }
            }
        }
        System.out.println ("The sum of the even-positioned digits is " + even);
        System.out.println ("The sum of the digits in the doubled odd-positioned digits is " + odd);
        int sum = even + odd;
        if (sum % 10 == 0)
        {
            System.out.println ("This is a correct IMEI number because the sum of the sums (" + sum + ")\n is a multiple of 10.");
        }
        else
        {
            System.out.println ("This is not a correct IMEI number because the sum of the sums (" + sum + ")\n is not multiple of 10.");
        }
    }
 
}
