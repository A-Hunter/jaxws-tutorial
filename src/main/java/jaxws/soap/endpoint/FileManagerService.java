package jaxws.soap.endpoint;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlMimeType;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Ghazi Naceur on 15/06/2017.
 */
@WebService(endpointInterface = "jaxws.soap.endpoint.FileManagerEndPoint", serviceName ="FileManagerEndPoint")
public class FileManagerService implements FileManagerEndPoint {

    private Path path = Paths.get("E:\\GitHubRepositories\\jaxws-tutorial\\src\\main\\resources\\test.txt");

    @WebMethod
    public void upload(DataHandler file) {
        try(InputStream input = file.getInputStream();
            OutputStream output = new FileOutputStream(new File(path.toString()))) {
                byte[] binary = new byte[100000];
                int reads = 0;
                while ((reads = input.read(binary)) != -1){
                    output.write(binary,0,reads);
                }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @WebMethod
    public @XmlMimeType("application/octet-stream") DataHandler download() {
        DataSource source = new FileDataSource(new File(path.toString()));
        return new DataHandler(source);
    }
}
