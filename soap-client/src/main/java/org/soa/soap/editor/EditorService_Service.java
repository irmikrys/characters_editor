
package org.soa.soap.editor;

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
 * JAX-WS RI 2.3.1-SNAPSHOT
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "EditorService", targetNamespace = "http://soa.org/soap/editor", wsdlLocation = "http://localhost:8080/soap-server_main_war/EditorService?wsdl")
public class EditorService_Service
    extends Service
{

    private final static URL EDITORSERVICE_WSDL_LOCATION;
    private final static WebServiceException EDITORSERVICE_EXCEPTION;
    private final static QName EDITORSERVICE_QNAME = new QName("http://soa.org/soap/editor", "EditorService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/soap-server_main_war/EditorService?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        EDITORSERVICE_WSDL_LOCATION = url;
        EDITORSERVICE_EXCEPTION = e;
    }

    public EditorService_Service() {
        super(__getWsdlLocation(), EDITORSERVICE_QNAME);
    }

    public EditorService_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), EDITORSERVICE_QNAME, features);
    }

    public EditorService_Service(URL wsdlLocation) {
        super(wsdlLocation, EDITORSERVICE_QNAME);
    }

    public EditorService_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, EDITORSERVICE_QNAME, features);
    }

    public EditorService_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public EditorService_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns EditorService
     */
    @WebEndpoint(name = "editorPort")
    public EditorService getEditorPort() {
        return super.getPort(new QName("http://soa.org/soap/editor", "editorPort"), EditorService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns EditorService
     */
    @WebEndpoint(name = "editorPort")
    public EditorService getEditorPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://soa.org/soap/editor", "editorPort"), EditorService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (EDITORSERVICE_EXCEPTION!= null) {
            throw EDITORSERVICE_EXCEPTION;
        }
        return EDITORSERVICE_WSDL_LOCATION;
    }

}
