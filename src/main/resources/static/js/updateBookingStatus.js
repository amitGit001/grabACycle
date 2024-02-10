function updateBookingStatus(cycleId) {
  let url = "/updateBookingStatus/" + cycleId;

  sendHttpRequest("GET", url)
    .then((response) => {
      let cycle = JSON.parse(response);
      console.log(cycle);

      if (cycle) {
        refreshStatus(cycle);
      } else {
        console.error("Error booking the bicycle");
      }
    })
    .catch((error) => {
      console.error(error);
    });

  function refreshStatus(cycle) {
    let status = cycle.bookingStatus;
    let id = cycle.id;

    let row = document.getElementById(id);
    let cell = row.querySelector(`td:nth-child(5) span`);

    if (status) {
      cell.innerText = "Booked";
    } else {
      cell.innerText = "Available";
    }
  }
}
