package jaxws.soap.endpoint;

import entities.FileReference;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.annotation.PostConstruct;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlMimeType;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Ghazi Naceur on 15/06/2017.
 */
@WebService(endpointInterface = "jaxws.soap.endpoint.FileManagerEndPoint", serviceName = "FileManagerEndPoint")
public class FileManagerService implements FileManagerEndPoint {

    static List<FileReference> references = new ArrayList<>();

    private Path path = Paths.get("E:\\GitHubRepositories\\jaxws-tutorial\\src\\main\\resources\\test.txt");

    static {
        references = initialize();
    }

    static List<FileReference> initialize(){
        List<FileReference> refs = new ArrayList<>();
        refs.add(new FileReference((long) 1, "ref1"));
        refs.add(new FileReference((long) 2, "ref2"));
        refs.add(new FileReference((long) 3, "ref3"));
        return refs;
    }

    @WebMethod
    public void upload(DataHandler file) {
        try (InputStream input = file.getInputStream();
             OutputStream output = new FileOutputStream(new File(path.toString()))) {
            byte[] binary = new byte[100000];
            int reads = 0;
            while ((reads = input.read(binary)) != -1) {
                output.write(binary, 0, reads);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @WebMethod
    public @XmlMimeType("application/octet-stream")
    DataHandler download() {
        DataSource source = new FileDataSource(new File(path.toString()));
        return new DataHandler(source);
    }

    @WebMethod
    public void create(FileReference reference) {
        references.add(reference);
        references.forEach(ref -> System.out.println(ref.getId() + " - " + ref.getTitle()));
    }

    @WebMethod
    public void update(FileReference reference) {
        int index = references.indexOf(reference);
//        references.set(index, reference);
        delete(reference.getId());
        create(reference);
        references.forEach(ref -> System.out.println(ref.getId() + " - " + ref.getTitle()));
    }

    @WebMethod
    public FileReference retrieve(long id) {
        for (FileReference reference : references) {
            if (reference.getId() == id) {
                return reference;
            }
        }
        return null;
    }

    @WebMethod
    public void delete(long id) {
        for (FileReference reference : references) {
            if (reference.getId() == id) {
                references.remove(reference);
            }
        }
    }

    @WebMethod
    public Collection<FileReference> loadAll() {
        references.forEach(ref -> System.out.println(ref.getId() + " - " + ref.getTitle()));
        return references;
    }
}
