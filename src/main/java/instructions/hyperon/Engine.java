package instructions.hyperon;

import java.util.List;

public class Engine {

    private final Connection connection;

    public Engine() {
        connection = new Connection();
    }

    public List<String> getCollection() {
        return connection.fetch();
//        return connection.innerFetch();
    }

}
