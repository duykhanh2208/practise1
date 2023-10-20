function showProduct() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/products",
        success: function (data) {
            let content = ""
            content+= `<button onclick="ShowProductAddingForm()">Thêm sản phẩm</button>`
            content += `<table>
            <tr>
                <th>Số thứ tự</th>
                <th>Tên sản phẩm</th>
                <th>Số lượng</th>
                <th>Đơn giá</th>
                <th>Ngày sản xuất</th>
                <th>Nhà cung cấp</th>
                <th>Miêu tả</th>
                <th>Kho chứa</th>
                <th colspan="2"></th>
            </tr>`
            for (let i = 0; i < data.length; i++) {
                content += `<tr>
                <td>${i + 1}</td>
                <td>${data[i].name}</td>
                <td>${data[i].quantity}</td>
                <td>${data[i].price}</td>
                <td>${data[i].date}</td>
                <td>${data[i].provider}</td>
                <td>${data[i].description}</td>
                <td>${data[i].wareHouse.name}</td>
                <td><button onclick="updateProduct(${data[i].id})">Sửa sản phẩm</button></td>
                <td><button onclick="deleteProduct(${data[i].id})">Xóa sản phẩm</button></td>
            </tr>`
            }
            content += `</table>`

            document.getElementById("display").innerHTML=content;
        }
    })
}

function ShowProductAddingForm(){
    let product = ""
    product+=`<table>
        <tr>
            <td>Nhập vào tên sản phẩm</td>
            <td><input type="text" id="name"></td>
        </tr>
        <tr>
            <td>Nhập vào số lượng sản phẩm</td>
            <td><input type="text" id="quantity"></td>
        </tr>
        <tr>
            <td>Nhập giá</td>
            <td><input type="text" id="price"></td>
        </tr>
        <tr>
            <td>Chọn ngày sản xuất</td>
            <td><input type="Date" id="date"></td>
        </tr>
        <tr>
            <td>Nhập vào nhà sản xuất</td>
            <td><input type="text" id="provider"></td>
        </tr>
        <tr>
            <td>Nhập vào miêu tả sản phẩm</td>
            <td><input type="text" id="description"></td>
        </tr>
        <tr>
            <td>Lựa chọn kho</td>
            <td><select name="wareHouse" id="wareHouse"></select></td>
        </tr>
        <tr>
            <td></td>
            <td></td>
        </tr>
        <tr>
        <td><button onclick="goback()">Quay lại</button></td>
        <td><button onclick="createProduct()">Tạo mới sản phẩm</button></td>
        </tr>
    </table>`
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/wareHouses",
        success: function (data) {
            let content = ""
            for (let i = 0; i < data.length; i++) {
                content+=`<option value="${data[i].id}">${data[i].name}</option>`
            }
            document.getElementById("wareHouse").innerHTML= content;
        }
    })

    document.getElementById("display").innerHTML=product;
}

function goback(){
    showProduct();
}

function createProduct(){
    let name = document.getElementById("name").value;
    let quantity = document.getElementById("quantity").value;
    let price = document.getElementById("price").value;
    let date = document.getElementById("date").value;
    let provider = document.getElementById("provider").value;
    let description = document.getElementById("description").value;
    let wareHouse = document.getElementById("wareHouse").value;
    let product1 ={
        name:name,
        quantity:quantity,
        price:price,
        date:date,
        provider:provider,
        description:description,
        wareHouse:{
            id:wareHouse
        }
    }
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "POST",
        data: JSON.stringify(product1),
        url: "http://localhost:8080/api/products/create",
        success: function () {
            alert("Tạo thành công!")
            showProduct();
        }
        })
}

function updateProduct(id){

    $.ajax({
        type: "GET",
        url: `http://localhost:8080/api/products/${id}`,
        success: function (data) {
            let product = ""
            product+=`<table>
        <tr>
            <td>Nhập vào tên sản phẩm</td>
            <td><input type="text" id="nameUpdate" value="${data.name}"></td>
            
        </tr>
        <tr>
            <td>Nhập vào số lượng sản phẩm</td>
            <td><input type="text" id="quantityUpdate" value="${data.quantity}"></td>
        </tr>
        <tr>
            <td>Nhập giá</td>
            <td><input type="text" id="priceUpdate" value="${data.price}"></td>
        </tr>
        <tr>
            <td>Chọn ngày sản xuất</td>
            <td><input type="Date" id="dateUpdate" value="${data.date}"></td>
        </tr>
        <tr>
            <td>Nhập vào nhà sản xuất</td>
            <td><input type="text" id="providerUpdate" value="${data.provider}"></td>
        </tr>
        <tr>
            <td>Nhập vào miêu tả sản phẩm</td>
            <td><input type="text" id="descriptionUpdate" value="${data.description}"></td>
        </tr>
        <tr>
            <td>Lựa chọn kho</td>
            <td><select name="wareHouseUpdate" id="wareHouseUpdate"></select></td>
        </tr>
        <tr>
            <td></td>
            <td></td>
        </tr>
        <tr>
        <td><button onclick="goback()">Quay lại</button></td>
        <td><button onclick="saveProduct(${data.id})">Cập nhật sản phẩm</button></td>
        </tr>
    </table>`
            $.ajax({
                type: "GET",
                url: "http://localhost:8080/api/wareHouses",
                success: function (data) {
                    let content = ""
                    for (let i = 0; i < data.length; i++) {
                        content+=`<option value="${data[i].id}">${data[i].name}</option>`
                    }
                    document.getElementById("wareHouseUpdate").innerHTML= content;
                }
            })

            document.getElementById("display").innerHTML=product;
        }
    })


}

function saveProduct(id){
    let name = document.getElementById("nameUpdate").value;
    let quantity = document.getElementById("quantityUpdate").value;
    let price = document.getElementById("priceUpdate").value;
    let date = document.getElementById("dateUpdate").value;
    let provider = document.getElementById("providerUpdate").value;
    let description = document.getElementById("descriptionUpdate").value;
    let wareHouse = document.getElementById("wareHouseUpdate").value;
    let product1 ={
        id:id,
        name:name,
        quantity:quantity,
        price:price,
        date:date,
        provider:provider,
        description:description,
        wareHouse:{
            id:wareHouse
        }
    }
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "POST",
        data: JSON.stringify(product1),
        url: `http://localhost:8080/api/products/update/${id}`,
        success: function () {
            alert("Cập nhật thành công!")
            showProduct();
        }
    })
}

function deleteProduct(id){
    if(confirm("Bạn có chắc muốn xóa sản phẩm này?")) {
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            type: "DELETE",
            url: `http://localhost:8080/api/products/delete/${id}`,
            success: function () {
                alert("Sản phẩm đã được xóa!")
                showProduct();
            }
        })
    }
}