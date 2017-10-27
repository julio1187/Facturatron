
package com.finkok.stamp;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "StampSOAP", targetNamespace = "http://facturacion.finkok.com/stamp", wsdlLocation = "file:/Users/octavioruizcastillo/Downloads/prueba/stamp_1.wsdl")
public class StampSOAP
    extends Service
{

    private final static URL STAMPSOAP_WSDL_LOCATION;
    private final static WebServiceException STAMPSOAP_EXCEPTION;
    private final static QName STAMPSOAP_QNAME = new QName("http://facturacion.finkok.com/stamp", "StampSOAP");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("file:/Users/octavioruizcastillo/Downloads/prueba/stamp_1.wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        STAMPSOAP_WSDL_LOCATION = url;
        STAMPSOAP_EXCEPTION = e;
    }

    public StampSOAP() {
        super(__getWsdlLocation(), STAMPSOAP_QNAME);
    }

    public StampSOAP(WebServiceFeature... features) {
        super(__getWsdlLocation(), STAMPSOAP_QNAME, features);
    }

    public StampSOAP(URL wsdlLocation) {
        super(wsdlLocation, STAMPSOAP_QNAME);
    }

    public StampSOAP(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, STAMPSOAP_QNAME, features);
    }

    public StampSOAP(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public StampSOAP(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns Application
     */
    @WebEndpoint(name = "Application")
    public Application getApplication() {
        return super.getPort(new QName("http://facturacion.finkok.com/stamp", "Application"), Application.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns Application
     */
    @WebEndpoint(name = "Application")
    public Application getApplication(WebServiceFeature... features) {
        return super.getPort(new QName("http://facturacion.finkok.com/stamp", "Application"), Application.class, features);
    }

    private static URL __getWsdlLocation() {
        if (STAMPSOAP_EXCEPTION!= null) {
            throw STAMPSOAP_EXCEPTION;
        }
        return STAMPSOAP_WSDL_LOCATION;
    }

}
