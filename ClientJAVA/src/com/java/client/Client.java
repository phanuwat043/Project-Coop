
package com.java.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import org.json.JSONException;
import org.json.JSONObject;
import javax.smartcardio.CardException;
import com.hostos.lib.thaismartcard.main.tscmReader;
import com.hostos.lib.thaismartcard.model.MoiApplet1Info;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {

    List<String> infomation;
    JSONObject json;

    public Client() throws IOException, JSONException, CardException {
        Socket socket = new Socket("localhost", 8888);
        OutputStreamWriter writer = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));

        tscmReader tscm = new tscmReader();
        MoiApplet1Info moi = new MoiApplet1Info();
        json = new JSONObject(reader);

        json.put("name", moi.getThaiName());
        json.put("address", moi.getAddress());
        json.put("pic", moi.getFacesImage().toString());
        writer.write(json.toString() + "\n");
        writer.flush();

        System.out.println("Receive form Server:\n" + json.toString());

        //add infomation to arraylist
        infomation = new ArrayList<>();
        infomation.add(json.toString());
        socket.close();
    }

    public static void main(String[] args) {
        try {
            Client c = new Client();
            System.out.println(c.getInfomation());
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CardException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<String> getInfomation() {
        return infomation;
    }

    public void setInfomation(List<String> infomation) {
        this.infomation = infomation;
    }
}
