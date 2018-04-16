package com.zzz.study.marshalling;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.marshalling.*;
import org.jboss.marshalling.*;

import java.io.IOException;

public class MyMarshallingFactory {  
    public static MarshallingEncoder buildMarshallingEncoder(){  
        MarshallerFactory factory = new org.jboss.marshalling.serial.SerialMarshallerFactory();
        MarshallingConfiguration configuration = new MarshallingConfiguration();  
        configuration.setVersion(5);  
        MarshallerProvider provider = new DefaultMarshallerProvider(factory, configuration);  
        MarshallingEncoder encoder = new MarshallingEncoder(provider);  
        return encoder;  
    }  
    public static MarshallingDecoder buildMarshallingDecoder(){  
        MarshallerFactory factory = new org.jboss.marshalling.serial.SerialMarshallerFactory();
        MarshallingConfiguration configuration = new MarshallingConfiguration();  
        configuration.setVersion(5);  
        UnmarshallerProvider provider = new DefaultUnmarshallerProvider(factory, configuration);  
        MarshallingDecoder decoder = new MarshallingDecoder(provider);  
        return decoder;  
    }

    public static Marshaller buildMarshaller() throws IOException {
        MarshallerFactory factory = new org.jboss.marshalling.serial.SerialMarshallerFactory();
        MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(5);

        return factory.createMarshaller(configuration);
    }

    public static Unmarshaller buildUnmarshaller() throws IOException {
        MarshallerFactory factory = new org.jboss.marshalling.serial.SerialMarshallerFactory();
        MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(5);

        return factory.createUnmarshaller(configuration);
    }

    public static void writeObject(ByteBuf byteBuf,Object object) throws IOException {
        byteBuf.writeInt(0);
        int lengthPos = byteBuf.writerIndex();
        Marshaller marshaller = MyMarshallingFactory.buildMarshaller();
        ChannelBufferByteOutput output = new ChannelBufferByteOutput(byteBuf);
        marshaller.start(output);
        marshaller.writeObject(object);
        marshaller.finish();
        byteBuf.setInt(lengthPos - 4, byteBuf.writerIndex() - lengthPos);
    }

    public static Object readObject(ByteBuf byteBuf) throws IOException, ClassNotFoundException {

        int objLen = byteBuf.readInt();
        ByteBuf objBuf = byteBuf.slice(byteBuf.readerIndex(), objLen);
        Unmarshaller unmarshaller = MyMarshallingFactory.buildUnmarshaller();
        ChannelBufferByteInput input = new ChannelBufferByteInput(objBuf);
        unmarshaller.start(input);
        Object obj = unmarshaller.readObject();
        unmarshaller.finish();
        byteBuf.readerIndex(byteBuf.readerIndex() + objLen);
        return obj;
    }
} 