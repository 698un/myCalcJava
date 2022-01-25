package by.itstep.mySite.control.net.entity;

import by.itstep.mySite.control.net.NetRequest;

/**This class is clientKey for Identify every client
 *
 */
public class ClientKey {


    //!!! NECESELARY
    private ClientKey(){

        }

    /** This method parse content of the request and search ClientKey in header if request
     *
     * @param netReq  object of request
     * @return "none" if clientKey not found
     * @return ClientKet value if clientKey is containt in the header of the request
     */
    public static String getClientKey(NetRequest netReq){

        String content = netReq.getContent();
        String result;

        try {

            int index = content.indexOf("ClientKey=");
            if (index==-1) return "none";//if not found "ClientKey" return "none"

            index+=10;//translate by length of "webuser="

            int indexLast = -1;
            int indexLast1 = content.indexOf("\n", index);
            int indexLast2 = content.indexOf(";", index);

            //если оба найдены
            if (indexLast1 > index && indexLast2 > index) indexLast = Math.min(indexLast1, indexLast2);

            //если один из них не найден
            if (indexLast1 * indexLast2 <= 0) indexLast = Math.max(indexLast1, indexLast2);

            result = content.substring(index,indexLast).trim();

            //by any exception set clientKey=none
            } catch (Exception e) {
                e.printStackTrace();
                result = "none";}


        return result;
        }//getClientKey



}
