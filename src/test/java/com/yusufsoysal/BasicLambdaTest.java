package com.yusufsoysal;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

import static org.hamcrest.collection.IsArrayContaining.hasItemInArray;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class BasicLambdaTest {

    @Test
    public void testArraySort(){
        String[] words = {"one", "two", "three", "four", "five"};

        Arrays.sort(words, (first, second) -> Integer.compare(first.length(), second.length()));

        assertThat(words, equalTo(new String[]{"one", "two", "four", "five", "three"}));
    }

    @Test
    public void testMethodReferences(){
        String[] words = {"one", "two", "three", "four", "five"};

        Arrays.sort(words, String::compareToIgnoreCase);

        assertThat(words, equalTo(new String[]{"five", "four", "one", "three", "two"}));
    }

    @Test
    public void testStaticInterfaceMethods() {
        String[] words = {"one", "two", "three", "four", "five"};

        Arrays.sort(words, Comparator.comparing(String::length));

        assertThat(words, equalTo(new String[]{"one", "two", "four", "five", "three"}));
    }

    @Test
    public void listDirectoriesUsingLambda() throws IOException {
        File randomDirectory = new File("./" + Math.random());
        randomDirectory.mkdir();

        try{
            File randomSub = new File(randomDirectory, "randomSub");
            File randomSub2 = new File(randomDirectory, "randomSub2");
            randomSub.mkdir();
            randomSub2.mkdir();

            File[] files = randomDirectory.listFiles(File::isDirectory);

            assertThat(files.length, equalTo(2));
            assertThat(files, hasItemInArray(randomSub));
            assertThat(files, hasItemInArray(randomSub2));
        } finally{
            Arrays.stream(randomDirectory.listFiles())
                    .forEach(File::delete);

            randomDirectory.delete();
        }
    }

}
