function changeCategory() {
    let eID = document.getElementById("inlineFormCustomSelect category");
    let category = eID.options[eID.selectedIndex].value;
    console.log(category)
    location.href = "/sort?category=" + category
}

function changeSupplier() {
    let eID = document.getElementById("inlineFormCustomSelect supplier");
    let supplier = eID.options[eID.selectedIndex].value;
    console.log(supplier)
    location.href = "/sort?supplier=" + supplier
}
