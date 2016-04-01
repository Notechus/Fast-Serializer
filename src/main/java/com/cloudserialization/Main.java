package com.cloudserialization;

import com.cloudserialization.data.Array;

public class Main
{
    static void printBytes(byte[] data)
    {
        for (int i = 0; i < data.length; i++)
        {
            System.out.printf("0x%x ", data[i]);
        }
    }

    public static void main(String[] args)
    {
        int[] digits = new int[50000];
        for (int i = 0; i < digits.length; ++i)
        {
            digits[i] = i;
        }
        int[] elements = new int[]{1, 2, 3, 4, 5};
        Array array = Array.Integer("test", digits);

        byte[] data = new byte[array.getSize()];
        array.getBytes(data, 0);
        printBytes(data);
    }
}
