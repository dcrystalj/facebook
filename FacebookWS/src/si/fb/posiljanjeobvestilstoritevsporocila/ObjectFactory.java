//
// Generated By:JAX-WS RI IBM 2.2.1-11/28/2011 08:28 AM(foreman)- (JAXB RI IBM 2.2.3-11/28/2011 06:21 AM(foreman)-)
//


package si.fb.posiljanjeobvestilstoritevsporocila;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the si.fb.posiljanjeobvestilstoritevsporocila package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _TipSporocilaResponse_QNAME = new QName("http://www.fb.si/PosiljanjeObvestilStoritevSporocila", "TipSporocilaResponse");
    private final static QName _TipSporosilaRequest_QNAME = new QName("http://www.fb.si/PosiljanjeObvestilStoritevSporocila", "TipSporosilaRequest");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: si.fb.posiljanjeobvestilstoritevsporocila
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SporociloTip }
     * 
     */
    public SporociloTip createSporociloTip() {
        return new SporociloTip();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TipSporocilaResponseEnum }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fb.si/PosiljanjeObvestilStoritevSporocila", name = "TipSporocilaResponse")
    public JAXBElement<TipSporocilaResponseEnum> createTipSporocilaResponse(TipSporocilaResponseEnum value) {
        return new JAXBElement<TipSporocilaResponseEnum>(_TipSporocilaResponse_QNAME, TipSporocilaResponseEnum.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SporociloTip }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.fb.si/PosiljanjeObvestilStoritevSporocila", name = "TipSporosilaRequest")
    public JAXBElement<SporociloTip> createTipSporosilaRequest(SporociloTip value) {
        return new JAXBElement<SporociloTip>(_TipSporosilaRequest_QNAME, SporociloTip.class, null, value);
    }

}
