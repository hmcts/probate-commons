package uk.gov.hmcts.reform.probate.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.Stack;
import java.util.TreeSet;

@Component
@Slf4j
public class ParamMapKeyService {
    public ParamMapKeyService() {
    }

    public Set<String> getParams(final Map<String, Object> paramMap) {
        return Collections.unmodifiableSet(this.logParamsMap(paramMap, "", new Stack<>()));
    }

    boolean isNotReentrant(Object input, String currentPrefix, Stack<Integer> parentObjects) {
        final Integer identity = System.identityHashCode(input);
        final boolean isReentrant = parentObjects.contains(identity);
        if (isReentrant) {
            log.warn("Found recursive object at prefix: [{}]", currentPrefix);
        }
        parentObjects.push(identity);
        return ! isReentrant;
    }

    SortedSet<String> logParamsMap(
            final Map<String, Object> paramMap,
            final String prefix,
            final Stack<Integer> parents) {
        final SortedSet<String> collect = new TreeSet<>();

        if (isNotReentrant(paramMap, prefix, parents)) {
            for (final var entry : paramMap.entrySet()) {
                if (entry.getValue() instanceof Map) {
                    final Map<String, Object> child = (Map<String, Object>) entry.getValue();
                    final String nextPrefix = prefix + entry.getKey() + ".";
                    final Set<String> children = logParamsMap(child, nextPrefix, parents);
                    collect.addAll(children);
                } else if (entry.getValue() instanceof List) {
                    final String nextPrefix = prefix + entry.getKey();
                    final List<Object> children = (List<Object>) entry.getValue();
                    final SortedSet<String> childSet = logParamsList(children, nextPrefix, parents);
                    collect.addAll(childSet);
                } else {
                    collect.add(prefix + entry.getKey());
                }
            }
        }
        parents.pop();
        return collect;
    }

    SortedSet<String> logParamsList(
            final List<Object> entries,
            final String prefix,
            final Stack<Integer> parents) {
        final SortedSet<String> collect = new TreeSet<>();

        if (isNotReentrant(entries, prefix, parents)) {
            for (int idx = 0; idx < entries.size(); idx++) {
                final Object entry = entries.get(idx);

                if (entry instanceof Map) {
                    final Map<String, Object> child = (Map<String, Object>) entry;
                    final String nextPrefix = prefix + "[" + idx + "].";
                    final SortedSet<String> children = logParamsMap(child, nextPrefix, parents);
                    collect.addAll(children);
                } else if (entry instanceof List) {
                    final List<Object> child = (List<Object>) entry;
                    final String nextPrefix = prefix + "[" + idx + "]";
                    final SortedSet<String> children = logParamsList(child, nextPrefix, parents);
                    collect.addAll(children);
                } else {
                    collect.add(prefix + "[" + idx + "]");
                }
            }
        }
        parents.pop();
        return collect;
    }
}
