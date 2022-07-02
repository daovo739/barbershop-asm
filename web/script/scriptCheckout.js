const cityEle = document.querySelector("#cities");
const districtEle = document.querySelector("#districts");
const wardEle = document.querySelector("#wards");
const checkoutMethod = document.querySelectorAll(".checkout-method");

let data;
let city;
let district;
let districts;
let itemRadio;
let totalCost;

const loadData = () => {
    window.addEventListener("load", async () => {
        updatePrice();
        const respones = await fetch("https://provinces.open-api.vn/api/?depth=3");
        data = await respones.json();
        let option = `<option value="" selected>Choose the city</option>`;
        data.forEach((city) => {
            option += `<option value="${city.name}" data-id-city="${city.code}">${city.name}</option>`;
        });
        cityEle.innerHTML = option;
        
    });

    cityEle.addEventListener("change", () => {
        const cityId = cityEle.options[cityEle.selectedIndex].getAttribute("data-id-city");
        city = data.filter((item) => {
            if (item.code === +cityId) {
                return item;
            }
        });
        districts = city[0].districts;

        let option = `<option value="" selected>Choose the district</option>`;
        districts.forEach((ele) => {
            option += `<option value="${ele.name}" data-id-district="${ele.code}">${ele.name}</option>`;
        });
        districtEle.innerHTML = option;
    });

    districtEle.addEventListener("change", () => {
        const districtId = districtEle.options[districtEle.selectedIndex].getAttribute("data-id-district");
        district = districts.filter((item) => {
            if (item.code === +districtId) {
                return item;
            }
        });

        const wards = district[0].wards;
        let option = `<option value="" selected>Choose the district</option>`;
        wards.forEach((ele) => {
            option += `<option value="${ele.name}">${ele.name}</option>`;
        });
        wardEle.innerHTML = option;
    });
};

loadData();

const changeQuantityCheckout = (inputEle) => {
    const quantity = inputEle.value;
    const id = inputEle.getAttribute('data-product-id');
    $.ajax({
        url: "/barbershop/checkout" + "?" + $.param({quantity, id}),
        type: "PUT",
        success: function (results) {
//                            console.log(results);
            document.querySelector(".checkout-products").innerHTML = results;
            updatePrice();
        },
        error: function (error) {
            console.log(error);
        }
    });

};

const deleteProductCheckout = (product) => {
    const id = product.getAttribute("data-product-id");
    $.ajax({
        url: "/barbershop/checkout" + "?" + $.param({id}),
        type: "DELETE",
        success: function (results) {
//                            console.log(results);
            
            document.querySelector(".checkout-products").innerHTML = results;
            updatePrice();
            const cartCountEle = document.querySelector('.cart-count');
            cartCountEle.textContent = parseInt(cartCountEle.textContent) - 1;
        },
        error: function (error) {
            console.log(error);
        }
    });
    
};

const updatePrice = () => {
    const checkoutFeeShipping = document.querySelector(".checkout-fee-shipping");
    const checkoutTotal = document.querySelector(".checkout-total");
    const checkoutCartTotal = document.querySelector(".checkout-cart-total");
    checkoutMethod.forEach((item) => {
        if (item.checked) {
            itemRadio = item.getAttribute("data-payment-price");
        }
    });
    checkoutFeeShipping.textContent = `${itemRadio}`;
    let total = parseFloat(itemRadio.replace("$", "")) + parseFloat(checkoutCartTotal.textContent.replace("$", ""));
    checkoutTotal.textContent = `$${total.toFixed(2)}`;
    totalCost = total.toFixed(2);
};

checkoutMethod.forEach((item) => {
    item.addEventListener("click", () => {
        updatePrice();
    });
});



