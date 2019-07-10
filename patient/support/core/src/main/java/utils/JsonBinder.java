package utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Map;


public class JsonBinder {

    private ObjectMapper mapper;

    public JsonBinder(JsonInclude.Include incl) {
        mapper = new ObjectMapper();
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        // 设置输出包含的属性
        mapper.setSerializationInclusion(incl);
        // 设置输入时忽略JSON字符串中存在而Java对象实际没有的属性
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * 创建输出全部属性到Json字符串的Binder.
     */
    public static JsonBinder buildNormalBinder() {
        return new JsonBinder(Include.ALWAYS);
    }

    /**
     * 创建只输出非空属性到Json字符串的Binder.
     */
    public static JsonBinder buildNonNullBinder() {
        return new JsonBinder(Include.NON_NULL);
    }

    /**
     * 创建只输出初始值被改变的属性到Json字符串的Binder.
     */
    public static JsonBinder buildNonDefaultBinder() {
        return new JsonBinder(Include.NON_DEFAULT);
    }


    /**
     * 如果JSON字符串为Null或"null"字符串,返回Null. 如果JSON字符串为"[]",返回空集合.
     * <p>
     * 如需读取集合如List/Map,且不是List<String>这种简单类型时使用如下语句: List<MyBean> beanList =
     * binder.getMapper().readValue(listString, new
     * TypeReference<List<MyBean>>() {});
     */
    public <T> T fromJson(String jsonString, Class<T> clazz) {
        if (StringUtils.isEmpty(jsonString)) {
            return null;
        }

        try {
            return mapper.readValue(jsonString, clazz);
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * 反序列化复杂Collection如List<Bean>, 先使用函数createCollectionType构造类型,然后调用本函数.
     *
     * @see #createCollectionType(Class, Class...)
     */
    @SuppressWarnings("unchecked")
    public <T> T fromJson(String jsonString, JavaType javaType) {
        if (StringUtils.isEmpty(jsonString)) {
            return null;
        }

        try {
            return (T) mapper.readValue(jsonString, javaType);
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * 构造泛型的Collection Type如:
     * <p>
     * ArrayList<MyBean>, 则调用constructCollectionType(ArrayList.class,MyBean.class)
     * HashMap<String,MyBean>, 则调用(HashMap.class,String.class, MyBean.class)
     * </p>
     */
    public JavaType createCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

    /**
     * 当JSON里只含有Bean的部分属性时，更新一个已存在Bean，只覆盖部分属性.
     */
    @SuppressWarnings("unchecked")
    public <T> T update(String jsonString, T object) {
        try {
            return (T) mapper.readerForUpdating(object).readValue(jsonString);
        } catch (JsonProcessingException e) {
        } catch (IOException e) {
        }
        return null;
    }

    /**
     * 如果对象为Null,返回"null". 如果集合为空集合,返回"[]".
     */
    public String toJson(Object object) {

        try {
            return mapper.writeValueAsString(object);
        } catch (IOException e) {
            return null;
        }
    }

    public static <T> T fromMapToBean(Object o, Class<?> clazz) {

        if (o == null) {
            return null;
        }
        Map<String ,Object> map=  (Map) o;
        Object obj = null;
        try {
            obj = clazz.newInstance();

            // 获取自定义clazz类的全部字段
            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field field : fields) {

                // 获取字段修饰符 需要排除静态及final修饰的字段
                int mod = field.getModifiers();
                if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                    continue;
                }
                // 强制设置为 true 即可以对字段值进行设置 防止一些方法阻碍我们对属性进行赋值
                field.setAccessible(true);

                // map 中如果包含字段名称的 key 则将对应的值放入 clazz 对象对应的字段上
                if (map.containsKey(field.getName())) {
                    field.set(obj, map.get(field.getName()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T) obj;
    }


    /**
     * 将对象转换成JSONP格式字符串
     *
     * @param function jsonp回调方法名
     * @param target   转换为jsonp的对象
     */
    public String toJsonp(String function, Object target) {
        return toJson(new JSONPObject(function, target));
    }

    /**
     * 设置转换日期类型的format pattern,如果不设置默认打印Timestamp毫秒数.
     */
    public void setDateFormat(String pattern) {
        if (!StringUtils.isEmpty(pattern)) {
            DateFormat df = new SimpleDateFormat(pattern);
            mapper.getSerializationConfig().with(df);
            mapper.getDeserializationConfig().with(df);
        }
    }

    /**
     * 取出Mapper做进一步的设置或使用其他序列化API.
     */
    public ObjectMapper getMapper() {
        return mapper;
    }
}
