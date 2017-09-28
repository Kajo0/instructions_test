package instructions.instructions;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import com.google.common.base.Stopwatch;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import instructions.converter.Converter;
import instructions.instructions.data.Data;

public class Tester {

    private final List<String> types = ImmutableList.of("A", "B", "C", "D", "E");

    public static void main(String[] args) {
        new Tester().run();
    }

    private List<Data> initData(int count) {
        List<Data> data = Lists.newArrayList();

        String code;
        int size = types.size();
        Random rand = new Random(System.currentTimeMillis());
        for (int i = 0; i < count; ++i) {
            code = types.get(rand.nextInt(size));
            data.add(Data.of(code));
        }

//        System.out.println("\tData summary:");
//        data.stream()
//                .collect(Collectors.groupingBy(Data::getCode, Collectors.summingInt(i -> 1)))
//                .forEach((key, value) -> System.out.println(key + " = " + value));
//        System.out.println();

        return data;
    }

    private void run() {
        for (int i = 0; i < 8; ++i) {
            runFor((int) Math.pow(10, i));
        }
    }

    private void runFor(int count) {
        List<Data> data = initData(count);

        // init issue
        Converter converter = new Converter();
        test(data, converter::convertDumm);
        test(data, converter::convertSmart);

        converter = new Converter();
        long dumm = test(data, converter::convertDumm);
        converter = new Converter();
        long smart = test(data, converter::convertSmart);

        String winner = smart < dumm ? "SMART" : "DUMM";

        System.out.println(
                String.format("Result for %10d objects [ms]: smart: %10d , dumm: %10d | winner: %s", count, smart, dumm,
                        winner));
    }

    private long test(List<Data> data, Function<List<Data>, List<Data>> func) {
        Stopwatch timer = Stopwatch.createStarted();
        func.apply(data);
        return timer.elapsed(TimeUnit.MILLISECONDS);
    }

}
