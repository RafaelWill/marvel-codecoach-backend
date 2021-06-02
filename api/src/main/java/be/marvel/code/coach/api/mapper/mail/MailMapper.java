package be.marvel.code.coach.api.mapper.mail;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MailMapper {

    public Map<String, String> mapObject(Object objectToMap) {
        ObjectMapper oMapper = new ObjectMapper();
        Map<String, Object> tempMap = oMapper.convertValue(objectToMap, Map.class);
        Map<String, String> resultMap = new HashMap<>();
        for (var o : tempMap.entrySet()) {
            resultMap.put(o.getKey(), o.getValue().toString());
        }
        return resultMap;
    }
}
