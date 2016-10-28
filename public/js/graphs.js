$(document).ready(function() {

    var current = 'Nacional'

    Highcharts.setOptions({
        global: {
            useUTC: false
        }
    });
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
            name: 'Random data',
            data: (function() {
                // generate an array of random data
                var data = [],
                    time = (new Date()).getTime(),
                    i;
                for (i = -19; i <= 0; i += 1) {
                    data.push({
                        x: time + i * 1000,
                        y: Math.random()
                    });
                }
                return data;
            }())
        }]
    });

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
            data: (function() {
                // generate an array of random data
                var data = [],
                    time = (new Date()).getTime(),
                    i;
                for (i = -19; i <= 0; i += 1) {
                    data.push({
                        x: time + i * 1000,
                        y: Math.random()
                    });
                }
                return data;
            }())
        }]
    });

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
            formatter: function() {
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
            data: (function() {
                // generate an array of random data
                var data = [],
                    time = (new Date()).getTime(),
                    i;
                for (i = -19; i <= 0; i += 1) {
                    data.push({
                        x: time + i * 1000,
                        y: Math.random()
                    });
                }
                return data;
            }())
        }]
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
                            text: 'Pozo: ' + item.id +" - "+ item.region
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
        var startDate = datepick.startDate.toISOString();
        var endDate = datepick.endDate.toISOString();

        console.log("Dates: "+startDate+" - "+endDate);
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