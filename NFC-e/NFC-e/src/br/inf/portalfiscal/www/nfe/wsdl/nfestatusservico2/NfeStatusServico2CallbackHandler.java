
/**
 * NfeStatusServico2CallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.1  Built on : Aug 31, 2011 (12:22:40 CEST)
 */

    package br.inf.portalfiscal.www.nfe.wsdl.nfestatusservico2;

    /**
     *  NfeStatusServico2CallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class NfeStatusServico2CallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public NfeStatusServico2CallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public NfeStatusServico2CallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for nfeStatusServicoNF2 method
            * override this method for handling normal response from nfeStatusServicoNF2 operation
            */
           public void receiveResultnfeStatusServicoNF2(
                    br.inf.portalfiscal.www.nfe.wsdl.nfestatusservico2.NfeStatusServico2Stub.NfeStatusServicoNF2Result result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from nfeStatusServicoNF2 operation
           */
            public void receiveErrornfeStatusServicoNF2(java.lang.Exception e) {
            }
                


    }
    