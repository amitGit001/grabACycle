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
