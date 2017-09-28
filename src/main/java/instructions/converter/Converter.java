package instructions.converter;

import java.util.List;
import java.util.stream.Collectors;

import instructions.hyperon.Engine;

public class Converter {

    private final Engine engine;

    public Converter() {
        engine = new Engine();
    }

    public void reset() {
        engine.reset();
    }

    public List<String> convertDumm(List<String> list) {
        return list.stream()
                .filter(this::isGreenType)
                .peek(this::modifyData)
                .collect(Collectors.toList());
    }

    public List<String> convertSmart(List<String> list) {
        List<String> types = getTypes();

        return list.stream()
                .filter(types::contains)
                .peek(this::modifyData)
                .collect(Collectors.toList());
    }

    private String modifyData(String data) {
        return data + " // 123";
    }

    private boolean isGreenType(String data) {
        return getTypes().contains(data);
    }

    private List<String> getTypes() {
        return engine.getCollection();
    }

}
