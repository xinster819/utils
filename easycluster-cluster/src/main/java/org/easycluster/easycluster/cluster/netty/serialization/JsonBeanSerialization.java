package org.easycluster.easycluster.cluster.netty.serialization;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.easycluster.easycluster.cluster.exception.InvalidMessageException;
import org.easycluster.easycluster.core.ByteUtil;
import org.easycluster.easycluster.core.DES;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

public class JsonBeanSerialization implements Serialization {

	private static final Logger	LOGGER			= LoggerFactory.getLogger(JsonBeanSerialization.class);

	private static final String	ENCODING		= "UTF-8";

	private int					dumpBytes		= 256;
	private boolean				isDebugEnabled	= false;
	private byte[]				encryptKey		= null;

	@Override
	public <T> byte[] serialize(T signal) {
		if (signal instanceof byte[]) {
			return (byte[]) signal;
		}

		String text = JSON.toJSONString(signal);
		if (LOGGER.isDebugEnabled() && isDebugEnabled) {
			LOGGER.debug("Serialize object {}, and object as json --> {}", ToStringBuilder.reflectionToString(signal), text);
		}

		byte[] bytes = null;
		try {
			bytes = text.getBytes(ENCODING);
		} catch (UnsupportedEncodingException ignore) {
		}

		if (LOGGER.isDebugEnabled() && isDebugEnabled) {
			LOGGER.debug("Serialize object {}, and object raw bytes --> {}", ToStringBuilder.reflectionToString(signal),
					ByteUtil.bytesAsHexString(bytes, dumpBytes));
		}

		if (bytes.length > 0 && encryptKey != null) {
			try {
				bytes = DES.encryptThreeDESECB(bytes, encryptKey);

				if (LOGGER.isDebugEnabled() && isDebugEnabled) {
					LOGGER.debug("After encryption, object raw bytes --> {}", ByteUtil.bytesAsHexString(bytes, dumpBytes));
				}
			} catch (Exception e) {
				String error = "Failed to encrypt the body due to error " + e.getMessage();
				LOGGER.error(error, e);
				throw new InvalidMessageException(error, e);
			}
		}

		return bytes;
	}

	@Override
	public <T> T deserialize(byte[] bytes, Class<T> type) {
		if (bytes.length > 0 && encryptKey != null) {
			try {
				bytes = DES.decryptThreeDESECB(bytes, encryptKey);
				if (LOGGER.isDebugEnabled() && isDebugEnabled) {
					LOGGER.debug("Deserialize type -> {}, object raw bytes --> {}", type.getName(), ByteUtil.bytesAsHexString(bytes, dumpBytes));
				}
			} catch (Exception e) {
				String error = "Failed to decrypt the bytes due to error " + e.getMessage();
				LOGGER.error(error, e);
				throw new InvalidMessageException(error, e);
			}
		}

		String text = null;
		try {
			text = new String(bytes, ENCODING);
		} catch (UnsupportedEncodingException ingore) {
		}

		T signal = JSON.parseObject(text, type);

		if (LOGGER.isDebugEnabled() && isDebugEnabled) {
			LOGGER.debug("Deserialize object raw bytes --> {}, deserialized object:{}", ByteUtil.bytesAsHexString(bytes, dumpBytes),
					ToStringBuilder.reflectionToString(signal));
		}
		return signal;
	}

	public int getDumpBytes() {
		return dumpBytes;
	}

	public void setDumpBytes(int dumpBytes) {
		this.dumpBytes = dumpBytes;
	}

	public boolean isDebugEnabled() {
		return isDebugEnabled;
	}

	public void setDebugEnabled(boolean isDebugEnabled) {
		this.isDebugEnabled = isDebugEnabled;
	}

	public void setEncryptKey(String encryptKey) {
		if (encryptKey != null) {
			this.encryptKey = encryptKey.getBytes();
		}
	}
}
