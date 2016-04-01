package com.cloudserialization;

import com.cloudserialization.data.FSArray;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main
{
    static void printBytes(byte[] data)
    {
        for (int i = 0; i < data.length; i++)
        {
            System.out.printf("0x%x ", data[i]);
        }
    }

    static void saveToFile(String path, byte[] data)
    {
        try
        {
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(path));
            stream.write(data);
            stream.close();
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }


    public static void main(String[] args)
    {
        int[] digits = new int[50];
        for (int i = 0; i < digits.length; ++i)
        {
            digits[i] = i;
        }
        int[] elements = new int[]{1, 2, 3, 4, 5};
        FSArray array = FSArray.Integer("test", digits);

        byte[] data = new byte[array.getSize()];
        array.getBytes(data, 0);
        printBytes(data);
    }
}
