var serialport = require('serialport');
var socketio = require('socket.io-client')('http://localhost:8081');
var SerialPort = serialport.SerialPort;

//puerto serial de dispositivo gsm 1
var gsm = new SerialPort('/dev/ttyUSB0',{
    baudrate: 9600,
    parser: serialport.parsers.readline('\n')
},false);

//puerto serial del tragamonedas
var coin = new SerialPort('/dev/ttyUSB1',{
    baudrate: 9600,
},false);

//apertura del puerto serial del dispositivo gsm 1
gsm.open(function(error){
  if(error){
    console.log("no se pudo abrir el puerto del dispositivo gsm1");
  }else{
    console.log("gsm ok");
    gsm.on("data",function(data){
      console.log(data + '\n');
    });
    gsm.write("AT\r\n");
  }
});

//apertura del puerto serial del tragamonedas
coin.open(function(error){
  if(error){
    console.log("no se pudo abrir el puerto del tragamonedas");
  }else{
    console.log("tragamonedas ok");
    coin.on("data",function(data){
      var char = "" + data;
      switch(char.charCodeAt()){
        case 97:
              console.log("monto ingresado: " + 0.20);
              var value = 0.20;
              socketio.emit('monto',value);
              break;
        case 98:
              console.log("monto ingresado: " + 0.50);
              var value = 0.50;
              socketio.emit('monto',value);
              break;
        case 99:
              console.log("monto ingresado: " + 5);
              var value = 5;
              socketio.emit('monto',value);
              break;
        case 65:
              console.log("monto ingresado: " + 1);
              var value = 1;
              socketio.emit('monto',value);
              break;
        case 66:
              console.log("monto ingresado: " + 2);
              var value = 2;
              socketio.emit('monto',value);
              break;
        case 67:
              console.log("monto ingresado: " + 0.10);
              var value = 0.10;
              socketio.emit('monto',value);
              break;
        default :
              console.log("dato no procesado");
              break;
      }
    });
  }
});

var llamar = function(numero){
  var cmd = "ATD" + numero + ";\r\n";
  gsm.write(cmd);
};

var colgar = function(){
  var cmd = "ATH\r\n";
  gsm.write(cmd);
};

var recargaEntel = function(numero,monto){
    var cmd = "AT+CUSD=1,\"*133*"+numero+"*"+monto+"#\"\r\n";
    var confirmar = "AT+CUSD=1,\"1\"\r\n";
    gsm.write(cmd);
    setTimeout(function(){
      gsm.write(confirmar);
    },5000);
};

module.exports.llamar = llamar;
module.exports.colgar = colgar;
module.exports.recargaEntel = recargaEntel;
