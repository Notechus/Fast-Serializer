package com.cloudserialization.data;

import static com.cloudserialization.serialization.SerializationWriter.writeBytes;

/**
 * Created by notechus on 3/31/16.
 */
public class FSArray
{
    public static final byte CONTAINER_TYPE = ContainerType.ARRAY;
    public short nameLength;
    public byte[] name;
    public byte type;
    public int count;

    // performance will be beautiful even it it looks ugly
    public byte[] byteData;
    public short[] shortData;
    public char[] charData;
    public int[] intData;
    public long[] longData;
    public float[] floatData;
    public double[] doubleData;
    public boolean[] booleanData;

    public void setName(String name)
    {
        assert (name.length() > Short.MAX_VALUE);
        this.nameLength = (short) name.length();
        this.name = name.getBytes();
    }

    public int getBytes(byte[] dest, int pointer)
    {
        pointer = writeBytes(dest, pointer, CONTAINER_TYPE);
        pointer = writeBytes(dest, pointer, nameLength);
        pointer = writeBytes(dest, pointer, name);
        pointer = writeBytes(dest, pointer, type);
        pointer = writeBytes(dest, pointer, count);
        switch (type)
        {
            case FSType.BYTE:
                pointer = writeBytes(dest, pointer, byteData);
                break;
            case FSType.SHORT:
                pointer = writeBytes(dest, pointer, shortData);
                break;
            case FSType.CHAR:
                pointer = writeBytes(dest, pointer, charData);
                break;
            case FSType.INTEGER:
                pointer = writeBytes(dest, pointer, intData);
                break;
            case FSType.LONG:
                pointer = writeBytes(dest, pointer, longData);
                break;
            case FSType.FLOAT:
                pointer = writeBytes(dest, pointer, floatData);
                break;
            case FSType.DOUBLE:
                pointer = writeBytes(dest, pointer, doubleData);
                break;
            case FSType.BOOLEAN:
                pointer = writeBytes(dest, pointer, booleanData);
                break;
        }

        return pointer;
    }

    public int getSize()
    {
        return 1 + 2 + name.length + 1 + 4 + getDataSize();
    }

    public int getDataSize()
    {
        switch (type)
        {
            case FSType.BYTE:
                return byteData.length * FSType.getSize(FSType.BYTE);
            case FSType.SHORT:
                return shortData.length * FSType.getSize(FSType.SHORT);
            case FSType.CHAR:
                return charData.length * FSType.getSize(FSType.CHAR);
            case FSType.INTEGER:
                return intData.length * FSType.getSize(FSType.INTEGER);
            case FSType.LONG:
                return longData.length * FSType.getSize(FSType.LONG);
            case FSType.FLOAT:
                return floatData.length * FSType.getSize(FSType.FLOAT);
            case FSType.DOUBLE:
                return doubleData.length * FSType.getSize(FSType.DOUBLE);
            case FSType.BOOLEAN:
                return booleanData.length * FSType.getSize(FSType.BOOLEAN);
        }
        return 0;
    }


    public static FSArray Byte(String name, byte[] data)
    {
        FSArray array = new FSArray();
        array.setName(name);
        array.type = FSType.BYTE;
        array.count = data.length;
        array.byteData = data;

        return array;
    }

    public static FSArray Short(String name, short[] data)
    {
        FSArray array = new FSArray();
        array.setName(name);
        array.type = FSType.SHORT;
        array.count = data.length;
        array.shortData = data;

        return array;
    }

    public static FSArray Char(String name, char[] data)
    {
        FSArray array = new FSArray();
        array.setName(name);
        array.type = FSType.CHAR;
        array.count = data.length;
        array.charData = data;

        return array;
    }

    public static FSArray Integer(String name, int[] data)
    {
        FSArray array = new FSArray();
        array.setName(name);
        array.type = FSType.INTEGER;
        array.count = data.length;
        array.intData = data;

        return array;
    }

    public static FSArray Long(String name, long[] data)
    {
        FSArray array = new FSArray();
        array.setName(name);
        array.type = FSType.LONG;
        array.count = data.length;
        array.longData = data;

        return array;
    }

    public static FSArray Float(String name, float[] data)
    {
        FSArray array = new FSArray();
        array.setName(name);
        array.type = FSType.FLOAT;
        array.count = data.length;
        array.floatData = data;

        return array;
    }

    public static FSArray Double(String name, double[] data)
    {
        FSArray array = new FSArray();
        array.setName(name);
        array.type = FSType.DOUBLE;
        array.count = data.length;
        array.doubleData = data;

        return array;
    }

    public static FSArray Boolean(String name, boolean[] data)
    {
        FSArray array = new FSArray();
        array.setName(name);
        array.type = FSType.BOOLEAN;
        array.count = data.length;
        array.booleanData = data;

        return array;
    }
}
