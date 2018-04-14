function load() {
    localStorage.apiKey = document.getElementById('key').value;

    $.ajax({
        url: 'http://192.168.2.101:8050/u' + localStorage.apiKey + '?module=auth&act=check_key',
        type: 'GET',
        dataType: 'json',
        crossDomain: true,

        success: function (data, textStatus, xhr) {
            if (data.result == true) {
                document.location.href = "/main.html";
            }
            else {
                alert("Неверный пароль");
            }
        },

        error: function (xhr, textStatus, errorThrown) {
            alert("Неверный пароль");
        }
    });
}