var timerId = setTimeout(action, 5000);


function getMeteoAll() {
    $.ajax({
        url: 'http://192.168.2.101:8050/u' + localStorage.apiKey + '?module=meteo&act=get_meteo&sensor=all',
        type: 'GET',
        dataType: 'json',
        crossDomain: true,

        success: function (data, textStatus, xhr) {
            if (data.result == true) {
                $("#room").html(data.sensor0.temp + "&deg;C");
                $("#veranda").html(data.sensor1.temp + "&deg;C");
                $("#kitchen").html(data.sensor2.temp + "&deg;C");
                $("#street").html(data.sensor3.temp + "&deg;C");
                $("#2nd").html(data.sensor4.temp + "&deg;C");
                $("#ground").html(data.sensor5.temp + "&deg;C");
                $("#hum").html(data.sensor7.hum + "%");
                $("#pres").html(data.sensor6.pres + "мм");
            }
            else {
                printError();
            }
        },

        error: function (xhr, textStatus, errorThrown) {
            printError();
        }
    });
}

function printError() {
    $("#room").html("<font color=\"red\">error</font>");
    $("#veranda").html("<font color=\"red\">error</font>");
    $("#kitchen").html("<font color=\"red\">error</font>");
    $("#street").html("<font color=\"red\">error</font>");
    $("#2nd").html("<font color=\"red\">error</font>");
    $("#ground").html("<font color=\"red\">error</font>");
    $("#hum").html("<font color=\"red\">error</font>");
    $("#pres").html("<font color=\"red\">error</font>");
}

window.onload = function() {
    getMeteoAll();
}

function action() {
    getMeteoAll();
    timerId = setTimeout(action, 5000);
}