var valActual, valNuevo;
var llamando = false;
var socket = io.connect('http://localhost:8081');
var mount = 0;

socket.on('monto',function(monto){
  var replace = "BS";
  var valueCoin = document.getElementById('monto');

  if(mount == 0){
    mount = mount + monto;
    valueCoin.innerHTML = valueCoin.innerHTML.replace(replace, mount);
  }else{
    replace = "" + mount;
    mount = mount + monto;
    valueCoin.innerHTML = valueCoin.innerHTML.replace(replace, mount);
  }
});

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

function eliminar(){
  valActual = getTextValue();
  valNuevo = valActual.substring(0,valActual.length-1);
  setTextValue(valNuevo);

}

var cls = function(){
  setTextValue("");
};
