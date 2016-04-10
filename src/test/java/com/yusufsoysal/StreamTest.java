package com.yusufsoysal;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class StreamTest {

    private List<String> words = Arrays.asList("Lorem","ipsum","dolor","sit","amet","sea","id","suscipit","vivendum","Eum","eu","oporteat","sententiae","At","errem","epicuri","oporteat","pri","Definiebas","elaboraret","deterruisset","ne","sea,","eu","mea","oportere","recteque,","an","has","ludus","regione","perfecto","Ne","nam","justo","partem,","eu","eos","fierent","platonem");

    @Test
    public void generateStreamTest(){
        Stream<String> numbers = Stream.of("one", "two", "three");
        String[] numberArray = numbers.toArray(String[]::new);

        assertThat(numberArray, equalTo(new String[]{"one", "two", "three"}));
    }

    @Test
    public void generateInfiniteStream(){
        AtomicInteger number = new AtomicInteger(1);
        Stream<Integer> infiniteStream = Stream.generate(number::getAndIncrement);

        Integer[] firstTenNumbers = infiniteStream.limit(10)
                .toArray(Integer[]::new);

        assertThat(firstTenNumbers, equalTo(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}));
    }

    @Test
    public void filterTest(){
        long longWordCount = words.stream()
                .filter(word -> word.length() > 10)
                .count();

        assertThat(longWordCount, equalTo(1L));
    }

    @Test
    public void mapTest(){
        String[] uppercaseWords = words.stream()
                .map(String::toUpperCase)
                .toArray(String[]::new);

        assertThat(uppercaseWords, equalTo(new String[]{"LOREM","IPSUM","DOLOR","SIT","AMET","SEA","ID","SUSCIPIT","VIVENDUM","EUM","EU","OPORTEAT","SENTENTIAE","AT","ERREM","EPICURI","OPORTEAT","PRI","DEFINIEBAS","ELABORARET","DETERRUISSET","NE","SEA,","EU","MEA","OPORTERE","RECTEQUE,","AN","HAS","LUDUS","REGIONE","PERFECTO","NE","NAM","JUSTO","PARTEM,","EU","EOS","FIERENT","PLATONEM"}));
    }

    @Test
    public void mapWithFilterTest(){
        Optional<String> firstLongWord = words.stream()
                .filter(word -> word.length() > 10)
                .map(String::toUpperCase)
                .findFirst();

        assertThat(firstLongWord.get(), equalTo("DETERRUISSET"));
    }

}
