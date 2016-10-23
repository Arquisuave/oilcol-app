 $(document).ready(function () {
     $('.scroll_content').slimscroll({
         height: '200px'
     })
     $(".dial").knob();
     var mapData = {
         "CO-RAN": 1135,
         "CO-RCA": 200,
         "CO-BOG": 200
             /**"SA": 200,
             "CA": 1300,
             "DE": 220,
             "FR": 540,
             "CN": 120,
             "AU": 760,
             "BR": 550,
             "IN": 200,
             "GB": 120,
             "RU": 2000**/
     };


     var markers = [
         {
             latLng: [4.7501293, -74.0617922],
             name: 'Una Casa'
         },
         {
             latLng: [12.5507521, -81.7303871],
             name: "San Andrés"
         }
            ]

     var map = new jvm.MultiMap({
         container: $('#world-map'),
         maxLevel: 2,
         main: {
             map: 'co-compl_merc',
             backgroundColor: "transparent",
             regionStyle: {
                 initial: {
                     fill: '#7D7D7D',
                     "fill-opacity": 1,
                     stroke: 'none',
                     "stroke-width": 0,
                     "stroke-opacity": 0
                 }
             },
             markers: markers,
             markerStyle: {
                 initial: {
                     fill: '#F8E23B',
                     stroke: '#383f47'
                 }
             },
             series: {
                 regions: [{
                     values: mapData,
                     scale: ["#1ab394", "#22d6b1"],
                     normalizeFunction: 'polynomial'
            }]

             }
         },
         mapUrlByCode: function (code, multiMap) {
             // var id = code.toLowerCase()+'_merc';
             // return id;
             // console.log("CODIGO REGION "+code);
             var path = 'js/plugins/jvectormap/col-compl.js';
             //console.log(path);
             var reg = {
                 'CO-RCA': "Caribe",
                 'CO-RAN': "Andina",
                 'CO-RAM': "Amazonía",
                 'CO-RPA': "Pacifico",
                 'CO-ROR': "Orinoquía",
                 'CO-COMPL': "Nacional"
             };
             var stringP = $('#title-h').text();
             // console.log(stringP);
             string2 = stringP.split(":");
             //console.log(string2[0]);

             if (reg[code] == undefined) {
                 //no hace nada
             } else {
                 stringP = string2[0].concat(": ", reg[code]);
                 //console.log(stringP);
                 var stringP = $('#title-h').text(stringP);

                 var estadoPozosAntes = $('#estadoPozos').text();
                 estadoPozosAntes = estadoPozosAntes.split(":")[0];
                 $('#estadoPozos').text(estadoPozosAntes + ": " + reg[code]);

                 var estadoEmergenciasPerc = $('#emergenciasPerc').text();
                 estadoEmergenciasPerc = estadoEmergenciasPerc.split(":")[0];
                 $('#emergenciasPerc').text(estadoEmergenciasPerc + ": " + reg[code]);

                 var estadoListaEmergen = $('#listaEmergen').text();
                 estadoListaEmergen = estadoListaEmergen.split(":")[0];
                 $('#listaEmergen').text(estadoListaEmergen + ": " + reg[code]);

             }


             return path;
         }
     });
     $.ajax({
         method: "GET",
         dataType: 'jsonp',
         //beforeSend: function(xhr){xhr.setRequestHeader('OilCol-Token',123)},
         //headers: {'OilCol-Token':'123'},
         url: "http://localhost:9000/pozo"
     }).done(function (msg) {

         console.log("yay");

     }).fail(function (msg, textstat) {
         console.log(textstat);
     }).always(function (msg) {
         console.log("buu")
     });
     // $('#world-map').vectorMap({
     //     map: 'co-compl_merc',
     //     backgroundColor: "transparent",
     //     regionStyle: {
     //         initial: {
     //             fill: '#7D7D7D',
     //             "fill-opacity": 1,
     //             stroke: 'none',
     //             "stroke-width": 0,
     //             "stroke-opacity": 0
     //         }
     //     },
     //     series: {
     //         regions: [{
     //             values: mapData,
     //             scale: ["#1ab394", "#22d6b1"],
     //             normalizeFunction: 'polynomial'
     //         }]
     //     }
     // });
 });