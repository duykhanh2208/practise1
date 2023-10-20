function showBill(){
    let data1 = localStorage.getItem("user_practise1");
    let user1 = JSON.parse(data1);
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "POST",
        data: JSON.stringify(user1),
        url: `http://localhost:8080/api/bills/searchBillDetailByUserID/${user1.id}`,
        success: function (data) {
            console.log(data);
            let content = ""
            content += `<table>
        <tr>
        <th>STT</th>
        <th>Tên sản phẩm</th>
        <th>Số lượng</th>
        <th>Đơn giá</th>
        <th>Ngày mua hàng</th>
        <th>Tên tài khoản</th>
        <th>Thành tiền</th>
  
        </tr>`
            let TotalMoney = 0;
            for (let i = 0; i < data.length; i++) {
                TotalMoney += data[i].quantity*data[i].price;
                content += `<tr>
            <td>${i + 1}</td>
            <td>${data[i].product.name}</td>
            <td>${data[i].quantity}</td>
            <td>${data[i].price}</td>
            <td>${data[i].date}</td>
            <td>${data[i].bill.user.id}</td>
            <td>${data[i].price*data[i].quantity}</td>
            </tr>`
            }
            content += `<tr>
    <td><a href="/FE_Practise1/Home/Home.html">Quay lại</a></td>
    <td colspan="4"></td>
    <td><label for="">Tổng tiền: </label><label for="">${TotalMoney}</label> </td>
</tr>
</table>`
            document.getElementById("display").innerHTML = content;
        }
    })
}