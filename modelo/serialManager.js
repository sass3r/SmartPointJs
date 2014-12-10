var serialport = require('serialport');
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
              break;
        case 98:
              console.log("monto ingresado: " + 0.50);
              break;
        case 99:
              console.log("monto ingresado: " + 5);
              break;
        case 65:
              console.log("monto ingresado: " + 1);
              break;
        case 66:
              console.log("monto ingresado: " + 2);
              break;
        case 67:
              console.log("monto ingresado: " + 0.10);
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

module.exports.llamar = llamar;
module.exports.colgar = colgar;
