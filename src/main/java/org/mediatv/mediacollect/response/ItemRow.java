
package org.mediatv.mediacollect.response;

import lombok.Data;

@Data
public class ItemRow {

    public String title;
    public Image image;
    public Link link;
    public String contentId;
    public String formatId;

}
