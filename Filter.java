package sweproject;

import java.io.*;
import java.util.*;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Filter {

    public static void retweetFilter(HashMap<String, Map<String, Integer>> map) {

        Map<String, Map<String, Integer>> result = map.entrySet()
        .stream()
        .filter(map -> map.getKey().intValue >= 100)
        .collect(Collectors.toMap(map -> map.getKey(), map -map.getValue()));
    }
}