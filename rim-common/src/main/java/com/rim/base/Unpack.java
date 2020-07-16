package com.rim.base;

import com.rim.exception.UnpackException;

import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;


/**
 * @author rickiyang
 * @date 2020-07-15
 * @Desc 实现解包功能
 */

public class Unpack {
    protected ByteBuffer buffer;

    public int size() {
        return buffer.limit() - buffer.position();
    }

    /* wrap */
    public Unpack(byte[] bytes, int offset, int length) {
        buffer = ByteBuffer.wrap(bytes, offset, length);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
    }

    public Unpack(byte[] bytes) {
        this(bytes, 0, bytes.length);
    }

    /**
     * buf [ position : limit] -> Unpack
     */
    public Unpack(ByteBuffer buf) {
        this(buf.array(), buf.position(), buf.limit() - buf.position());
    }

    public void setBuffer(ByteBuffer buf) {
        buffer = buf;
    }


    public ByteBuffer getBuffer() {
        return buffer.duplicate();
    }

    public ByteBuffer getOriBuffer() {
        return buffer;
    }

    public String getString() {
        return new String(buffer.array(), StandardCharsets.UTF_8);
    }

    public byte[] popFetch(int sz) {
        try {
            byte[] fetch = new byte[sz];
            buffer.get(fetch);
            return fetch;
        } catch (BufferUnderflowException bEx) {
            throw new UnpackException(bEx);
        }
    }

    @Override
    public String toString() {
        return buffer.toString();
    }

}

