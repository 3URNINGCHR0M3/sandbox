package net.forje.taskcoach.effortreport;

import net.forje.taskcoach.jaxb.Tasks;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;

public class FileMarshaller {

    private JAXBContext jaxbContext;

    public FileMarshaller() throws JAXBException {
        jaxbContext = JAXBContext.newInstance("net.forje.taskcoach.jaxb");
    }


    public void marshall (File file, Tasks tasks) throws Exception {

        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream(file);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.marshal(tasks, fileOutputStream);
        } finally {

            if (fileOutputStream != null) {
                try {
                    fileOutputStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }




        }

    }

    public Tasks process(File file) throws Exception {

        InputStream inputStream = null;

        final Tasks tasks;
        try {
            inputStream = new FileInputStream(file);

            final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            tasks = (Tasks) unmarshaller.unmarshal(inputStream);
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }

        return tasks;

    }

}
