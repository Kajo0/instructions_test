package instructions.hyperon;

import java.util.List;

import com.google.common.collect.Lists;

class Connection {

    volatile private List<String> cache;

    void reset() {
        cache = null;
    }

    List<String> fetch() {
        if (cache == null) {
            cache = Lists.newArrayList("A", "B", "C");
        }
        return cache;
    }

    List<String> innerFetch() {
        return innerFetch1();
    }

    List<String> innerFetch1() {
        return innerFetch2();
    }

    List<String> innerFetch2() {
        return innerFetch3();
    }

    List<String> innerFetch3() {
        return fetch();
    }

}
