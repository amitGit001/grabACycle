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
  let cycleId = button.getAttribute("data-cycle-id");
  let currentPage = button.getAttribute("data-current-page");
  let sortField = button.getAttribute("data-sort-field");
  let sortDir = button.getAttribute("data-sort-dir");

  let url =
    "/deleteCycle/" +
    cycleId +
    "?page=" +
    currentPage +
    "&sortField=" +
    sortField +
    "&sortDir=" +
    sortDir;

  sendHttpRequest("GET", url).then((response) => {
    let data = JSON.parse(response);
    let cycles = data.cycles;
    let totalItems = data.totalItems;

    console.log(cycles);

    console.log(
      "Deleting cycle with id:" +
        cycleId +
        "& page=" +
        currentPage +
        "& sortField=" +
        sortField
    );

    // removing the cycle row from the UI
    let cycleToDeleteRow = document.getElementById(cycleId);
    console.log(cycleToDeleteRow);
    cycleToDeleteRow.remove();

    // Refreshing the current table in the UI
    refreshTable(cycles, currentPage, sortField, sortDir);

    // updating total items
    let totalItemsElem = document.getElementById("total-items-data");
    totalItemsElem.innerText = totalItems;
  });
}

function refreshTable(data, currentPage, sortField, sortDir) {
  let tableBody = document.querySelector("#cycles-list-table");

  tableBody.innerHTML = "";

  data.forEach((cycle) => {
    let row = tableBody.insertRow();

    row.setAttribute("id", cycle.id);

    let idCell = row.insertCell(0);
    idCell.textContent = cycle.id;

    let nameCell = row.insertCell(1);
    nameCell.textContent = cycle.name;

    let modelCell = row.insertCell(2);
    modelCell.textContent = cycle.model;

    let typeCell = row.insertCell(3);
    typeCell.textContent = cycle.type;

    let statusCell = row.insertCell(4);
    statusCell.textContent = cycle.bookingStatus;

    let actionsCell = row.insertCell(5);

    let actionsButtons = `<div> <a href='/showFormForUpdate/${cycle.id}?returnToPage=${currentPage}&sortField=${sortField}&sortDir=${sortDir}'>Update</a> <button onclick='deleteCycleById(this)' data-cycle-id='${cycle.id}' data-current-page='${currentPage}' data-sort-field='${sortField}' data-sort-dir='${sortDir}'>Delete</button> </div>`;

    actionsCell.innerHTML = actionsButtons;
  });
}
