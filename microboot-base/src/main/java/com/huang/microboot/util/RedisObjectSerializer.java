package com.huang.microboot.util;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.serializer.support.DeserializingConverter;
import org.springframework.core.serializer.support.SerializingConverter;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
public class RedisObjectSerializer implements RedisSerializer<Object>{
	private Converter<Object, byte[]> serializer=new SerializingConverter();
	private Converter<byte[],Object> deserializer=new DeserializingConverter();
	@Override
	public byte[] serialize(Object obj) throws SerializationException {
		if(obj==null) {
			return new byte[0];
		}
		return this.serializer.convert(obj);
	}
	@Override
	public Object deserialize(byte[] obj) throws SerializationException {
		if(obj==null||obj.length==0) {
			return null;
		}
		return this.deserializer.convert(obj);
	}

}
