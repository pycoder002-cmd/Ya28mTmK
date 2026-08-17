package io.swagger.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import io.swagger.client.model.Data;
import io.swagger.client.model.Data1;
import io.swagger.client.model.Data10;
import io.swagger.client.model.Data11;
import io.swagger.client.model.Data12;
import io.swagger.client.model.Data13;
import io.swagger.client.model.Data14;
import io.swagger.client.model.Data15;
import io.swagger.client.model.Data16;
import io.swagger.client.model.Data17;
import io.swagger.client.model.Data18;
import io.swagger.client.model.Data19;
import io.swagger.client.model.Data2;
import io.swagger.client.model.Data20;
import io.swagger.client.model.Data21;
import io.swagger.client.model.Data22;
import io.swagger.client.model.Data23;
import io.swagger.client.model.Data24;
import io.swagger.client.model.Data3;
import io.swagger.client.model.Data4;
import io.swagger.client.model.Data5;
import io.swagger.client.model.Data6;
import io.swagger.client.model.Data7;
import io.swagger.client.model.Data8;
import io.swagger.client.model.Data9;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

/* loaded from: classes2.dex */
public class JsonUtil {
    public static GsonBuilder gsonBuilder = new GsonBuilder();

    static {
        gsonBuilder.serializeNulls();
        gsonBuilder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        gsonBuilder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() { // from class: io.swagger.client.JsonUtil.1
            @Override // com.google.gson.JsonDeserializer
            public Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                return new Date(jsonElement.getAsJsonPrimitive().getAsLong());
            }
        });
    }

    public static <T> T deserializeToList(String str, Class cls) {
        return (T) getGson().fromJson(str, getListTypeForDeserialization(cls));
    }

    public static <T> T deserializeToObject(String str, Class cls) {
        return (T) getGson().fromJson(str, getTypeForDeserialization(cls));
    }

    public static Gson getGson() {
        return gsonBuilder.create();
    }

    public static Type getListTypeForDeserialization(Class cls) {
        String simpleName = cls.getSimpleName();
        return "Data".equalsIgnoreCase(simpleName) ? new TypeToken<List<Data>>() { // from class: io.swagger.client.JsonUtil.2
        }.getType() : "Data1".equalsIgnoreCase(simpleName) ? new TypeToken<List<Data1>>() { // from class: io.swagger.client.JsonUtil.3
        }.getType() : "Data10".equalsIgnoreCase(simpleName) ? new TypeToken<List<Data10>>() { // from class: io.swagger.client.JsonUtil.4
        }.getType() : "Data11".equalsIgnoreCase(simpleName) ? new TypeToken<List<Data11>>() { // from class: io.swagger.client.JsonUtil.5
        }.getType() : "Data12".equalsIgnoreCase(simpleName) ? new TypeToken<List<Data12>>() { // from class: io.swagger.client.JsonUtil.6
        }.getType() : "Data13".equalsIgnoreCase(simpleName) ? new TypeToken<List<Data13>>() { // from class: io.swagger.client.JsonUtil.7
        }.getType() : "Data14".equalsIgnoreCase(simpleName) ? new TypeToken<List<Data14>>() { // from class: io.swagger.client.JsonUtil.8
        }.getType() : "Data15".equalsIgnoreCase(simpleName) ? new TypeToken<List<Data15>>() { // from class: io.swagger.client.JsonUtil.9
        }.getType() : "Data16".equalsIgnoreCase(simpleName) ? new TypeToken<List<Data16>>() { // from class: io.swagger.client.JsonUtil.10
        }.getType() : "Data17".equalsIgnoreCase(simpleName) ? new TypeToken<List<Data17>>() { // from class: io.swagger.client.JsonUtil.11
        }.getType() : "Data18".equalsIgnoreCase(simpleName) ? new TypeToken<List<Data18>>() { // from class: io.swagger.client.JsonUtil.12
        }.getType() : "Data19".equalsIgnoreCase(simpleName) ? new TypeToken<List<Data19>>() { // from class: io.swagger.client.JsonUtil.13
        }.getType() : "Data2".equalsIgnoreCase(simpleName) ? new TypeToken<List<Data2>>() { // from class: io.swagger.client.JsonUtil.14
        }.getType() : "Data20".equalsIgnoreCase(simpleName) ? new TypeToken<List<Data20>>() { // from class: io.swagger.client.JsonUtil.15
        }.getType() : "Data21".equalsIgnoreCase(simpleName) ? new TypeToken<List<Data21>>() { // from class: io.swagger.client.JsonUtil.16
        }.getType() : "Data22".equalsIgnoreCase(simpleName) ? new TypeToken<List<Data22>>() { // from class: io.swagger.client.JsonUtil.17
        }.getType() : "Data23".equalsIgnoreCase(simpleName) ? new TypeToken<List<Data23>>() { // from class: io.swagger.client.JsonUtil.18
        }.getType() : "Data24".equalsIgnoreCase(simpleName) ? new TypeToken<List<Data24>>() { // from class: io.swagger.client.JsonUtil.19
        }.getType() : "Data3".equalsIgnoreCase(simpleName) ? new TypeToken<List<Data3>>() { // from class: io.swagger.client.JsonUtil.20
        }.getType() : "Data4".equalsIgnoreCase(simpleName) ? new TypeToken<List<Data4>>() { // from class: io.swagger.client.JsonUtil.21
        }.getType() : "Data5".equalsIgnoreCase(simpleName) ? new TypeToken<List<Data5>>() { // from class: io.swagger.client.JsonUtil.22
        }.getType() : "Data6".equalsIgnoreCase(simpleName) ? new TypeToken<List<Data6>>() { // from class: io.swagger.client.JsonUtil.23
        }.getType() : "Data7".equalsIgnoreCase(simpleName) ? new TypeToken<List<Data7>>() { // from class: io.swagger.client.JsonUtil.24
        }.getType() : "Data8".equalsIgnoreCase(simpleName) ? new TypeToken<List<Data8>>() { // from class: io.swagger.client.JsonUtil.25
        }.getType() : "Data9".equalsIgnoreCase(simpleName) ? new TypeToken<List<Data9>>() { // from class: io.swagger.client.JsonUtil.26
        }.getType() : new TypeToken<List<Object>>() { // from class: io.swagger.client.JsonUtil.27
        }.getType();
    }

    public static Type getTypeForDeserialization(Class cls) {
        String simpleName = cls.getSimpleName();
        return "Data".equalsIgnoreCase(simpleName) ? new TypeToken<Data>() { // from class: io.swagger.client.JsonUtil.28
        }.getType() : "Data1".equalsIgnoreCase(simpleName) ? new TypeToken<Data1>() { // from class: io.swagger.client.JsonUtil.29
        }.getType() : "Data10".equalsIgnoreCase(simpleName) ? new TypeToken<Data10>() { // from class: io.swagger.client.JsonUtil.30
        }.getType() : "Data11".equalsIgnoreCase(simpleName) ? new TypeToken<Data11>() { // from class: io.swagger.client.JsonUtil.31
        }.getType() : "Data12".equalsIgnoreCase(simpleName) ? new TypeToken<Data12>() { // from class: io.swagger.client.JsonUtil.32
        }.getType() : "Data13".equalsIgnoreCase(simpleName) ? new TypeToken<Data13>() { // from class: io.swagger.client.JsonUtil.33
        }.getType() : "Data14".equalsIgnoreCase(simpleName) ? new TypeToken<Data14>() { // from class: io.swagger.client.JsonUtil.34
        }.getType() : "Data15".equalsIgnoreCase(simpleName) ? new TypeToken<Data15>() { // from class: io.swagger.client.JsonUtil.35
        }.getType() : "Data16".equalsIgnoreCase(simpleName) ? new TypeToken<Data16>() { // from class: io.swagger.client.JsonUtil.36
        }.getType() : "Data17".equalsIgnoreCase(simpleName) ? new TypeToken<Data17>() { // from class: io.swagger.client.JsonUtil.37
        }.getType() : "Data18".equalsIgnoreCase(simpleName) ? new TypeToken<Data18>() { // from class: io.swagger.client.JsonUtil.38
        }.getType() : "Data19".equalsIgnoreCase(simpleName) ? new TypeToken<Data19>() { // from class: io.swagger.client.JsonUtil.39
        }.getType() : "Data2".equalsIgnoreCase(simpleName) ? new TypeToken<Data2>() { // from class: io.swagger.client.JsonUtil.40
        }.getType() : "Data20".equalsIgnoreCase(simpleName) ? new TypeToken<Data20>() { // from class: io.swagger.client.JsonUtil.41
        }.getType() : "Data21".equalsIgnoreCase(simpleName) ? new TypeToken<Data21>() { // from class: io.swagger.client.JsonUtil.42
        }.getType() : "Data22".equalsIgnoreCase(simpleName) ? new TypeToken<Data22>() { // from class: io.swagger.client.JsonUtil.43
        }.getType() : "Data23".equalsIgnoreCase(simpleName) ? new TypeToken<Data23>() { // from class: io.swagger.client.JsonUtil.44
        }.getType() : "Data24".equalsIgnoreCase(simpleName) ? new TypeToken<Data24>() { // from class: io.swagger.client.JsonUtil.45
        }.getType() : "Data3".equalsIgnoreCase(simpleName) ? new TypeToken<Data3>() { // from class: io.swagger.client.JsonUtil.46
        }.getType() : "Data4".equalsIgnoreCase(simpleName) ? new TypeToken<Data4>() { // from class: io.swagger.client.JsonUtil.47
        }.getType() : "Data5".equalsIgnoreCase(simpleName) ? new TypeToken<Data5>() { // from class: io.swagger.client.JsonUtil.48
        }.getType() : "Data6".equalsIgnoreCase(simpleName) ? new TypeToken<Data6>() { // from class: io.swagger.client.JsonUtil.49
        }.getType() : "Data7".equalsIgnoreCase(simpleName) ? new TypeToken<Data7>() { // from class: io.swagger.client.JsonUtil.50
        }.getType() : "Data8".equalsIgnoreCase(simpleName) ? new TypeToken<Data8>() { // from class: io.swagger.client.JsonUtil.51
        }.getType() : "Data9".equalsIgnoreCase(simpleName) ? new TypeToken<Data9>() { // from class: io.swagger.client.JsonUtil.52
        }.getType() : new TypeToken<Object>() { // from class: io.swagger.client.JsonUtil.53
        }.getType();
    }

    public static String serialize(Object obj) {
        return getGson().toJson(obj);
    }
}
