
package com.java.main;

import com.java.model.MoiApplet1Info;
import com.java.model.NhsoAppletInfo;
import com.java.reader.pcscServices;
import java.io.IOException;
import java.util.List;
import javax.smartcardio.Card;
import javax.smartcardio.CardException;
import javax.smartcardio.CardTerminal;
import javax.smartcardio.TerminalFactory;
import org.openide.util.Exceptions;

public class tscmReader {
    private final TerminalFactory factory;
    private final List<CardTerminal> terminals;
    private CardTerminal terminal;
    private Card card;
    private pcscServices p;
    
    public tscmReader() throws CardException, IOException{
        p = new pcscServices();
        factory = TerminalFactory.getDefault();
        terminals = factory.terminals().list();
        // Use the first terminal
        terminal = terminals.get(0);
        // Connect with the card
        card = terminal.connect("*");
        //p.wakeupCard(card);
        p.getAvialableDataInfo(card, true);   
    }
    
    public static void main(String[] args){
        try {
            new tscmReader();
            MoiApplet1Info moi = new MoiApplet1Info();
            NhsoAppletInfo nsho = new NhsoAppletInfo();
            System.out.println(moi.getThaiName());
            System.out.println(nsho.getMainHospitalsName());
            System.out.println(nsho.getChangeHospitalAmount());
            System.out.println(nsho.getPaidType());
            System.out.println(nsho.getMainRights());
            System.out.println(nsho.getFormattedDateOfIssue());
            System.out.println(nsho.getFormattedDateOfExpirey());
            System.out.println(nsho.getSubHospitalsName());
            System.out.println(moi.getFacesImage());
        } catch (CardException ex) {
            Exceptions.printStackTrace(ex);
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
    }
}
