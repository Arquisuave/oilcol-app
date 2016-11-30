$(document).ready(function() {

    var current = 'Nacional'

    Highcharts.setOptions({
        global: {
            useUTC: false
        }
    });

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
            msg= "Jose Gabriel Tamura";
            $('#rolUser').text("Jefe de CAMPO #2");
            $('#imagenUser').attr("src", "img/Nosotros/Tamu.jpg");

        }
        if(msg.startsWith("ea.margffoy"))
        {
            document.getElementById('campitos').style.visibility ='hidden';
            msg= "Edgar Margffoy";
            $('#rolUser').text("Jefe de CAMPO #5");
            $('#imagenUser').attr("src", "img/Nosotros/Edgar.jpg");
        }
        if(msg.startsWith("c.garcia"))
        {
            document.getElementById('campitos').style.visibility ='hidden';
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



    $('#reportrange').daterangepicker({
                format: 'DD/MM/YYYY',
                startDate: moment().subtract(29, 'days'),
                endDate: moment(),
                minDate: '01/01/2016',
                maxDate: '12/31/2017',
                dateLimit: { days: 60 },
                showDropdowns: true,
                showWeekNumbers: true,
                timePicker: true,
                timePickerIncrement: 1,
                timePicker12Hour: true,
                autoUpdateInput:true,
                ranges: {
                    'Today': [moment(), moment()],
                    'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
                    'Last 7 Days': [moment().subtract(6, 'days'), moment()],
                    'Last 30 Days': [moment().subtract(29, 'days'), moment()],
                    'This Month': [moment().startOf('month'), moment().endOf('month')],
                    'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
                    'Trimestral': [moment().subtract(3, 'month'), moment()],
                    'Semestral':[moment().subtract(6, 'month'), moment()],
                    'Yearly':[moment().subtract(12, 'month'), moment()]
                },
                opens: 'right',
                drops: 'down',
                buttonClasses: ['btn', 'btn-sm'],
                applyClass: 'btn-primary',
                cancelClass: 'btn-default',
                separator: ' to ',
                locale: {
                    applyLabel: 'Submit',
                    cancelLabel: 'Cancel',
                    fromLabel: 'From',
                    toLabel: 'To',
                    customRangeLabel: 'Custom',
                    daysOfWeek: ['Su', 'Mo', 'Tu', 'We', 'Th', 'Fr','Sa'],
                    monthNames: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
                    firstDay: 1
                }
            }, function(start, end, label) {
                console.log(start.toISOString(), end.toISOString(), label);
                $('#reportrange span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
            });

    $('.region-select').select2({
        placeholder: "Seleccionar una opción",
        allowClear: true
    });

    $('.region-select').val('').trigger('change');

    $(".campo-select").prop("disabled", false);
    $(".pozo-select").prop("disabled", false);
    $(".region-select").prop("disabled", false);

    $(".pozo-select").select2({
        // tags: true,
        // multiple: true,
        // tokenSeparators: [',', ' '],
        placeholder: "Seleccionar un pozo",
        allowClear: true,
        minimumInputLength: 1,
        minimumResultsForSearch: 1000,
        ajax: {
            url: '/pozo',
            dataType: "json",
            type: "GET",
            delay: 250,
            // data: function(params) {
                // var queryParameters = {
                    // term: params.term
                // }
                // return queryParameters;
            // },
            processResults: function(data) {
                return {
                    results: $.map(data, function(item) {
                        console.log(item);
                        var obj = {
                            id: item.id,
                            text: 'Pozo: ' + item.id 
                        };
                        console.log(obj);
                        return obj;
                    })
                };
            }
        }
});

    var camp_url = '/campo';
    $('.campo-select').select2({
        placeholder: "Seleccionar un campo",
        allowClear: true,
        minimumInputLength: 1,
        minimumResultsForSearch: 1000,
        ajax: {
            url: camp_url,
            dataType: "json",
            type: "GET",
            delay: 250,
            data: function(params) {
                console.log(this);
                // this.url += '/'+params.term;
                console.log(this);
                // var queryParameters = {
                //     term: params.term
                // }
                return {};
            },
            processResults: function(data) {

                return {
                    results: $.map(data, function(item) {
                        console.log(item);
                        var obj = {
                            id: item.id,
                            text: 'Campo: ' + item.id +" - "+ item.region
                        };
                        console.log(obj);
                        return obj;
                    })
                };
            }
        }
    });

    $('.region-select').on('select2:select', function (evt) {
         $(".campo-select").prop("disabled", true);
         $(".pozo-select").prop("disabled", true);
        // Do something
    });

    $('.region-select').on('select2:unselect', function (evt) {
         $(".campo-select").prop("disabled", false);
         $(".pozo-select").prop("disabled", false);
        // Do something
    });

    $('.campo-select').on('select2:select', function (evt) {
         $(".region-select").prop("disabled", true);
         $(".pozo-select").prop("disabled", true);
        // Do something
    });

    $('.campo-select').on('select2:unselect', function (evt) {
         $(".region-select").prop("disabled", false);
         $(".pozo-select").prop("disabled", false);
        // Do something
    });


    $('.pozo-select').on('select2:select', function (evt) {
         $(".region-select").prop("disabled", true);
         $(".campo-select").prop("disabled", true);
        // Do something
    });

    $('.pozo-select').on('select2:unselect', function (evt) {
         $(".region-select").prop("disabled", false);
         $(".campo-select").prop("disabled", false);
        // Do something
    });


    $('#report-search').click(function(event) {
        var idReg = $('.region-select').val();
        var idCamp = $('.campo-select').val();
        var idPozo = $('.pozo-select').val();
        console.log("Región: "+idReg);
        console.log("Campo: "+idCamp);
        console.log("Pozo: "+idPozo);
        var datepick = $('#reportrange').data('daterangepicker');
        var startDateB = datepick.startDate.toISOString();
        var sD=new Date(startDateB);
        var startDate = sD.getFullYear()+"-"+(sD.getMonth()+1)+"-"+sD.getUTCDate()+" "+sD.getHours()+":"+sD.getMinutes()+":"+sD.getSeconds();
        var endDateB = datepick.endDate.toISOString();
        var eD=new Date(endDateB);
        var endDate = eD.getFullYear()+"-"+(eD.getMonth()+1)+"-"+eD.getUTCDate()+" "+eD.getHours()+":"+eD.getMinutes()+":"+eD.getSeconds();
        console.log(startDate);
        console.log(endDate);
        var json = {};
        if(idReg!==null){
            json["region"]=idReg;
        }
        if(idCamp!==null){
            json["jefeDeCampo"]=idCamp;
        }
        if(idPozo!==null){
            json["pozoId"]=idPozo;
        }
        json["fechaInicio"]=startDate;
        json["fechaFin"]=endDate;
        console.log("Dates: "+startDate+" - "+endDate);
        $.ajax({
            method: "POST",
            url: "/reporte/temp",
            contentType: 'application/json',
            dataType: 'json',
            data: JSON.stringify(json)
        }).done(function(msg){
            var dat = [];
            for (var i=0;i<msg.length;i++){
                console.log(new Date(msg[i]["timeStamp"]));
               dat.push([new Date(msg[i]["timeStamp"]),parseInt(msg[i]["info"])]);
            }
            $('.temprt-graph').highcharts({
                chart: {
                    type: 'spline',
                    animation: Highcharts.svg, // don't animate in old IE
                    marginRight: 10,
                    // events: {
                    //     load: function () {
                    //         // set up the updating of the chart each second
                    //         var series = this.series[0];
                    //         setInterval(function () {
                    //             var x = (new Date()).getTime(), // current time
                    //                 y = Math.random()+Math.random();
                    //             series.addPoint([x, y], true, true);
                    //         }, 500);
                    //     }
                    // }
                },
                title: {
                    text: current
                },
                xAxis: {
                    type: 'datetime',
                    tickPixelInterval: 150
                },
                yAxis: {
                    title: {
                        text: 'Value'
                    },
                    plotLines: [{
                        value: 0,
                        width: 1,
                        color: '#808080'
                    }]
                },
                tooltip: {
                    formatter: function() {
                        return '<b>' + this.series.name + '</b><br/>' +
                            Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) + '<br/>' +
                            Highcharts.numberFormat(this.y, 2) + '°C';
                    }
                },
                legend: {
                    enabled: false
                },
                exporting: {
                    enabled: false
                },
                credits: false,
                series: [{
                    name: 'Temperatura',
                    data: dat
                }]
            });


        });
        $.ajax({
            method: "POST",
            url: "/reporte/bar",
            contentType: 'application/json',
            dataType: 'json',
            data: JSON.stringify(json)
        }).done(function(msg) {
            var dat = [];
            for (var i = 0; i < msg.length; i++) {
                console.log(new Date(msg[i]["timeStamp"]));
                dat.push([new Date(msg[i]["timeStamp"]), parseInt(msg[i]["info"])]);
            }
            $('.barrrt-graph').highcharts({
                chart: {
                    type: 'spline',
                    animation: Highcharts.svg, // don't animate in old IE
                    marginRight: 10,
                    // events: {
                    //     load: function () {
                    //         // set up the updating of the chart each second
                    //         var series = this.series[0];
                    //         setInterval(function () {
                    //             var x = (new Date()).getTime(), // current time
                    //                 y = Math.random()+Math.random();
                    //             series.addPoint([x, y], true, true);
                    //         }, 500);
                    //     }
                    // }
                },
                title: {
                    text: current
                },
                xAxis: {
                    type: 'datetime',
                    tickPixelInterval: 150
                },
                yAxis: {
                    title: {
                        text: 'Value'
                    },
                    plotLines: [{
                        value: 0,
                        width: 1,
                        color: '#808080'
                    }]
                },
                tooltip: {
                    formatter: function() {
                        return '<b>' + this.series.name + '</b><br/>' +
                            Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) + '<br/>' +
                            Highcharts.numberFormat(this.y, 2) + " Barriles";
                    }
                },
                legend: {
                    enabled: false
                },
                exporting: {
                    enabled: false
                },
                credits: false,
                series: [{
                    name: 'Random data',
                    data: dat
                }]
            });
        });
        $.ajax({
            method: "POST",
            url: "/reporte/ener",
            contentType: 'application/json',
            dataType: 'json',
            data: JSON.stringify(json)
        }).done(function(msg) {
            var dat = [];
            for (var i = 0; i < msg.length; i++) {
                console.log(new Date(msg[i]["timeStamp"]));
                dat.push([new Date(msg[i]["timeStamp"]), parseInt(msg[i]["info"])]);
            }
            $('.energrt-graph').highcharts({
                chart: {
                    type: 'spline',
                    animation: Highcharts.svg, // don't animate in old IE
                    marginRight: 10,
                    // events: {
                    //     load: function () {
                    //         // set up the updating of the chart each second
                    //         var series = this.series[0];
                    //         setInterval(function () {
                    //             var x = (new Date()).getTime(), // current time
                    //                 y = Math.random()+Math.random();
                    //             series.addPoint([x, y], true, true);
                    //         }, 500);
                    //     }
                    // }
                },
                title: {
                    text: current
                },
                xAxis: {
                    type: 'datetime',
                    tickPixelInterval: 150
                },
                yAxis: {
                    title: {
                        text: 'Value'
                    },
                    plotLines: [{
                        value: 0,
                        width: 1,
                        color: '#808080'
                    }]
                },
                tooltip: {
                    formatter: function () {
                        return '<b>' + this.series.name + '</b><br/>' +
                            Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) + '<br/>' +
                            Highcharts.numberFormat(this.y, 2) + ' kW/h';
                    }
                },
                legend: {
                    enabled: false
                },
                exporting: {
                    enabled: false
                },
                credits: false,
                series: [{
                    name: 'Random data',
                    data: dat
                }]
            });
        });
    });

    $('#reset-params').click(function(event) {
        $('.region-select').val('').trigger('change');
        $('.pozo-select').val('').trigger('change');
        $('.campo-select').val('').trigger('change');
        current = 'Nacional';
        var datepick = $('#reportrange').data('daterangepicker');
        console.log(datepick);
        // datepick.val('');

        $(".campo-select").prop("disabled", false);
        $(".pozo-select").prop("disabled", false);
        $(".region-select").prop("disabled", false);

    });


    // $.ajaxSetup({
    //     contentType: "application/json; charset=utf-8"
    // });

    // $.post("http://localhost:4892/emergency", {"pozo":1, "tipo":"INCENDIO"}, function(msg){console.log(msg)});

});