function createObject(event) {
    event.preventDefault();

    var form = event.target;
    var name = form.elements.name.value;
    var description = form.elements.description.value;

    var token = 'eyJpZCI6MCwicGFzc3dvcmQiOiIkMmEkMTAkUWxmcE0vRW5ISUJLRjlrWnA3eUk1T2xNcnFBV0hieDlkZC9wUEhVUHRubUsvcXpxeDdvZzIiLCJ1c2VyTmFtZSI6ImtoYW5oMTIzIn0=';
    var xhr = new XMLHttpRequest();
    xhr.open('POST', '/api/units/create', true);
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.setRequestHeader('Authorization', token);
    xhr.onload = function () {
        console.log(xhr.responseText);
        alert(token);
        form.reset();
    };
    xhr.onerror = function () {
        alert('Lỗi khi tạo đối tượng!');
    };
    xhr.send(JSON.stringify({ name: name, description: description }));
}