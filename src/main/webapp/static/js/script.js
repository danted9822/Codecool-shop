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

// let countQuantity = 1;
// let countEl = document.getElementById("count");
// let productId = document.querySelector('.plus').getAttribute('data-add_product')
// console.log(productId);
// function plusQuantity() {
//     console.log("Kecske" + this)
//     let dataPlus = document.querySelector('.plus')
//     let data = dataPlus.getAttribute('data-add_product');
//
//
//     countQuantity++;
//     countEl.value = countQuantity
//     console.log(countQuantity);
//
// }
//
// function minusQuantity() {
//     console.log("Kecske" + this)
//     let dataPlus = document.querySelector('#minus')
//     let data = dataPlus.getAttribute('data-add_product');
//
//
//     countQuantity--;
//     countEl.value = countQuantity
//     console.log(countQuantity);
// }

document.querySelectorAll(".plus").forEach(button => button.addEventListener("click", function() {
    this.previousElementSibling.value = parseInt(this.previousElementSibling.value) + 1;
}));

document.querySelectorAll(".minus").forEach(button => button.addEventListener("click", function() {
    this.nextElementSibling.value = parseInt(this.nextElementSibling.value) - 1;
}));
