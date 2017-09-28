package instructions.instructions;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import com.google.common.base.Stopwatch;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import instructions.converter.Converter;

public class Tester {

    private final List<String> types = ImmutableList.of("A", "B", "C", "D", "E");

    private Converter converter;

    public static void main(String[] args) {
        new Tester().run();
    }

    private Tester() {
        converter = new Converter();
    }

    private List<String> initData(int count) {
        List<String> data = Lists.newArrayList();

        String code = "A";
//        int size = types.size();
//        Random rand = new Random(System.currentTimeMillis());
        for (int i = 0; i < count; ++i) {
//            code = types.get(rand.nextInt(size));
            data.add(code);
        }

//        System.out.println("\tData summary:");
//        data.stream()
//                .collect(Collectors.groupingBy(Data::getCode, Collectors.summingInt(i -> 1)))
//                .forEach((key, value) -> System.out.println(key + " = " + value));
//        System.out.println();

        return data;
    }

    private void run() {
        for (int i = 0; i < 9; ++i) {
            runFor((int) Math.pow(10, i));
        }
    }

    private void runFor(int count) {
        List<String> data = initData(count);

        // init issue
        test(data, converter::convertSmart);
        test(data, converter::convertDumm);

        converter.reset();
        long smart = test(data, converter::convertSmart);
        converter.reset();
        long dumm = test(data, converter::convertDumm);

        String winner = smart < dumm ? "SMART" : "DUMM";

        System.out.println(
                String.format("Result for %10d objects [ms]: smart: %10d , dumm: %10d | winner: %s", count, smart, dumm,
                        winner));
    }

    private long test(List<String> data, Function<List<String>, List<String>> func) {
        Stopwatch timer = Stopwatch.createStarted();
        func.apply(data);
        return timer.elapsed(TimeUnit.MILLISECONDS);
    }

}
