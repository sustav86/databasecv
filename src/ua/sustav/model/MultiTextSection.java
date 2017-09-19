package ua.sustav.model;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by SUSTAVOV on 19.09.2017.
 */
public class MultiTextSection extends Section {
    static final long serialVersionUID = 1L;

    private List<String> values;

    public MultiTextSection(List<String> values) {
        this.values = values;
    }

    public MultiTextSection(String... values) {
        this(new LinkedList<>(Arrays.asList(values)));
    }

    public List<String> getValues() {
        return values;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MultiTextSection that = (MultiTextSection) o;

        return values != null ? values.equals(that.values) : that.values == null;
    }

    @Override
    public int hashCode() {
        return values != null ? values.hashCode() : 0;
    }

}
