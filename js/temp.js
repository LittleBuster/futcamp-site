function setTemp() {
    var val = document.getElementById('temp').value;
    if (val != "") {
       document.location.href = "/main?" + "user=u" + localStorage.apiKey + 
                                "&act=set_temp&temp=" + val;
    }
    else {
        alert("Incorrect temp value");
    }
}