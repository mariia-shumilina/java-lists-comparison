package testing;

import org.apache.commons.lang3.time.StopWatch;

import java.util.List;
import java.util.function.BiConsumer;

  
public class ListCollectionTester {
      
    public <T> double getExecutionTime(BiConsumer<List<T>, Integer> methodToCall, List<T> collection, int numberOfMethodCalls) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (int i = 0; i < numberOfMethodCalls; i++) {
            methodToCall.accept(collection, i);
        }
        stopWatch.stop();
        return stopWatch.getNanoTime() / 1000000.0;
    }
}
