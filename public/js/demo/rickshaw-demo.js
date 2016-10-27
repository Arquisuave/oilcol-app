$(function() {
    var graph = new Rickshaw.Graph( {
        element: document.querySelector("#chart"),
        series: [{
            color: '#1ab394',
            data: [
                { x: 0, y: 40 },
                { x: 1, y: 49 },
                { x: 2, y: 38 },
                { x: 3, y: 30 },
                { x: 4, y: 32 } ]
        }]
    });
    graph.render();

    var graph2 = new Rickshaw.Graph( {
        element: document.querySelector("#rickshaw_multi"),
        renderer: 'area',
        stroke: true,
        series: [ {
            data: [ { x: 0, y: 40 }, { x: 1, y: 49 }, { x: 2, y: 38 }, { x: 3, y: 20 }, { x: 4, y: 16 } ],
            color: '#1ab394',
            stroke: '#17997f'
        }, {
            data: [ { x: 0, y: 22 }, { x: 1, y: 25 }, { x: 2, y: 38 }, { x: 3, y: 44 }, { x: 4, y: 46 } ],
            color: '#eeeeee',
            stroke: '#d7d7d7'
        } ]
    } );
    graph2.renderer.unstack = true;
    graph2.render();

    var data = [ { x: -1893456000, y: 92228531 }, { x: -1577923200, y: 106021568 }, { x: -1262304000, y: 123202660 }, { x: -946771200, y: 132165129 }, { x: -631152000, y: 151325798 }, { x: -315619200, y: 179323175 }, { x: 0, y: 203211926 }, { x: 315532800, y: 226545805 }, { x: 631152000, y: 248709873 }, { x: 946684800, y: 281421906 }, { x: 1262304000, y: 308745538 } ];


    var graph3 = new Rickshaw.Graph({
        element: document.querySelector("#rickshaw_line"),
        renderer: 'line',
        series: [ {
            data: data,
            color: '#1ab394'
        } ]
    } );

    // var annotator = new Rickshaw.Graph.Annotate( {
        // graph: graph,
        // element: document.getElementById('timeline')
    // } );

    // var ticksTreatment = 'glow';

    var xAxis = new Rickshaw.Graph.Axis.Time( {
        graph: graph3,
        // timeFixture: new Rickshaw.Fixtures.Time.Local()
    } );

    
    // var y_axis = new Rickshaw.Graph.Axis.Y({
    //     graph: graph3,
    //     orientation: 'left',
    //     tickFormat: Rickshaw.Fixtures.Number.formatKMBT,
    //     element: document.getElementById('y_axis'),
    // });


    // // xAxis.render();
    
    // var yAxis = new Rickshaw.Graph.Axis.Y( {
    //     graph: graph3,
    //     tickFormat: Rickshaw.Fixtures.Number.formatKMBT
    // } );

    // yAxis.render();

    graph3.render();

    var graph4 = new Rickshaw.Graph({
        element: document.querySelector("#rickshaw_multi_line"),
        renderer: 'line',
        series: [{
            data: [ { x: 0, y: 40 }, { x: 1, y: 49 }, { x: 2, y: 38 }, { x: 3, y: 30 }, { x: 4, y: 32 } ],
            color: '#1ab394'
        }, {
            data: [ { x: 0, y: 20 }, { x: 1, y: 24 }, { x: 2, y: 19 }, { x: 3, y: 15 }, { x: 4, y: 16 } ],
            color: '#d7d7d7'
        }]
    });
    graph4.render();

    var graph5 = new Rickshaw.Graph( {
        element: document.querySelector("#rickshaw_bars"),
        renderer: 'bar',
        series: [ {
            data: [ { x: 0, y: 40 }, { x: 1, y: 49 }, { x: 2, y: 38 }, { x: 3, y: 30 }, { x: 4, y: 32 } ],
            color: '#1ab394'
        } ]
    } );
    graph5.render();

    var graph6 = new Rickshaw.Graph( {
        element: document.querySelector("#rickshaw_bars_stacked"),
        renderer: 'bar',
        series: [
            {
                data: [ { x: 0, y: 40 }, { x: 1, y: 49 }, { x: 2, y: 38 }, { x: 3, y: 30 }, { x: 4, y: 32 } ],
                color: '#1ab394'
            }, {
                data: [ { x: 0, y: 20 }, { x: 1, y: 24 }, { x: 2, y: 19 }, { x: 3, y: 15 }, { x: 4, y: 16 } ],
                color: '#d7d7d7'
            } ]
    } );
    graph6.render();

    var graph7 = new Rickshaw.Graph( {
        element: document.querySelector("#rickshaw_scatterplot"),
        renderer: 'scatterplot',
        stroke: true,
        padding: { top: 0.05, left: 0.05, right: 0.05 },
        series: [ {
            data: [ { x: 0, y: 15 },
                { x: 1, y: 18 },
                { x: 2, y: 10 },
                { x: 3, y: 12 },
                { x: 4, y: 15 },
                { x: 5, y: 24 },
                { x: 6, y: 28 },
                { x: 7, y: 31 },
                { x: 8, y: 22 },
                { x: 9, y: 18 },
                { x: 10, y: 16 }
            ],
            color: '#1ab394'
        } ]
    } );
    graph7.render();

});