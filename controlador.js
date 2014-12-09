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



});

nunjucks.configure(__dirname + "/vista",{
  express: app
});


app.use(express.static(__dirname + "/vista"));


app.get("/",function(req,res){
  res.render("call.html");
});

app.get("/recargas",function(req,res){
  res.render("recargas.html");
});

app.listen(8080);
