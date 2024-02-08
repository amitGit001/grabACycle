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

function deleteCycleById(button) {
  var cycleId = button.getAttribute("data-cycle-id");
  var currentPage = button.getAttribute("data-current-page");
  var sortField = button.getAttribute("data-sort-field");
  var sortDir = button.getAttribute("data-sort-dir");

  sendHttpRequest(
    "GET",
    "/deleteCycle/" +
      cycleId +
      "?page=" +
      currentPage +
      "&sortField=" +
      sortField +
      "&sortDir=" +
      sortDir
  ).then((response) => {

    let cycles = JSON.parse(response);

    console.log(cycles);

    console.log(
      "Deleting cycle with id:" +
        cycleId +
        "& page=" +
        currentPage +
        "& sortField=" +
        sortField
    );

    let cycleRow = document.getElementById(cycleId);
    console.log(cycleRow);
    cycleRow.remove();
  });
}
