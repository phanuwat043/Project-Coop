
package com.java.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import org.json.JSONException;
import org.json.JSONObject;
import com.java.main.tscmReader;
import com.java.model.MoiApplet1Info;
import javax.smartcardio.CardException;

public class Client {

    public static void main(String[] args) throws IOException, JSONException, CardException{
        Socket socket = new Socket("localhost",8080);
        OutputStreamWriter writer = new OutputStreamWriter(socket.getOutputStream(),"UTF-8");
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
        
        tscmReader tscm = new tscmReader();
        MoiApplet1Info moi = new MoiApplet1Info();
        
        JSONObject json = new JSONObject();
        json.put("ID", moi.getPid());
        writer.write(json.toString()+"\n");
        writer.flush();
        
        String line = reader.readLine();
        json = new JSONObject(line);
        
        System.out.println("Receive form Server:\n"+json.toString(0));
        socket.close();
    }
}
