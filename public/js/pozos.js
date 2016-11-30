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
        document.getElementById('campitos').style.visibility ='hidden';
        document.getElementById('btnAñadir').style.visibility ='hidden';
        document.getElementById('btnEliminar').style.visibility ='hidden';
        msg= "Jose Gabriel Tamura";
        $('#rolUser').text("Jefe de CAMPO #2");
        $('#imagenUser').attr("src", "img/Nosotros/Tamu.jpg");

    }
    if(msg.startsWith("ea.margffoy"))
    {
        document.getElementById('campitos').style.visibility ='hidden';
        document.getElementById('btnAñadir').style.visibility ='hidden';
        document.getElementById('btnEliminar').style.visibility ='hidden';
        msg= "Edgar Margffoy";
        $('#rolUser').text("Jefe de CAMPO #5");
        $('#imagenUser').attr("src", "img/Nosotros/Edgar.jpg");
    }
    if(msg.startsWith("c.garcia"))
    {
        document.getElementById('campitos').style.visibility ='hidden';
        document.getElementById('btnAñadir').style.visibility ='hidden';
        document.getElementById('btnEliminar').style.visibility ='hidden';
        msg= "Camila Garcia";
        $('#rolUser').text("Jefe de CAMPO #4");
        $('#imagenUser').attr("src", "img/Nosotros/Cami.jpg");
    }
    if(msg.startsWith("mm.gomez10"))
    {
        msg= "Margarita Gómez";
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
    var elemento = document.getElementById("nuevoEstado");
    var nuevoEstado = elemento.options[elemento.selectedIndex].value;
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open("PUT", "/updatePozo/"+idPozo+"/"+nuevoEstado);
    xmlhttp.send();
});