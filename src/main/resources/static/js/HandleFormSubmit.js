// create cycle form handler
function handleCreateCycleFormSubmit() {
  let addCycleForm = document.getElementById("create-cycle-form").elements;
  let name = addCycleForm.namedItem("name").value;
  let model = addCycleForm.namedItem("model").value;
  let type = addCycleForm.namedItem("type").value;

  if (name.length < 2 || model.length < 2 || type.length < 2) {
    alert("Entry length cannot be less than 2");
    return false;
  }

  return true;
}

// update cycle form handler
function handleUpdateCycleFormSubmit() {
  return handleCreateCycleFormSubmit();
}

// registration form submit handler

function handleRegistrationFormSubmit() {
  let registrationForm = document.getElementById("registration-form");

  let password = registrationForm.namedItem("password");
  let confirmPassword = registrationForm.namedItem("confirm-password");

  if (password === confirmPassword) {
    registrationForm.removeChild(confirmPassword);
    return true;
  } else {
    alert("Passwords do not match");
    return false;
  }
}
