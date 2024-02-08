console.log("hey there");

function sendHttpRequest(method, url) {
  return new Promise((resolve, reject) => {
    let req = new XMLHttpRequest();
    // req.responseType = "json";
    req.onreadystatechange = function () {
      if (this.readyState == 4) {
        if (this.status == 200) {
          let data = this.response;
          // var data = this.response;
          //          console.log(data);
          resolve(data);
        } else reject(this.status);
      }
    };
    req.open(method, url, true);
    req.send();
  });
}

function deleteCycleById(cycleId) {


  sendHttpRequest("GET", "/deleteCycle/" + cycleId).then((response) => {
    console.log(response);
    console.log("Deleting cycle with:" + cycleId);
    let cycleRow = document.getElementById(cycleId);
    console.log(cycleRow);
    cycleRow.remove();
  });
}
