package com.sawers.cubitrucktest;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.File;


class OperacionAT{
  private String puerto;
  private SerialPort serial;
  private OutputStream output;
  private InputStream input;
  private OutputStreamWriter writer;
  private InputStreamReader reader;
  private String cmd;

  public OperacionAT(String port){
    puerto = port;
    serial = null;
    output = null;
    input = null;
    writer = null;
    reader = null;
    cmd = "";

  }

  public boolean abrirPuerto(){
    boolean res = false;
    File fichero = new File(puerto);
    try{
      serial = new SerialPort(fichero,9600,0);
      output = serial.getOutputStream();
      input = serial.getInputStream();
    }catch(IOException ioe){

    }
    if(output != null && input != null){
      writer = new OutputStreamWriter(output);
      reader = new InputStreamReader(input);
      res = true;
    }
    return res;
  }

  public void cerrarPuerto(){
      serial.close();
      input = null;
      output = null;
  }

  public void llamar(String num){
    cmd = "ATD" + num + ";\r\n";
    try{
      writer.write(cmd,0,cmd.length());
      writer.flush();
    }catch(IOException ioe){

    }

  }

  public void colgar(){
    cmd = "ATH\r\n";
    try{
      writer.write(cmd,0,cmd.length());
      writer.flush();
    }catch(IOException ioe){

    }

  }

  public void contestar(){
    cmd = "ATA\r\n";
    try{
      writer.write(cmd,0,cmd.length());
      writer.flush();
    }catch(IOException ioe){

    }

  }

  public void recargar1bs(String num){
    cmd = "AT+CUSD=1,\"*66#\"\r\n";

    try{
      writer.write(cmd,0,cmd.length());
      writer.flush();
    }catch(IOException ioe){}

    try{
      Thread.currentThread().sleep(4500);
    }catch(Exception e){}

    cmd = "AT+CUSD=1,\"1\"\r\n";

    try{
      writer.write(cmd,0,cmd.length());
      writer.flush();
    }catch(IOException ioe){}

    try{
      Thread.currentThread().sleep(4500);
    }catch(Exception e){}

    cmd = "AT+CUSD=1," + "\"" + num + "\"\r\n";

    try{
      writer.write(cmd,0,cmd.length());
      writer.flush();
    }catch(IOException ioe){}

    try{
      Thread.currentThread().sleep(4500);
    }catch(Exception e){}

    cmd = "AT+CUSD=1,\"1\"\r\n";

    try{
      writer.write(cmd,0,cmd.length());
      writer.flush();
    }catch(IOException ioe){}

    try{
      Thread.currentThread().sleep(4500);
    }catch(Exception e){}

    cmd = "AT+CUSD=1,\"1\"\r\n";

    try{
      writer.write(cmd,0,cmd.length());
      writer.flush();
    }catch(IOException ioe){}

    try{
      Thread.currentThread().sleep(4500);
    }catch(Exception e){}

    cmd = "AT+CUSD=1,\"2\"\r\n";

    try{
      writer.write(cmd,0,cmd.length());
      writer.flush();
    }catch(IOException ioe){}


  }

  public void miniCargaTigo(String num, int mnto){
      if(validarMnto(mnto)){
          cmd = "AT+CUSD=1,\"*66#\"\r\n";

          try{
            writer.write(cmd,0,cmd.length());
            writer.flush();
          }catch(IOException ioe){}

          try{
            Thread.currentThread().sleep(4500);
          }catch(Exception e){}

          cmd = "AT+CUSD=1,\"1\"\r\n";

          try{
            writer.write(cmd,0,cmd.length());
            writer.flush();
          }catch(IOException ioe){}

          try{
            Thread.currentThread().sleep(4500);
          }catch(Exception e){}

          cmd = "AT+CUSD=1," + "\"" + num + "\"\r\n";

          try{
            writer.write(cmd,0,cmd.length());
            writer.flush();
          }catch(IOException ioe){}

          try{
            Thread.currentThread().sleep(4500);
          }catch(Exception e){}

          switch(mnto){
            case 1:
                    cmd = "AT+CUSD=1,\"1\"\r\n";
                    break;
            case 2:
                    cmd = "AT+CUSD=1,\"2\"\r\n";
                    break;
            case 5:
                    cmd = "AT+CUSD=1,\"3\"\r\n";
                    break;
            case 10:
                    cmd = "AT+CUSD=1,\"4\"\r\n";
                    break;

            case 20:
                    cmd = "AT+CUSD=1,\"5\"\r\n";
                    break;
          }

          try{
            writer.write(cmd,0,cmd.length());
            writer.flush();
          }catch(IOException ioe){}

          try{
            Thread.currentThread().sleep(4500);
          }catch(Exception e){}

          cmd = "AT+CUSD=1,\"1\"\r\n";

          try{
            writer.write(cmd,0,cmd.length());
            writer.flush();
          }catch(IOException ioe){}

          try{
            Thread.currentThread().sleep(4500);
          }catch(Exception e){}

          cmd = "AT+CUSD=1,\"2\"\r\n";

          try{
            writer.write(cmd,0,cmd.length());
            writer.flush();
          }catch(IOException ioe){}

      }else{
          //monto no valido
      }
  }

  public boolean validarMnto(int mnto){
    boolean res = false;
    if(mnto == 1){
      res = true;
    }else{
      if(mnto == 2){
        res = true;
      }else{
        if(mnto == 5){
          res = true;
        }else{
          if(mnto == 10){
            res = true;
          }else{
            if(mnto == 20){
              res = true;
            }
          }
        }
      }
    }
    return res;
  }

  public void consultaDeSaldoTigo(){
    cmd = "AT+CUSD=1,\"*123#\"\r\n";
    try{
      writer.write(cmd,0,cmd.length());
      writer.flush();
    }catch(IOException ioe){

    }
  }

  public void enviarSMS(String num, String texto){
    cmd = "AT+CMGS=\"" + num  + "\"\r\n";
    try{
      writer.write(cmd,0,cmd.length());
      writer.flush();
    }catch(IOException ioe){}

    try{
      Thread.currentThread().sleep(2000);
    }catch(Exception e){}


    cmd = texto;
    try{
      writer.write(cmd,0,cmd.length());
      writer.flush();
    }catch(IOException ioe){}

    try{
      writer.write((char)26);
      writer.flush();
    }catch(IOException ioe){}


  }

  public String recargaEntel(String num, int mnto){
    String res = "";
    char buff[] = new char[1023];
    cmd = "AT+CUSD=1,\"*133*" + num + "*" + mnto + "#\"\r\n";
    try{
      writer.write(cmd, 0, cmd.length());
      writer.flush();
    }catch(IOException ioe){}

    try{
      Thread.currentThread().sleep(4500);
    }catch(Exception e){}

    cmd = "AT+CUSD=1,\"2\"";

    try{
      Thread.currentThread().sleep(4500);
    }catch(Exception e){}

    BufferedReader bufferedReader = new BufferedReader(reader);
    try {
      bufferedReader.read(buff,0,1023);
    }catch(IOException ioe){}
    res = new String(buff,0,1023);

    try{
      Thread.currentThread().sleep(5000);
    }catch(Exception e){}


    cmd = "AT+CHUP\r\n";
    try{
      writer.write(cmd,0,cmd.length());
      writer.flush();
    }catch(IOException ioe){}


    return res;
  }

  public String consultaDeSaldoEntel(){
    String res = "";
    char buff[] = new char[255];
    cmd = "AT+CUSD=1,\"*105#\"\r\n";
    try{
      writer.write(cmd,0,cmd.length());
      writer.flush();
    }catch(IOException ioe){}
      BufferedReader bufferedReader = new BufferedReader(reader);
    try {
      bufferedReader.read(buff,0,255);
    }catch(IOException ioe){}
    res = new String(buff,0,255);

    try{
      Thread.currentThread().sleep(5000);
    }catch(Exception e){}


    cmd = "AT+CHUP\r\n";
    try{
      writer.write(cmd,0,cmd.length());
      writer.flush();
    }catch(IOException ioe){}

    return res;

  }

  public void saldoDispRecargaEntel(){
    cmd = "AT+CUSD=1,\"*133#\"\r\n";
    try{
      writer.write(cmd,0,cmd.length());
      writer.flush();
    }catch(IOException ioe){}

    try{
      Thread.currentThread().sleep(4500);
    }catch(Exception e){}

    cmd = "AT+CUSD=1,\"1\"\r\n";

    try{
      writer.write(cmd,0,cmd.length());
      writer.flush();
    }catch(IOException ioe){}

  }

  public void initSerial(){
    cmd = "AT\r\n";
    for(int i = 0; i < 9; i++){
      try{
        writer.write(cmd,0,cmd.length());
        writer.flush();
      }catch(IOException ioe){}

    }
  }

  public void saldoDispRecargaTigo(){
    cmd = "AT+CUSD=1,\"*888*0011#\"\r\n";
    try{
      writer.write(cmd,0,cmd.length());
      writer.flush();
    }catch(IOException ioe){}

    try{
      Thread.currentThread().sleep(6000);
    }catch(Exception e){}

    cmd = "AT+CUSD=1,\"2\"\r\n";

    try{
      writer.write(cmd,0,cmd.length());
      writer.flush();
    }catch(IOException ioe){}
  }

  public void recargaTigo(String num, int mnto){
    cmd = "AT+CUSD=1,\"*888*0011*" + num + "*" + mnto + "#\"\r\n";

    try{
      writer.write(cmd,0,cmd.length());
      writer.flush();
    }catch(IOException ioe){}

    try{
      Thread.currentThread().sleep(6000);
    }catch(Exception e){}

    cmd = "AT+CUSD=1,\"2\"\r\n";

    try{
      writer.write(cmd,0,cmd.length());
      writer.flush();
    }catch(IOException ioe){}

  }

}
