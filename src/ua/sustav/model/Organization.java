package ua.sustav.model;

import java.util.Date;
import java.util.List;

/**
 * Created by SUSTAVOV
 *  on 14.09.2017.
 */
public class Organization {
    static final long serialVersionUID = 1L;

    private List<OrganizationPeriod> periods;
    private Link link;

    public class OrganizationPeriod {
        private Date startDate;
        private Date endDate;
        private String position;
        private String content;

        public OrganizationPeriod(Date startDate, Date endDate, String position, String content) {
            this.startDate = startDate;
            this.endDate = endDate;
            this.position = position;
            this.content = content;
        }
    }


}
