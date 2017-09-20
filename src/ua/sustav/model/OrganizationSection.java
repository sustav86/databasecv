package ua.sustav.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by SUSTAVOV
 *  on 14.09.2017.
 */
public class OrganizationSection extends Section implements Serializable  {

    private List<Organization> values;

    public OrganizationSection(Organization... values) {
        this.values = new LinkedList<>(Arrays.asList(values));
    }
}
