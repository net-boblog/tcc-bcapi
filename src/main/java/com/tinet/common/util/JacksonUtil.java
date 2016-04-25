package com.tinet.common.util;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Created with IntelliJ IDEA.
 *
 * @version 1.0
 * @author: 罗尧
 * @since 14-8-8 20:42
 * Email:johnny_lx@yahoo.com
 */
public class JacksonUtil {

    private static Logger logger = LoggerFactory.getLogger(JacksonUtil.class);

    public static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        objectMapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
            @Override
            public void serialize(Object value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
                jgen.writeString("");
            }
        });
    }

    public static ObjectMapper getInstance() {
        return objectMapper;
    }

    private static Integer getKeyIndex(String key) {
        int left = key.indexOf("[");
        int right = key.indexOf("]");
        if (left == -1 || right == -1) {
            return null;
        }
        String index = key.substring(left + 1, right);
        try {
            return Integer.valueOf(index);
        } catch (Exception e) {
            return -1;
        }
    }

    private static Object getJsonObject(Object object, String key) {
        if (object instanceof JsonNode) {
            JsonNode obj = (JsonNode) object;
            if (obj.isArray()) {
                List retList = new ArrayList();
                for (int i = 0; i < obj.size(); i++) {
                    JsonNode proc = obj.get(i);
                    if (key.endsWith("[]")) {
                        retList.add(getJsonNode(proc, key.substring(0, key.length() - 2)));
                    } else {
                        Integer index = getKeyIndex(key);
                        if (index == null) {
                            retList.add(getJsonNode(proc, key));
                        } else if (index == -1) {
                            retList.add(getJsonNode(proc, key.substring(0, key.indexOf("["))));
                        } else {
                            retList.add(getJsonNodeIndex(proc, key.substring(0, key.indexOf("[")), index));
                        }

                    }
                }
                return retList;
            } else {
                if (key.endsWith("[]")) {
                    return getJsonNode(obj, key.substring(0, key.length() - 2));
                } else {
                    Integer index = getKeyIndex(key);
                    if (index == null) {
                        return getJsonNode(obj, key);
                    } else if (index == -1) {
                        return getJsonNode(obj, key.substring(0, key.indexOf("[")));
                    } else {
                        return getJsonNodeIndex(obj, key.substring(0, key.indexOf("[")), index);
                    }
                }
            }
        } else if (object instanceof List) {
            List list = (List) object;
            List retList = new ArrayList();
            for (int i = 0; i < list.size(); i++) {
                Object jo = list.get(i);
                Object ret = getJsonObject(jo, key);
                if (ret instanceof List) {
                    retList.addAll((List) ret);
                } else {
                    retList.add(ret);
                }
            }
            return retList;
        }
        return null;
    }

    public static JsonNode getJsonNode(JsonNode obj, String key) {
        if (obj == null) {
            return null;
        }
        return obj.findValue(key);
    }

    public static JsonNode getJsonNodeIndex(JsonNode obj, String key, int index) {
        if (obj == null || index < 0) {
            return null;
        }

        JsonNode node = obj.findValue(key);

        if (node != null && node.isArray() && node.size() > index) {
            return node.get(index);
        } else {
            return null;
        }
    }

    /**
     * 根据json对象直接获取指定路径keys对应的json对象
     *
     * @param obj
     * @param keys 如 new String[]{"A","B[]","C[1]","D"}   其中B[]表示取全部数组，C[1]表示取index为1的数据，从0开始
     * @return
     */
    public static List getJsonObject(JsonNode obj, String... keys) {
        return getJsonObject(obj, null, keys);
    }

    /**
     * get JsonNode value
     * @param jsonObject
     * @param key
     * @return
     */
    public static JsonNode getJsonValue(JsonNode jsonObject, String key) {
        if (jsonObject == null || key == null || !jsonObject.has(key)) {
            return null;
        }
        return jsonObject.findValue(key);
    }
    /**
     * 
     * @Description: 获取JsonNode中key对应的文本值
     * @param @param jsonObject
     * @param @param key
     * @param @return   
     * @return String  
     * @throws
     * @author 王志满
     * @date 2015-11-2
     */
    public static String getJsonNodeValueText(JsonNode jsonNode, String key){
    	 if (jsonNode == null || key == null || !jsonNode.has(key)) {
             return null;
         }
    	return jsonNode.get(key).asText();
    }
    
    /**
     * 
     * @Description: TODO
     * @param @param coment
     * @param @return   
     * @return JsonNode  
     * @throws
     * @author  wangzhiman
     * @date 2015-11-17
     */
    public static JsonNode convertJsonTextToJsonNode(String coment){
    	JsonNode jsonNode=null;
    	try {
    		if(StringUtil.isNotEmpty(coment)){
    			jsonNode=objectMapper.readValue(coment,JsonNode.class);
    		}
		} catch (JsonParseException e) {
			 logger.error("类型转换错误：" + e.getMessage(), e);
             throw new RuntimeException(e);
		} catch (JsonMappingException e) {
			logger.error("映射转换错误：" + e.getMessage(), e);
            throw new RuntimeException(e);
		} catch (IOException e) {
			logger.error("IO异常" + e.getMessage(), e);
            throw new RuntimeException(e);
		}
    	return jsonNode;
    	
    }
    
    /**
     * 
     * @Description: TODO
     * @param @param listJsonNodes
     * @param @return   
     * @return List<JsonNode>  
     * @throws
     * @author 王志满
     * @date 2015-11-2
     */
    public static List<JsonNode> getListJsonNodeFromStringList(String listJsonNodes){
    	if(listJsonNodes==null||listJsonNodes==""){
    		return null;
    	}
    	List<JsonNode> listNodes=new ArrayList<JsonNode>();
    	try {
    		listNodes=objectMapper.readValue(listJsonNodes,new TypeReference<List<JsonNode>>(){});
		} catch (JsonParseException e) {
			 logger.error("类型转换错误：" + e.getMessage(), e);
             throw new RuntimeException(e);
		} catch (JsonMappingException e) {
			 logger.error("映射转换错误：" + e.getMessage(), e);
             throw new RuntimeException(e);
		} catch (IOException e) {
			 logger.error("IO异常" + e.getMessage(), e);
             throw new RuntimeException(e);
		}
    	return listNodes;
    }
    /**
     * 根据json对象直接获取指定路径keys对应的json对象
     *
     * @param obj
     * @param clazz 指定转化后的对象
     * @param keys  如 new String[]{"A","B[]","C[1]","D"}   其中B[]表示取全部数组，C[1]表示取index为1的数据，从0开始
     * @return
     */
    public static <T> List<T> getJsonObject(JsonNode obj, Class<T> clazz, String... keys) {
        if (obj == null || keys == null || keys.length == 0) {
            return null;
        }
        Object proc = obj;
        for (String key : keys) {
            if (proc == null) return null;
            if (key == null) {
                throw new RuntimeException("Json转换时发现无效的键值");
            } else {
                proc = getJsonObject(proc, key);
            }
        }
        if (proc instanceof List) {
            if (clazz != null) {
                try {
                    List<T> ret = new ArrayList<T>();
                    for (Object o : (List) proc) {
//                        ret.add(getInstance().readValue((JsonNode) o, clazz));
                    }
                    return ret;
                } catch (Exception e) {
                    logger.error("类型转换错误：" + e.getMessage(), e);
                    throw new RuntimeException(e);
                }
            } else {
                return (List) proc;
            }
        } else {
            try {
                if (clazz != null) {
                    List<T> ret = new ArrayList<T>();
//                    ret.add(getInstance().readValue((JsonNode) proc, clazz));
                    return ret;
                } else {
                    List ret = new ArrayList();
                    ret.add(proc);
                    return ret;
                }
            } catch (Exception e) {
                logger.error("类型转换错误：" + e.getMessage(), e);
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 使用泛型方法，把json字符串转换为相应的JavaBean对象。
     *      转换为普通JavaBean：readValue(json,Student.class)
     *      转换为List:readValue(json,List.class).但是如果我们想把json转换为特定类型的List，比如List<Student>，就不能直接进行转换了。
     *          因为readValue(json,List.class)返回的其实是List<Map>类型，你不能指定readValue()的第二个参数是List<Student>.class，所以不能直接转换。
     *          我们可以把readValue()的第二个参数传递为Student[].class.然后使用Arrays.asList();方法把得到的数组转换为特定类型的List。
     *      转换为Map：readValue(json,Map.class)
     * 我们使用泛型，得到的也是泛型
     *
     * @param content
     *            要转换的JavaBean类型
     * @param valueType
     *            原始json字符串数据
     * @return JavaBean对象
     */
    public static <T> T readValue(String content, Class<T> valueType) {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }
        try {
            return objectMapper.readValue(content, valueType);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {
        ObjectNode jo = new ObjectNode(JsonNodeFactory.instance);
        ObjectNode joA = new ObjectNode(JsonNodeFactory.instance);
        ArrayNode jrA1 = new ArrayNode(JsonNodeFactory.instance);
        ObjectNode joAA1 = new ObjectNode(JsonNodeFactory.instance);
        ObjectNode joAA1A = new ObjectNode(JsonNodeFactory.instance);
        joAA1A.put("a1", "aa1a1");
        joAA1A.put("a2", "aa1a2");
        joAA1A.put("a3", "aa1a3");
        ObjectNode joAA1B = new ObjectNode(JsonNodeFactory.instance);
        joAA1B.put("b1", "aa1b1");
        joAA1B.put("b2", "aa1b2");
        joAA1B.put("b3", "aa1b3");
        joAA1.put("asp", joAA1A);
        joAA1.put("bsp", joAA1B);
        ObjectNode joAA2 = new ObjectNode(JsonNodeFactory.instance);
        ObjectNode joAA2A = new ObjectNode(JsonNodeFactory.instance);
        joAA2A.put("a1", "aa2a1");
        joAA2A.put("a2", "aa2a2");
        joAA2A.put("a3", "aa2a3");
        ObjectNode joAA2B = new ObjectNode(JsonNodeFactory.instance);
        joAA2B.put("b1", "aa2b1");
        joAA2B.put("b2", "aa2b2");
        joAA2B.put("b3", "aa2b3");
        joAA2.put("asp", joAA2A);
        joAA2.put("bsp", joAA2B);


        jrA1.add(joAA1);
        jrA1.add(joAA2);
        joA.put("ar", jrA1);
        joAA1.put("a", joAA1A);
        joAA1.put("b", joAA1B);
        joAA2.put("a", joAA2A);
        joAA2.put("b", joAA2B);
        ObjectNode joB = new ObjectNode(JsonNodeFactory.instance);
        ObjectNode joBA = new ObjectNode(JsonNodeFactory.instance);
        joBA.put("a1", "ba1");
        joBA.put("a2", "ba2");
        JsonNode joBB = new ObjectNode(JsonNodeFactory.instance);
        joBA.put("b1", "bb1");
        joBA.put("b2", "bb2");

        joB.put("At", joBA);
        joB.put("ef", joBB);
        jo.put("A", joA);
        jo.put("B", joB);

        System.out.println(jo.toString());

        List list = getJsonObject(jo, new String[]{"A", "ar[0]", "asp"});
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                ObjectNode jobb = (ObjectNode) list.get(i);
                jobb.put("a1", "test");
                System.out.println(list.get(i));
            }
        }

        List<String> lista = getJsonObject(jo,String.class, new String[]{"A", "ar[0]", "asp","a1"});
        if (lista != null) {
            for (int i = 0; i < lista.size(); i++) {
                System.out.println(lista.get(i));
            }
        }
    }
}
