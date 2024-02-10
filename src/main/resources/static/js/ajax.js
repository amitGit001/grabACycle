function sendHttpRequest(method, url) {
  return new Promise((resolve, reject) => {
    let req = new XMLHttpRequest();

    req.onreadystatechange = function () {
      if (this.readyState == 4) {
        if (this.status == 200) {
          let data = this.response;

          resolve(data);
        } else reject(this.status);
      }
    };
    req.open(method, url, true);
    req.send();
  });
}
