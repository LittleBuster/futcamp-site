function load() {
	localStorage.apiKey = document.getElementById('key').value;
	document.location.href = "/main?" + "user=u" + localStorage.apiKey;
}