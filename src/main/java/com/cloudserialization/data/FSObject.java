package com.cloudserialization.data;

import java.util.ArrayList;
import java.util.List;

import static com.cloudserialization.serialization.SerializationWriter.writeBytes;

/**
 * Created by notechus on 4/1/16.
 */
public class FSObject
{
    public static final byte CONTAINER_TYPE = ContainerType.OBJECT;
    public short nameLength;
    public byte[] name;
    private int size = 1 + 2 + 2 + 2;
    private short fieldsCount;
    private short arraysCount;

    private List<FSField> fields = new ArrayList<>();
    private List<FSArray> arrays = new ArrayList<>();

    private FSObject()
    {

    }

    public FSObject(String name)
    {
        assert (name.length() < Short.MAX_VALUE);

        nameLength = (short) name.length();
        this.name = name.getBytes();
        size += nameLength;
    }

    public void addField(FSField field)
    {
        fields.add(field);
        size += field.getSize();

        fieldsCount = (short) fields.size();
    }

    public void addArray(FSArray array)
    {
        arrays.add(array);
        size += array.getSize();

        arraysCount = (short) arrays.size();
    }

    public int getSize()
    {
        return size;
    }

    public int getBytes(byte[] dest, int pointer)
    {
        pointer = writeBytes(dest, pointer, CONTAINER_TYPE);
        pointer = writeBytes(dest, pointer, nameLength);
        pointer = writeBytes(dest, pointer, name);
        pointer = writeBytes(dest, pointer, fieldsCount);
        for (FSField field : fields)
        {
            pointer = field.getBytes(dest, pointer);
        }
        pointer = writeBytes(dest, pointer, arraysCount);
        for (FSArray array : arrays)
        {
            pointer = array.getBytes(dest, pointer);
        }

        return pointer;
    }


}
