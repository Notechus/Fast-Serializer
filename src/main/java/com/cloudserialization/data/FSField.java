package com.cloudserialization.data;

import static com.cloudserialization.serialization.SerializationWriter.writeBytes;

/**
 * Created by notechus on 3/27/16.
 */
public class FSField
{
    // should imitate struct
    public static final byte CONTAINER_TYPE = ContainerType.FIELD;
    public short nameLength;
    public byte[] name;
    public byte type;
    public byte[] data;

    private FSField()
    {

    }

    public void setName(String name)
    {
        assert (name.length() > Short.MAX_VALUE);
        this.nameLength = (short) name.length();
        this.name = name.getBytes();
    }

    public int getSize()
    {
        assert (data.length == FSType.getSize(type));
        return 1 + 2 + name.length + 1 + data.length;
    }

    public int getBytes(byte[] dest, int pointer)
    {
        pointer = writeBytes(dest, pointer, CONTAINER_TYPE);
        pointer = writeBytes(dest, pointer, nameLength);
        pointer = writeBytes(dest, pointer, name);
        pointer = writeBytes(dest, pointer, type);
        pointer = writeBytes(dest, pointer, data);

        return pointer;
    }

    public static FSField Byte(String name, byte value)
    {
        FSField field = new FSField();
        field.setName(name);
        field.type = FSType.BYTE;
        field.data = new byte[FSType.getSize(FSType.BYTE)];
        writeBytes(field.data, 0, value);
        return field;
    }

    public static FSField Boolean(String name, boolean value)
    {
        FSField field = new FSField();
        field.setName(name);
        field.type = FSType.BOOLEAN;
        field.data = new byte[FSType.getSize(FSType.BOOLEAN)];
        writeBytes(field.data, 0, value);
        return field;
    }

    public static FSField Short(String name, short value)
    {
        FSField field = new FSField();
        field.setName(name);
        field.type = FSType.SHORT;
        field.data = new byte[FSType.getSize(FSType.SHORT)];
        writeBytes(field.data, 0, value);
        return field;
    }

    public static FSField Char(String name, char value)
    {
        FSField field = new FSField();
        field.setName(name);
        field.type = FSType.CHAR;
        field.data = new byte[FSType.getSize(FSType.CHAR)];
        writeBytes(field.data, 0, value);
        return field;
    }

    public static FSField Integer(String name, int value)
    {
        FSField field = new FSField();
        field.setName(name);
        field.type = FSType.INTEGER;
        field.data = new byte[FSType.getSize(FSType.INTEGER)];
        writeBytes(field.data, 0, value);
        return field;
    }

    public static FSField Long(String name, long value)
    {
        FSField field = new FSField();
        field.setName(name);
        field.type = FSType.LONG;
        field.data = new byte[FSType.getSize(FSType.LONG)];
        writeBytes(field.data, 0, value);
        return field;
    }

    public static FSField Float(String name, float value)
    {
        FSField field = new FSField();
        field.setName(name);
        field.type = FSType.FLOAT;
        field.data = new byte[FSType.getSize(FSType.FLOAT)];
        writeBytes(field.data, 0, value);
        return field;
    }

    public static FSField Double(String name, double value)
    {
        FSField field = new FSField();
        field.setName(name);
        field.type = FSType.DOUBLE;
        field.data = new byte[FSType.getSize(FSType.DOUBLE)];
        writeBytes(field.data, 0, value);
        return field;
    }


}