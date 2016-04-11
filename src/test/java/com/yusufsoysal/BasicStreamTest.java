package com.yusufsoysal;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class BasicStreamTest {

    private List<String> words = Arrays.asList("Lorem", "ipsum", "dolor", "sit", "amet", "sea", "id", "suscipit", "vivendum", "Eum", "eu", "oporteat", "sententiae", "At", "errem", "epicuri", "oporteat", "pri", "Definiebas", "elaboraret", "deterruisset", "ne", "sea,", "eu", "mea", "oportere", "recteque,", "an", "has", "ludus", "regione", "perfecto", "Ne", "nam", "justo", "partem,", "eu", "eos", "fierent", "platonem");

    @Test
    public void generateStreamTest() {
        Stream<String> numbers = Stream.of("one", "two", "three");
        String[] numberArray = numbers.toArray(String[]::new);

        assertThat(numberArray, equalTo(new String[]{"one", "two", "three"}));
    }

    @Test
    public void generateInfiniteStream() {
        AtomicInteger number = new AtomicInteger(1);
        Stream<Integer> infiniteStream = Stream.generate(number::getAndIncrement);

        Integer[] firstTenNumbers = infiniteStream.limit(10)
                .toArray(Integer[]::new);

        assertThat(firstTenNumbers, equalTo(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}));
    }

    @Test
    public void generateInfiniteStreamWithIteration() {
        Integer number = 1;
        Stream<Integer> infiniteStream = Stream.iterate(number, n -> n + 1);

        Integer[] firstTenNumbers = infiniteStream.limit(10)
                .toArray(Integer[]::new);

        assertThat(firstTenNumbers, equalTo(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}));
    }

    @Test
    public void filterTest() {
        long longWordCount = words.stream()
                .filter(word -> word.length() > 10)
                .count();

        assertThat(longWordCount, equalTo(1L));
    }

    @Test
    public void mapTest() {
        String[] uppercaseWords = words.stream()
                .map(String::toUpperCase)
                .toArray(String[]::new);

        assertThat(uppercaseWords, equalTo(new String[]{"LOREM", "IPSUM", "DOLOR", "SIT", "AMET", "SEA", "ID", "SUSCIPIT", "VIVENDUM", "EUM", "EU", "OPORTEAT", "SENTENTIAE", "AT", "ERREM", "EPICURI", "OPORTEAT", "PRI", "DEFINIEBAS", "ELABORARET", "DETERRUISSET", "NE", "SEA,", "EU", "MEA", "OPORTERE", "RECTEQUE,", "AN", "HAS", "LUDUS", "REGIONE", "PERFECTO", "NE", "NAM", "JUSTO", "PARTEM,", "EU", "EOS", "FIERENT", "PLATONEM"}));
    }

    @Test
    public void mapWithFilterTest() {
        Optional<String> firstLongWord = words.stream()
                .filter(word -> word.length() > 10)
                .map(String::toUpperCase)
                .findFirst();

        assertThat(firstLongWord.get(), equalTo("DETERRUISSET"));
    }

    @Test
    public void flatMapTest() {
        Optional<Character> firstCharacter = words.stream()
                .flatMap(word -> characterStream(word))
                .findFirst();

        assertThat(firstCharacter.get(), equalTo('L'));
    }

    @Test
    public void distinctValuesTest(){
        String[] distinctValues = Stream.of("Hello", "Hello", "World", "World")
                .distinct()
                .toArray(String[]::new);

        assertThat(distinctValues, equalTo(new String[]{"Hello", "World"}));
    }

    @Test
    public void sortValuesTest(){
        String[] sortedWords = words.stream()
                .sorted(Comparator.comparing(String::length).reversed())
                .toArray(String[]::new);

        assertThat(sortedWords, equalTo(new String[]{"deterruisset", "sententiae", "Definiebas", "elaboraret", "recteque,", "suscipit", "vivendum", "oporteat", "oporteat", "oportere", "perfecto", "platonem", "epicuri", "regione", "partem,", "fierent", "Lorem", "ipsum", "dolor", "errem", "ludus", "justo", "amet", "sea,", "sit", "sea", "Eum", "pri", "mea", "has", "nam", "eos", "id", "eu", "At", "ne", "eu", "an", "Ne", "eu"}));
    }

    public static Stream<Character> characterStream(String s) {
        List<Character> result = new ArrayList<>();
        for (char c : s.toCharArray()) {
            result.add(c);
        }
        return result.stream();
    }

}
