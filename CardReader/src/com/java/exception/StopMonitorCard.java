/*    */ package com.java.exception;
/*    */ 
/*    */ import java.awt.event.ActionEvent;
/*    */ import java.awt.event.ActionListener;
/*    */ import org.openide.util.Lookup;
/*    */ 
/*    */ public final class StopMonitorCard
/*    */   implements ActionListener
/*    */ {
/*    */   public void actionPerformed(ActionEvent e)
/*    */   {
/* 26 */     CardMonitor cardMonitor = (CardMonitor)Lookup.getDefault().lookup(CardMonitor.class);
/* 27 */     cardMonitor.stop();
/*    */   }
/*    */ }

/* Location:           C:\Work\OST\Projects\HospitalOSV3\Code\ExtModule\ThaiSmartCardModule\lib\th-go-geniustree-smartcard-api.jar
 * Qualified Name:     th.go.geniustree.smartcard.api.StopMonitorCard
 * JD-Core Version:    0.6.2
 */