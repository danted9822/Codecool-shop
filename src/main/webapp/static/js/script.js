function changeCategory() {
    let eID = document.getElementById("category");
    let category = eID.options[eID.selectedIndex].value;
    console.log(category)
}

function changeSupplier() {
    let eID = document.getElementById("supplier");
    let supplier = eID.options.value;
    console.log(supplier)
}