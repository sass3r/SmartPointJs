var serialport = require('serialport');
var SerialPort = serialport.SerialPort;

var connection = new SerialPort('/dev/ttyUSB0',{
    baudrate: 9600,
    //parser: serialport.parsers.readline('\n')
},false);

connection.open(function(error){
  if(error){
    console.log("no se pudo abrir el puerto");
  }else{
    console.log("puerto serial abierto");
    connection.on("data",function(data){
      var datos = data;
      console.log("monto: " + datos + '\n');
    });
    connection.write("AT\r\n");
  }
});

var llamar = function(numero){
  var cmd = "ATD" + numero + ";\r\n";
  connection.write(cmd);
};

var colgar = function(){
  var cmd = "ATH\r\n";
  connection.write(cmd);
};

module.exports.llamar = llamar;
module.exports.colgar = colgar;
