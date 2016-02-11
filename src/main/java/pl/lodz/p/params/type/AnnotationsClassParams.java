package pl.lodz.p.params.type;

import pl.lodz.p.util.Utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by maciek on 10/01/16.
 */
public abstract class AnnotationsClassParams {

    protected Map<String, Object> result;

    void initializeResultMapTemplate() {
        this.result = new HashMap<>();
        this.initializeFields();
        this.reduceUselessDefaultFields();
    }

    abstract void initializeFields();

    void reduceUselessDefaultFields() {
        Iterator<Map.Entry<String, Object>> i = result.entrySet().iterator();
        while (i.hasNext()) {
            Map.Entry<String, Object> entry = i.next();
            String key = entry.getKey();
            Object value = entry.getValue();
            Object defaultValue = Utils.getAnnotationDefault(getType(), entry.getKey());
            if (!isFieldRequired(key) && (value != null && value.equals(defaultValue))) {
                i.remove();
            }
        }
    }

    abstract Class getType();

    public Map<String, Object> getAnnotationsMembers() {
        this.initializeResultMapTemplate();
        return this.result;
    }

    protected boolean isFieldRequired(String key) {
        return getRequiredFields().contains(key);
    }

    abstract List<String> getRequiredFields();
}
