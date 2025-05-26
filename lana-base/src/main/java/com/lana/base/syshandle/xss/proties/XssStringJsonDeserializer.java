package com.lana.base.syshandle.xss.proties;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @author Liuyulet
 * @version 1.0
 * @data 2024/7/7 21:13
 */
@Slf4j
@AllArgsConstructor
public class XssStringJsonDeserializer extends StringDeserializer {

    private final XssCleaner xssCleaner;

    @Override
    public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        if (p.hasToken(JsonToken.VALUE_STRING)) {
            return xssCleaner.clean(p.getText());
        }
        JsonToken t = p.currentToken();
        if (t == JsonToken.START_ARRAY) {
            return _deserializeFromArray(p, ctxt);
        }
        if (t == JsonToken.VALUE_EMBEDDED_OBJECT) {
            Object ob = p.getEmbeddedObject();
            if (ob == null) {
                return null;
            }
            if (ob instanceof byte[]) {
                return ctxt.getBase64Variant().encode((byte[]) ob, false);
            }
            return ob.toString();
        }
        if (t == JsonToken.START_OBJECT) {
            return ctxt.extractScalarFromObject(p, this, _valueClass);
        }

        if (t.isScalarValue()) {
            String text = p.getValueAsString();
            return xssCleaner.clean(text);
        }
        return (String) ctxt.handleUnexpectedToken(_valueClass, p);
    }

}
