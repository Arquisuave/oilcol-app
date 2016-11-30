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
        msg= "Camila García";
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

$.ajax({
    method: "GET",
    url:"/campo"
}).done(function(msg){
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

// Agregar Campo
$('#btnSave').click(function() {
    try{
        console.log("Click en Guardar")
        console.log($('#idJefeCampo').val());
        var elemento = document.getElementById("region");
        var region = elemento.options[elemento.selectedIndex].value;
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.open("POST", "/campo");
        xmlhttp.setRequestHeader("Content-Type", "application/json");
        var hola = xmlhttp.send(JSON.stringify({
            "idJefeCampo": $('#idJefeCampo').val(),
            "region": region
        }));
    }
   catch (err){
       swal("Error", "El usuario ya tiene un campo asignado o no existe.", "error");
   }
});

//Sweet alert

$(document).ready(function () {

    $('.demo2').click(function(){
        swal({
            title: "¡Se ha guardado exitosamente!",
            text: ":)",
            type: "success"
        });
    });


// DELETE CAMPO
    $('.demo4').click(function () {
        swal({
                title: "¿Seguro?",
                text: "Este campo no podrá ser recuperado",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "Si, ¡borrar!",
                cancelButtonText: "No, cancelar",
                closeOnConfirm: false,
                closeOnCancel: false },
            function (isConfirm) {
                if (isConfirm) {

                        var idCampo = $('#idCampo').val();
                        var xmlhttp = new XMLHttpRequest();
                        xmlhttp.open("DELETE", "/campo/"+idCampo);
                        xmlhttp.send();
                        swal("¡Eliminado!", "El campo ha sido eliminado", "success");


                    window.location.reload();
                } else {
                    swal("Cancelado", "El campo está seguro :)", "error");
                }
            });
    });

});

