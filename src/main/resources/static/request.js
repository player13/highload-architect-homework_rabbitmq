const doRequest = (method, url, params, body, callback = () => {}) => {
    const request = new XMLHttpRequest();
    request.onreadystatechange = function () {
        if (request.readyState === 4) {
            if (request.status === 200) {
                callback(JSON.parse(request.responseText));
            } else {
                console.log('Server error:' + request.responseText);
            }
        }
    }

    if (params != null && params.length > 0) {
        let queryString = Object.keys(params).map(key => key + '=' + params[key]).join('&');
        request.open(method, url + '?' + queryString, true);
    } else {
        request.open(method, url, true);
    }
    request.setRequestHeader('Content-Type', 'application/json');
    request.send(JSON.stringify(body));
}
