function showAllCartDetail(){
    let data1 = localStorage.getItem("user_practise1");
    let user1 = JSON.parse(data1);
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "POST",
        data: JSON.stringify(user1),
        url: "http://localhost:8080/api/carts/showCart",
        success: function (data) {
            let content = ""
            content += `<table>
        <tr>
        <th>STT</th>
        <th>Tên sản phẩm</th>
        <th>Số lượng</th>
        <th>Đơn giá</th>
        <th>Thành tiền</th>
        <th colspan="1"></th>
        </tr>`
            let TotalMoney = 0;
            for (let i = 0; i < data.length; i++) {
                TotalMoney += data[i].quantity*data[i].product.price;
                content += `<tr>
            <td>${i + 1}</td>
            <td>${data[i].product.name}</td>
            <td>${data[i].quantity}</td>
            <td>${data[i].product.price}</td>
            <td>${data[i].product.price*data[i].quantity}</td>
       
        
            <td><button onclick="returnProduct(${data[i].id})">Trả hàng</button></td>
            </tr>`
            }
            content += `<tr>
    <td><a href="/FE_Practise1/Home/Home.html">Quay lại</a></td>
    <td colspan="3"></td>
    <td><label for="">Tổng tiền: </label><label for="">${TotalMoney}</label> </td>
    <td><button onclick="payment()">Thanh toán</button></td>
</tr>
</table>`
            document.getElementById("display").innerHTML = content;
        }
    })
}

function addToCart(id) {
    let quantity = prompt("Nhập vào số lượng mua");
    let flag = true;
    while (flag===true){
        if(quantity<=0){
            quantity = prompt("Nhập vào số lượng mua");
        }else {
            flag=false;
        }
    }

    let data2 = localStorage.getItem("user_practise1");
    let Obj = JSON.parse(data2);
    let cartDetail = {
        product : {
            id : id
        },
        cart: {},
        buyDate: new Date(),
        quantity : +quantity
    }
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "POST",
        data: JSON.stringify(cartDetail),
        url: `http://localhost:8080/api/carts/addToCart/${Obj.id}`,
        success: function (data) {
            console.log(data.id)
        }
    })
    LoadCartNumber();

}

function payment(){
    let data2 = localStorage.getItem("user_practise1");
    let userOnLocalStorage = JSON.parse(data2);
    let user_id = userOnLocalStorage.id;
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "POST",
        url: `http://localhost:8080/api/bills/saveBillDetail/${user_id}`,
        success: function () {
            alert("Bạn đã thanh toán thành công!");
            showAllCartDetail();

        }
    })
}

function returnProduct(id){
    confirm("Bạn có chắc muốn trả lại hàng");
    let cartDetail_id = id;
    $.ajax({
        type: "DELETE",
        url: `http://localhost:8080/api/carts/cartDetail/${cartDetail_id}`,
        success: function () {
            alert("Bạn đã trả hàng thành công.");
            showAllCartDetail();
        }
    })
    LoadCartNumber();
}