function showBookingModal(cycleId) {
  let bookingModal = document.getElementById("booking-modal");
  let closeModalBtn = document.getElementById("close-modal-btn");
  let confirmBookingBtn = document.getElementById("confirm-booking-btn");

  // the user is trying to book a cycle
  // lets show them the booking modal
  bookingModal.classList.remove("hide");
  bookingModal.classList.add("show");

  // if the user clicks on confirm button
  confirmBookingBtn.onclick = () => {
    // initiate updating booking status
    updateBookingStatus(cycleId);
    // close the modal
    bookingModal.classList.remove("show");
    bookingModal.classList.add("hide");
  };

  // if the user clicks on close button
  closeModalBtn.onclick = () => {
    // close the modal
    bookingModal.classList.remove("show");
    bookingModal.classList.add("hide");
  };
}

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
      cell.classList.remove("available");
      cell.classList.add("booked");
    } else {
      cell.innerText = "Available";
      cell.classList.remove("booked");
      cell.classList.add("available");
    }
  }
}
