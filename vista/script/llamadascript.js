var valActual, valNuevo;
var llamando = false;
var socket = io.connect('http://localhost:8081');

function getTextValue(){
  return document.getElementById('intext').value;
}

function setTextValue(value){
  document.getElementById('intext').value = value;
}


function marcar(id,value){
  valActual = getTextValue();
  setTextValue(valActual + value);

}

function call(){
  var block = document.getElementById('call');
  var numero = getTextValue();
  if(!llamando){
    block.innerHTML = block.innerHTML.replace('Llamar','Colgar');
    llamando = true;
    socket.emit('llamar',numero);


  }else{
    block.innerHTML = block.innerHTML.replace('Colgar','Llamar');
    llamando = false;
    socket.emit('colgar');

  }
}

function eliminar(){
  valActual = getTextValue();
  valNuevo = valActual.substring(0,valActual.length-1);
  setTextValue(valNuevo);

}

var cls = function(){
  setTextValue("");
};
