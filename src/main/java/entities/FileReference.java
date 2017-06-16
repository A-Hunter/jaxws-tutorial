package entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Ghazi Naceur on 16/06/2017.
 */
@XmlRootElement(name = "FileReference")
@XmlAccessorType(XmlAccessType.FIELD)
public class FileReference {

    @XmlElement
    private long id;

    @XmlElement
    private String title;

    public FileReference() {
        super();
    }

    public FileReference(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
