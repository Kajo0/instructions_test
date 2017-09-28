package instructions.converter;

import java.util.List;
import java.util.stream.Collectors;

import instructions.hyperon.Engine;
import instructions.instructions.data.Data;

public class Converter {

    private final Engine engine;

    public Converter() {
        engine = new Engine();
    }

    public List<Data> convertDumm(List<Data> list) {
        return list.stream()
                .filter(this::isGreenType)
                .peek(this::modifyData)
                .collect(Collectors.toList());
    }

    public List<Data> convertSmart(List<Data> list) {
        List<String> types = getTypes();

        return list.stream()
                .filter(d -> types.contains(d.getCode()))
                .peek(this::modifyData)
                .collect(Collectors.toList());
    }

    private Data modifyData(Data data) {
        data.setCode(data.getCode() + " // 123");
        return data;
    }

    private boolean isGreenType(Data data) {
        return getTypes().contains(data.getCode());
    }

    private List<String> getTypes() {
        return engine.getCollection();
    }

}
