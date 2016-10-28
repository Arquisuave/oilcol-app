/**
 * Aquí hago las cositas de los pozos
 */

//Get pozos
$.ajax({
    method: "GET",
    url:"/pozo"
}).done(function(msg){
    console.log(msg [5]);
    var pozos = [];
    for(var i=0;i<msg.length;i++){
        var este = {idPozo:msg[i].id,long:msg[i].lon,latt:msg[i].lat,est:msg[i].estado};
        var pozoActual = [este.idPozo,este.long,este.latt,este.est];
        pozos.push(pozoActual);
    }
    console.log(pozos);
    $('#tablita').DataTable({
        data: pozos,
        columns: [
            { title: "ID Pozo"},
            { title: "Longitud"},
            { title: "Latitud"},
            { title: "Estado"}
        ]
    });

});

//Post pozo
$('#btnSave').click(function() {
    console.log("Click en Guardar")
    console.log($('#longitud').val());
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open("POST", "/pozo");
    xmlhttp.setRequestHeader("Content-Type", "application/json");
    xmlhttp.send(JSON.stringify({
        "lon": $('#longitud').val(),
        "lat": $('#latitud').val(),
        "estado": $('#estado').val(),
        "campo": {
            "id": 2,
            "idJefeCampo": {
                "username": "tamuTAMU1",
                "type": null,
                "password": null,
                "notifications": []
            },
            "region": "AMAZONAS",
            "pozos": []
        }
    }));
});

//DELETE pozo
$('#btnDelete').click(function() {
    console.log("Click en Delete")
    var idPozo = $('#idPozo').val();
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open("DELETE", "/pozo/"+idPozo);
    xmlhttp.setRequestHeader("Content-Type", "application/json");
    xmlhttp.send();
});

//PUT pozo
$('#btnNuevoEstado').click(function() {
    console.log("Click en Edit")
    var idPozo = $('#idPozo2').val();
    var nuevoEstado = $('#nuevoEstado').val();
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open("PUT", "/updatePozo/"+idPozo+"/"+nuevoEstado);
    xmlhttp.send();
});