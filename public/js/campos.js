/**
 * Created by margarita on 27/10/2016.
 */
/**
 * Aquí hago las cositas de los pozos
 */

$.ajax({
    method: "GET",
    url:"/campo"
}).done(function(msg){
    console.log(msg [5]);
    var campos = [];
    for(var i=0;i<msg.length;i++){
        var este = {idCampo:msg[i].id,usernameJefe:msg[i].idJefeCampo.username,region:msg[i].region};
        var campoActual = [este.idCampo,este.usernameJefe,este.region];
        campos.push(campoActual);
    }
    console.log(campos);
    $('#tablita').DataTable({
        data: campos,
        columns: [
            { title: "ID Campo"},
            { title: "Usuario jefe"},
            { title: "Región"}
        ]
    });

});

$('#btnSave').click(function() {
    console.log("Click en Guardar")
    console.log($('#idJefeCampo').val());
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open("POST", "/campo");
    xmlhttp.setRequestHeader("Content-Type", "application/json");
    xmlhttp.send(JSON.stringify({
        "idJefeCampo": $('#idJefeCampo').val(),
        "region": $('#region').val()
    }));
});

//DELETE pozo
$('#btnDelete').click(function() {
    console.log("Click en Delete")
    var idCampo = $('#idCampo').val();
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open("DELETE", "/campo/"+idCampo);
    xmlhttp.send();
});
