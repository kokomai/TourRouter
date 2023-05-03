package com.tourrouter.jpa.converter;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.codec.Hex;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.extern.slf4j.Slf4j;

@Converter
@Slf4j
public class PasswordConverter implements AttributeConverter<String, String>{
	@Value("${spring.database.column.encrypt.key:}")
	private String key;
	
	private Cipher encryptCipher;
	private Cipher decryptCipher;
	
	@PostConstruct
	public void init() throws Exception {
		encryptCipher = Cipher.getInstance("AES");
		encryptCipher.init(Cipher.ENCRYPT_MODE, generateMySQLAESKey(key, "UTF-8"));
		decryptCipher = Cipher.getInstance("AES");
		decryptCipher.init(Cipher.DECRYPT_MODE, generateMySQLAESKey(key, "UTF-8"));
	}
	
	
	public static SecretKeySpec generateMySQLAESKey(final String key, final String encoding) {
		try {
			final byte[] finalKey = new byte[16];
			int i = 0;
			for(byte b : key.getBytes(encoding))
				finalKey[i++%16] ^=b;
			return new SecretKeySpec(finalKey, "AES");
		} catch(UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}




	@Override
	public String convertToDatabaseColumn(String attribute) {
		try {
			return new String(Hex.encode(encryptCipher.doFinal(attribute.getBytes(StandardCharsets.UTF_8))));
		} catch(Exception e) {
			log.error("convert to database colume error :", e);
		}
		return null;
	}




	@Override
	public String convertToEntityAttribute(String dbData) {
		try {
			return new String(decryptCipher.doFinal(Hex.decode(dbData)));
		} catch(Exception e) {
			log.error("convert to entity attribute error :", e);
		}
		return null;
	}
}
