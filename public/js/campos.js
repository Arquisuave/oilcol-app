/**
 * Created by margarita on 27/10/2016.
 */
/**
 * Aquí hago las cositas de los pozos
 */
$.ajax({
    method: "GET",
    //beforeSend: function(xhr){xhr.setRequestHeader('OilCol-Token',123)},
    //headers: {'OilCol-Token':'123'},
    url: "/userActual"
}).done(function (msg) {

    console.log("AQUI VIENE EL NOMBRE DEL USER ");
    console.log(msg);

    if(msg.startsWith("jg.tamura10"))
    {
        msg= "Jose Gabriel Tamura";
        $('#rolUser').text("Jefe de CAMPO #2");
        $('#imagenUser').attr("src", "img/Nosotros/Tamu.jpg");

    }
    if(msg.startsWith("ea.margffoy"))
    {
        msg= "Edgar Margffoy";
        $('#rolUser').text("Jefe de CAMPO #5");
        $('#imagenUser').attr("src", "img/Nosotros/Edgar.jpg");
    }
    if(msg.startsWith("c.garcia"))
    {
        msg= "Camila Garcia";
        $('#rolUser').text("Jefe de CAMPO #4");
        $('#imagenUser').attr("src", "img/Nosotros/Cami.jpg");
    }
    if(msg.startsWith("mm.gomez10"))
    {
        msg= "Margarita Gomez";
        $('#imagenUser').attr("src", "img/Nosotros/Margari.jpg");
    }
    $('#nombreUser').text(msg);

    var theImg = document.getElementById('imagenUser');
    theImg.height = 125;
    theImg.width = 125;

}).fail(function (msg, textstat) {
    console.log(textstat);
}).always(function (msg) {
    console.log("buu")
});

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
