package com.yusufsoysal;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class StreamTerminalOperationsTest {

    private List<String> words = Arrays.asList("John", "James", "Robert", "Michael", "William", "Joseph", "Mark", "Christopher", "Max");

    @Test
    public void countTest(){
        long wordCount = words.stream()
                .count();

        assertThat(wordCount, equalTo(9L));
    }

    @Test
    public void minTest(){
        Optional<String> shortestName = words.stream()
                .min(Comparator.comparing(String::length));

        assertThat(shortestName.get(), equalTo("Max"));
    }

    @Test
    public void maxTest(){
        Optional<String> shortestName = words.stream()
                .max(Comparator.comparing(String::length));

        assertThat(shortestName.get(), equalTo("Christopher"));
    }

    @Test
    public void findFirstTest(){
        Optional<String> firstWordWithJ = words.stream()
                .filter(word -> word.startsWith("J"))
                .findFirst();

        assertThat(firstWordWithJ.get(), equalTo("John"));
    }

    @Test
    public void anyMatchTest(){
        boolean wordStartsWithMExists = words.stream()
                .anyMatch(word -> word.startsWith("M"));

        assertThat(wordStartsWithMExists, equalTo(true));
    }

    @Test
    public void noneMatchTest(){
        boolean wordStartsWithYExists = words.stream()
                .noneMatch(word -> word.startsWith("Y"));

        assertThat(wordStartsWithYExists, equalTo(true));
    }

    @Test
    public void reduceTest(){
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        Optional<Integer> total = numbers.stream()
                                        .reduce((x, y) -> x + y);

        assertThat(total.get(), equalTo(55));
    }

    @Test
    public void reduceTestWithMethodReference(){
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        Optional<Integer> total = numbers.stream()
                .reduce(Integer::sum);

        assertThat(total.get(), equalTo(55));
    }

    @Test
    public void reduceTestWithIdentityValue(){
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        Integer total = numbers.stream()
                .reduce(10, Integer::sum);

        assertThat(total, equalTo(65));
    }


}
