package jaxws.soap.endpoint;

import javax.activation.DataHandler;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Created by Ghazi Naceur on 15/06/2017.
 */
@WebService
public interface FileManagerEndPoint {

    void upload(@WebParam(name = "file") DataHandler file);
    DataHandler download();
}
