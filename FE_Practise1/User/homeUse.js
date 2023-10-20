// function LoadCartNumber(){
//     let data = localStorage.getItem("user_practise1");
//     let user = JSON.parse(data);
//     $.ajax({
//         headers: {
//             'Accept': 'application/json',
//             'Content-Type': 'application/json'
//         },
//         type: "POST",
//         data: JSON.stringify(user),
//         url: "http://localhost:8080/api/carts/showCart",
//         success: function (data) {
//             let content = `<button onclick="showCartByUser()">Giỏ của bạn có ${data.length} sản phẩm</button>`
//             document.getElementById("showCartDetail").innerHTML = content;
//         }
//     })
// }
//
// function showAllCartDetail() {
//     let data1 = localStorage.getItem("user_practise1");
//     let user1 = JSON.parse(data1);
//     $.ajax({
//         headers: {
//             'Accept': 'application/json',
//             'Content-Type': 'application/json'
//         },
//         type: "POST",
//         data: JSON.stringify(user1),
//         url: "http://localhost:8080/api/carts/showCart",
//         success: function (data) {
//             let content = ""
//             content += `<table>
//         <tr>
//         <th>STT</th>
//         <th>Tên sản phẩm</th>
//         <th>Số lượng</th>
//         <th>Đơn giá</th>
//         <th>Thành tiền</th>
//         <th colspan="2"></th>
//         </tr>`
//             for (let i = 0; i < data.length; i++) {
//                 content += `<tr>
//             <td>${i + 1}</td>
//             <td>${data[i].product.name}</td>
//             <td>${data[i].quantity}</td>
//             <td>${data[i].product.price}</td>
//             <td>${data[i].product.price*data[i].quantity}</td>
//             <td><button onclick="payment(${data[i].id})">Thanh toán</button></td>
//             <td><button onclick="returnProduct(${data[i].id})">Trả hàng</button></td>
//             </tr>`
//             }
//             content += `</table>`
//             document.getElementById("showCartByUser").innerHTML = content;
//         }
//     })
// }
// function returnProduct(id){
//     confirm("Bạn có chắc muốn trả lại hàng");
//      let cartDetail_id = id;
//     $.ajax({
//         type: "DELETE",
//         url: `http://localhost:8080/api/carts/cartDetail/${cartDetail_id}`,
//         success: function () {
//            alert("Bạn đã trả hàng thành công.")
//             }
//         })
//     showAllCartDetail();
//     LoadCartNumber();
// }
//
// function loadDisplay() {
//     LoadCartNumber();
//     $.ajax({
//         type: "GET",
//         url: "http://localhost:8080/api/products",
//         success: function (data) {
//             let content = ""
//             content += `<table>
//         <tr>
//         <th>STT</th>
//         <th>Tên sản phẩm</th>
//         <th>Đơn giá</th>
//         <th>Số lượng</th>
//         <th>Ngày sản xuất</th>
//         <th>Nhà cung cấp</th>
//         <th>Kho</th>
//         <th>Nhập số lượng thêm</th>
//         <th colspan="2"></th>
//         </tr>`
//             for (let i = 0; i < data.length; i++) {
//                 content += `<tr>
//             <td>${i + 1}</td>
//             <td>${data[i].name}</td>
//             <td>${data[i].price}</td>
//             <td>${data[i].quantity}</td>
//             <td>${data[i].date}</td>
//             <td>${data[i].provider}</td>
//             <td>${data[i].wareHouse.name}</td>
//             <td><button onclick="addToCart(${data[i].id})">Thêm vào giỏ</button></td>
//             <td><button onclick="viewDetail(${data[i].id})">Xem chi tiết</button></td>
//             </tr>`
//             }
//             content += `</table>`
//             document.getElementById("display").innerHTML = content;
//         }
//     })
//     event.preventDefault();
// }
//
// function addToCart(id) {
//     let quantity = prompt("Nhập vào số lượng mua");
//     let flag = true;
//     while (flag===true){
//         if(quantity<=0){
//             quantity = prompt("Nhập vào số lượng mua");
//         }else {
//             flag=false;
//         }
//     }
//
//     let data2 = localStorage.getItem("user_practise1");
//     let Obj = JSON.parse(data2);
//     let cartDetail = {
//         product : {
//             id : id
//         },
//         cart: {},
//         buyDate: new Date(),
//         quantity : +quantity
//     }
//     $.ajax({
//         headers: {
//             'Accept': 'application/json',
//             'Content-Type': 'application/json'
//         },
//         type: "POST",
//         data: JSON.stringify(cartDetail),
//         url: `http://localhost:8080/api/carts/addToCart/${Obj.id}`,
//         success: function (data) {
//             console.log(data.id)
//         }
//     })
//     LoadCartNumber();
// }
//
//
// function showCartByUser() {
//     window.location.href = "../FE_Practise1/User/ShowCartByUser.html"
// }
//
// function payment(){
//     let data2 = localStorage.getItem("user_practise1");
//     let userOnLocalStorage = JSON.parse(data2);
//     let user_id = userOnLocalStorage.id;
//
//     $.ajax({
//         headers: {
//             'Accept': 'application/json',
//             'Content-Type': 'application/json'
//         },
//         type: "POST",
//         url: `http://localhost:8080/api/bills/saveBillDetail/${user_id}`,
//         success: function () {
//             alert("Bạn đã thanh toán thành công!")
//
//         }
//     })
//
// }
//
