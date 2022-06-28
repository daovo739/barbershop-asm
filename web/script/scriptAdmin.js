/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
    const toastLiveExample = document.getElementById('liveToastAdmin');
    const toast = new bootstrap.Toast(toastLiveExample);
    
const updateProduct =  (ele) => {
    const id = ele.getAttribute("data-id");
    const name = document.querySelector("#name-" + id).value || "";
    const brand = document.querySelector("#brand-" + id).value || "";
    const category = document.querySelector("#category-" + id).value || "";
    const price = document.querySelector("#price-" + id).value || "";
    const ajaxfile = document.querySelector("#ajaxfile-" + id);
    const file = ajaxfile.files[0];

    if (!name && !brand && !price && !file && !category) {
        document.querySelector(".toast-body-admin").textContent = "Empty input";
        toast.show();
    } else {
        let data = new FormData();
        data.append("file", file);
        data.append("id", id);
        data.append("name", name);
        data.append("brand", brand);
        data.append("category", category);
        data.append("price", price);
        $.ajax({
            url: "/barbershop/productsAdmin",
            type: "PUT",
            processData: false,
            contentType: false,
            enctype: 'multipart/form-data',
            data: data,
            success: function (results) {
                document.querySelectorAll(".close-modal").forEach((item) => {
                    item.click();
                });
                document.querySelector(".products-container").innerHTML = results;
                document.querySelector(".toast-body-admin").textContent = "Update successfully!";            
                toast.show();
//                            console.log(results);
            },
            error: function (error) {
                document.querySelector(".toast-body-admin").textContent = "Update failed!";
                toast.show();
                console.log(error);
            }
        });
    }
};

const createProduct =  () => {
    const name = document.querySelector("#name-create").value;
    const brand = document.querySelector("#brand-create").value;
    const category = document.querySelector("#category-create").value;
    const price = document.querySelector("#price-create").value;
    const ajaxfile = document.querySelector("#ajaxfile-create");
    const file = ajaxfile.files[0];
    console.log(price);
    if (!name || !brand || !price || !file || !category) {
        document.querySelector(".toast-body-admin").textContent = "Empty input";
        toast.show();
    } else {
        let data = new FormData();
        data.append("file", file);
        data.append("name", name);
        data.append("brand", brand);
        data.append("category", category);
        data.append("price", price);
        $.ajax({
            url: "/barbershop/productsAdmin",
            type: "POST",
            processData: false,
            contentType: false,
            enctype: 'multipart/form-data',
            data: data,
            success: function (results) {
                document.querySelectorAll(".close-modal").forEach((item) => {
                    item.click();
                });
                document.querySelector(".products-container").innerHTML = results;
                document.querySelector(".toast-body-admin").textContent = "Create successfully!";            
                toast.show();
//                            console.log(results);
            },
            error: function (error) {
                document.querySelector(".toast-body-admin").textContent = "Create failed!";
                toast.show();
                console.log(error);
            }
        });
    }
};

const deleteProduct = (ele) => {
    const id = ele.getAttribute("data-id");
    $.ajax({
        url: "/barbershop/productsAdmin" + "?" + $.param({id}),
        type: "DELETE",
        success: function (results) {
            document.querySelectorAll(".close-modal").forEach((item) => {
                    item.click();
                });
            document.querySelector(".products-container").innerHTML = results;
            document.querySelector(".toast-body-admin").textContent = "Delete successfully!";
            toast.show();
//                            console.log(results);
        },
        error: function (error) {
            document.querySelector(".toast-body-admin").textContent = "Delete failed!";
            toast.show();
            console.log(error);
        }
    });

};

const searchListAdmin = (value) => {
    if (value) {
        $.ajax({
            url: "/barbershop/searchAdmin",
            type: "POST",
            data: {
                search: value
            },
            success: function (results) {
                document.querySelector(".products-container").innerHTML = results;
            },
            error: function (error) {
                console.log(error);
            }
        });       
    } 
};