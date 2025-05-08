package uk.gov.hmcts.reform.probate.service;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParamMapKeyServiceTest {
    ParamMapKeyService paramMapKeyService = new ParamMapKeyService();

    @Test
    void shouldListKeysAlphabetically() {
        final Map<String, Object> map = Map.of(
                "z", "z",
                "o", "o",
                "y", "y",
                "a", "a");

        final Set<String> result = paramMapKeyService.getParams(map);
        final Iterator<String> iterator = result.iterator();

        assertEquals("a", iterator.next());
        assertEquals("o", iterator.next());
        assertEquals("y", iterator.next());
        assertEquals("z", iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    void shouldDescendMaps() {
        final Map<String, Object> inner = Map.of("inner", "inner");
        final Map<String, Object> mid = Map.of(
                "mid", inner,
                "other", "other");
        final List<Object> list = List.of("");
        final Map<String, Object> outer = Map.of(
                "outer", mid,
                "list", list,
                "other", "other");


        final Set<String> result = paramMapKeyService.getParams(outer);
        final Iterator<String> iterator = result.iterator();

        assertEquals("list[0]", iterator.next());
        assertEquals("other", iterator.next());
        assertEquals("outer.mid.inner", iterator.next());
        assertEquals("outer.other", iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    void shouldDescendLists() {
        final Map<String, Object> inner0 = Map.of("inner0", "inner0");
        final String inner1 = "inner1";
        final List<Object> inner2 = List.of("");
        final List<Object> mid = List.of(inner0, inner1, inner2);
        final Map<String, Object> outer = Map.of(
                "outer", mid,
                "other", "other",
                "inner", inner0);

        final Set<String> result = paramMapKeyService.getParams(outer);
        final Iterator<String> iterator = result.iterator();

        assertEquals("inner.inner0", iterator.next());
        assertEquals("other", iterator.next());
        assertEquals("outer[0].inner0", iterator.next());
        assertEquals("outer[1]", iterator.next());
        assertEquals("outer[2][0]", iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    void shouldNotRedescendPreviouslySeenEntries() {
        final Map<String, Object> recursive = new HashMap<>();
        recursive.put("recursive", recursive);
        recursive.put("other", "other");
        final Map<String, Object> outer = Map.of(
                "outer", recursive,
                "other", "other");

        final Set<String> result = paramMapKeyService.getParams(outer);
        final Iterator<String> iterator = result.iterator();

        assertEquals("other", iterator.next());
        assertEquals("outer.other", iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    void isNotRentrant_willAlwaysPushIdentity() {
        final Object o = new Object();
        final Integer oIdentity = System.identityHashCode(o);

        final Stack<Integer> parents = new Stack<>();

        final boolean first = paramMapKeyService.isNotReentrant(o, "", parents);
        assertTrue(first);
        assertEquals(1, parents.size());

        final boolean second = paramMapKeyService.isNotReentrant(o, "", parents);
        assertFalse(second);
        assertEquals(2, parents.size());
    }

    @Test
    void logParamsMap_leavesParentsSize_ifNotReentrant() {
        final Map<String, Object> map = Map.of(
                "z", "z",
                "o", "o",
                "y", "y",
                "a", "a");
        final Integer mapIdentity = System.identityHashCode(map);

        final Stack<Integer> parents = new Stack<>();
        parents.push(mapIdentity + 1);


        final Set<String> result = paramMapKeyService.logParamsMap(map, "", parents);
        final Iterator<String> iterator = result.iterator();

        assertEquals("a", iterator.next());
        assertEquals("o", iterator.next());
        assertEquals("y", iterator.next());
        assertEquals("z", iterator.next());
        assertFalse(iterator.hasNext());

        assertEquals(1, parents.size());
    }

    @Test
    void logParamsMap_leavesParentsSize_ifReentrant() {
        final Map<String, Object> map = Map.of(
                "z", "z",
                "o", "o",
                "y", "y",
                "a", "a");
        final Integer mapIdentity = System.identityHashCode(map);

        final Stack<Integer> parents = new Stack<>();
        parents.push(mapIdentity);


        final Set<String> result = paramMapKeyService.logParamsMap(map, "", parents);
        final Iterator<String> iterator = result.iterator();

        assertFalse(iterator.hasNext());

        assertEquals(1, parents.size());
    }

    @Test
    void logParamsList_leavesParentsSize_ifNotReentrant() {
        final List<Object> list = List.of("a", "b", "c");
        final Integer listIdentity = System.identityHashCode(list);

        final Stack<Integer> parents = new Stack<>();
        parents.push(listIdentity + 1);


        final Set<String> result = paramMapKeyService.logParamsList(list, "", parents);
        final Iterator<String> iterator = result.iterator();

        assertEquals("[0]", iterator.next());
        assertEquals("[1]", iterator.next());
        assertEquals("[2]", iterator.next());
        assertFalse(iterator.hasNext());

        assertEquals(1, parents.size());
    }

    @Test
    void logParamsList_leavesParentsSize_ifReentrant() {
        final List<Object> list = List.of("a", "b", "c");
        final Integer listIdentity = System.identityHashCode(list);

        final Stack<Integer> parents = new Stack<>();
        parents.push(listIdentity);


        final Set<String> result = paramMapKeyService.logParamsList(list, "", parents);
        final Iterator<String> iterator = result.iterator();

        assertFalse(iterator.hasNext());

        assertEquals(1, parents.size());
    }
}
