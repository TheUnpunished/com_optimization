package ru.Yakovlev.Task6;

import java.util.Comparator;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

public class RepetitionComparator implements Comparator<Repetition> {

    @Override
    public int compare(Repetition repetition, Repetition t1) {
        int i = 0;
        boolean found = repetition.getRep().length == t1.getRep().length;
        while (found & i < repetition.getRep().length){
            found = found & t1.getRep().length == repetition.getRep()[i];
            i ++;
        }
        return found? 0 : -1;
    }

    @Override
    public Comparator<Repetition> reversed() {
        return null;
    }

    @Override
    public Comparator<Repetition> thenComparing(Comparator<? super Repetition> other) {
        return null;
    }

    @Override
    public <U> Comparator<Repetition> thenComparing(Function<? super Repetition, ? extends U> keyExtractor, Comparator<? super U> keyComparator) {
        return null;
    }

    @Override
    public <U extends Comparable<? super U>> Comparator<Repetition> thenComparing(Function<? super Repetition, ? extends U> keyExtractor) {
        return null;
    }

    @Override
    public Comparator<Repetition> thenComparingInt(ToIntFunction<? super Repetition> keyExtractor) {
        return null;
    }

    @Override
    public Comparator<Repetition> thenComparingLong(ToLongFunction<? super Repetition> keyExtractor) {
        return null;
    }

    @Override
    public Comparator<Repetition> thenComparingDouble(ToDoubleFunction<? super Repetition> keyExtractor) {
        return null;
    }
}
