package com.funi.distributedcomputer.clone;

import java.io.*;

public abstract class SuperObject<T> implements Serializable, Cloneable {
    /**
     * 深度克隆
     *
     * @param <T>
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public <T> T deepClone() throws IOException, ClassNotFoundException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(this);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        return (T) objectInputStream.readObject();
    }
}
