package ua.sustav.util;

import ua.sustav.DataBaseCVException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.Reader;
import java.io.Writer;

/**
 * Created by SUSTAVOV on 20.09.2017.
 */
public class XmlParser {
    private final Marshaller marshaller;
    private final Unmarshaller unmarshaller;

    public XmlParser(Class... classesToBeBound) {
        try {
            JAXBContext jbctx = JAXBContext.newInstance(classesToBeBound);

            marshaller = jbctx.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");


            unmarshaller = jbctx.createUnmarshaller();
        } catch (JAXBException e) {
            throw new DataBaseCVException("JAXB init failed", e);
        }
    }

    public <T> T unmarshall(Reader reader) {
        try {
            return (T) unmarshaller.unmarshal(reader);
        } catch (JAXBException e) {
            throw new DataBaseCVException("JAXB unmarshal failed", e);
        }
    }

    public void marshall(Object instance, Writer writer) {
        try {
            marshaller.marshal(instance, writer);
        } catch (JAXBException e) {
            throw new DataBaseCVException("JAXB marshal failed", e);
        }
    }
}
