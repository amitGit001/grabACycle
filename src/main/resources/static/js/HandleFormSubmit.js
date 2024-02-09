function handleFormSubmit() {
  let addCycleForm = document.getElementById("create-cycle-form").elements;
  let name = addCycleForm.namedItem("name").value;
  let model = addCycleForm.namedItem("model").value;
  let type = addCycleForm.namedItem("type").value;

  //   console.log(addCycleForm);
  //   console.log(name);
  //   console.log(model);
  //   console.log(type);

  if (name.length < 2 || model.length === 0 || type.length === 0) {
    alert("Please check your entries");
    return false;
  }

  return true;
}
