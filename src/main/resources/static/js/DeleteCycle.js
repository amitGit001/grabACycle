
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