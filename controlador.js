var express = require('express');
var serialManager = require('./modelo/serialManager.js');
var nunjucks = require('nunjucks');
var socketio = require('socket.io').listen(8081);

var app = express();

socketio.sockets.on('connection', function(socket){

  socket.on('llamar', function(numero){
    serialManager.llamar(numero);
  });

  socket.on('colgar', function(numero){
    serialManager.colgar();
  });

  socket.on('monto',function(monto){
    console.log("desde servidor: " + monto);
    socketio.sockets.emit('monto',monto);
  });

  socket.on('recargaEntel',function(numero,monto){
    serialManager.recargaEntel(numero,monto);
  });



});

nunjucks.configure(__dirname + "/vista",{
  express: app
});


app.use(express.static(__dirname + "/vista"));


app.get("/llamadas",function(req,res){
  res.render("llamadas.html");
});

app.get("/recargas",function(req,res){
  res.render("recargas.html");
});

app.listen(8080);
