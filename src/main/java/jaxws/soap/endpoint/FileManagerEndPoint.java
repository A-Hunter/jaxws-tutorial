package jaxws.soap.endpoint;

import entities.FileReference;

import javax.activation.DataHandler;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.Collection;

/**
 * Created by Ghazi Naceur on 15/06/2017.
 */
@WebService
public interface FileManagerEndPoint {

    void upload(@WebParam(name = "file") DataHandler file);
    DataHandler download();
    void create(@WebParam(name = "reference") FileReference reference);
    void update(@WebParam(name = "reference") FileReference reference);
    FileReference retrieve(@WebParam(name = "id") long id);
    void delete(@WebParam(name = "id") long id);
    Collection<FileReference> loadAll();
}
